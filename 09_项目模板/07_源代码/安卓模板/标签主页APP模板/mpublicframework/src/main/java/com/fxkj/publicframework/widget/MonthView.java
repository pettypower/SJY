package com.fxkj.publicframework.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fxkj.publicframework.R;
import com.fxkj.publicframework.beans.DateBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MonthView extends ViewGroup {
    private static final int ROW = 6;
    private static final int COLUMN = 7;
    private Context mContext;
    private OnSingleChooseListener onSingleChooseListener;

    public void setOnSingleChooseListener(OnSingleChooseListener onSingleChooseListener) {
        this.onSingleChooseListener = onSingleChooseListener;
    }

    public MonthView(Context context) {
        super(context);
    }
    public MonthView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);

        mContext = context;
        setBackgroundColor(mContext.getResources().getColor(R.color.layout_bg));
    }
    /**
     * 根据年 月 获取对应的月份 天数
     */
    public int[] getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int[] iArr = new int[2];
        int maxDate = a.get(Calendar.DATE);
        iArr[0] = maxDate;
        a.set(Calendar.DAY_OF_MONTH, 1);
        iArr[1] = a.get(Calendar.DAY_OF_WEEK);
        return iArr;
    }
    /**
     *
     * @param signs 签到日期列表
     * @param month 月份
     */
    public void setDate(List<String> signs,String month){
        if (TextUtils.isEmpty(month)){
            return;
        }
        if (!month.contains("-"))
            return;
        String[] mArr = month.split("-");
        int[] daysByYearMonth = getDaysByYearMonth(Integer.parseInt(mArr[0]), Integer.parseInt(mArr[1]));
        int days = daysByYearMonth[0];
        int weekDay = daysByYearMonth[1];
        List<DateBean> dateBeans = new ArrayList<>();

        DateBean bean = null;
        for (int i = 1; i <= days; i++) {
            bean = new DateBean();
            String m = mArr[1];
            m = m.length()==1?"0"+m:m;
            String day = i<10?"0"+i:i+"";
            bean.setDate(mArr[0]+"-"+m+"-"+day);
            bean.setDay(i+"");
            bean.setSelect(false);
            boolean isSign = false;
            if (signs!=null){
                for (String srt : signs) {
                    if (bean.getDate().equals(srt)) {
                        isSign = true;
                        break;
                    }
                }
            }
            bean.setSign(isSign);
            dateBeans.add(bean);
            bean = null;
        }

        List<DateBean> bDs = new ArrayList<>();
        //填充当月第一天之前的日期
        for (int i = 0; i < weekDay-1 ; i++) {
            DateBean dateBean = new DateBean();
            dateBean.setDay("");
            bDs.add(dateBean);
        }
        dateBeans.addAll(0,bDs);
        setDateList(dateBeans);
    }
    private View lastView;
    public void setDateList(List<DateBean> dates) {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        for (int i=0;i<dates.size();i++) {
            DateBean dateBean = dates.get(i);
            View view;
            TextView solarDay;//阳历TextView
            View v_point;
            view = LayoutInflater.from(mContext).inflate(R.layout.item_month_layout, null);
            solarDay = (TextView) view.findViewById(R.id.solar_day);
            solarDay.setTextColor(mContext.getResources().getColor(R.color.layout_title_color));
            solarDay.setTextSize(14);
            solarDay.setText(dateBean.getDay());
            v_point = view.findViewById(R.id.v_point);
            if (dateBean.isSign()){
                v_point.setVisibility(View.VISIBLE);
            }else{
                v_point.setVisibility(View.INVISIBLE);
            }

            LinearLayout lin_d = (LinearLayout) view.findViewById(R.id.lin_d);
            if (dateBean.isSelect()){
                lin_d.setBackgroundResource(R.mipmap.blue_circle);
            }else{
                lin_d.setBackgroundResource(R.mipmap.blue_tra);
            }
            if (!TextUtils.isEmpty(dateBean.getDate())){
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lastView != null) {
                            setDayColor(lastView, 0);
                        }
                        setDayColor(v, 1);
                        lastView = v;
                        if (onSingleChooseListener != null) {
                            onSingleChooseListener.onSingleChoose(v, dateBean);
                        }
                    }
                });
            }
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            if (today.equals(dateBean.getDate())){
                view.performClick();
            }
            addView(view, i);
        }
        requestLayout();
    }
    private void setDayColor(View v, int type) {
        TextView solarDay = (TextView) v.findViewById(R.id.solar_day);
        LinearLayout lin_d = (LinearLayout) v.findViewById(R.id.lin_d);
        solarDay.setTextSize(14);
        if (type == 0) {
            lin_d.setBackgroundResource(R.mipmap.blue_tra);
            solarDay.setTextColor(mContext.getResources().getColor(R.color.layout_title_color));
        } else if (type == 1) {
            lin_d.setBackgroundResource(R.mipmap.blue_circle);
            solarDay.setTextColor(mContext.getResources().getColor(R.color.textcolor));
        }
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() == 0) {
            return;
        }

        View childView = getChildAt(0);
        int itemWidth = childView.getMeasuredWidth();
        int itemHeight = childView.getMeasuredHeight();
        //计算列间距
        int dx = (getMeasuredWidth() - itemWidth * COLUMN) / (COLUMN * 2);

        //当显示五行时扩大行间距
        int dy = 0;
        if (getChildCount() == 35) {
            dy = itemHeight / 5;
        }

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);

            int left = i % COLUMN * itemWidth + ((2 * (i % COLUMN) + 1)) * dx;
            int top = i / COLUMN * (itemHeight + dy);
            int right = left + itemWidth;
            int bottom = top + itemHeight;
            view.layout(left, top, right, bottom);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int itemWidth = widthSpecSize / COLUMN;

        //计算日历的最大高度
        if (heightSpecSize > itemWidth * ROW) {
            heightSpecSize = itemWidth * ROW + ROW * 30;
        }

        setMeasuredDimension(widthSpecSize, heightSpecSize);

        int itemHeight = heightSpecSize / ROW;

        int itemSize = Math.min(itemWidth, itemHeight);
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.measure(MeasureSpec.makeMeasureSpec(itemSize, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(itemSize, MeasureSpec.EXACTLY));
        }
    }

   public interface OnSingleChooseListener {
        /**
         * @param view
         * @param date
         */
        void onSingleChoose(View view, DateBean date);
    }
}
