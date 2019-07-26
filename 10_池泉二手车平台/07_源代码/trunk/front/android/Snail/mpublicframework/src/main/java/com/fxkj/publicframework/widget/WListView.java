package com.fxkj.publicframework.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.fxkj.publicframework.R;
import com.fxkj.publicframework.globalvariable.Constant;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by wxmij on 2016/4/29.
 */
public class WListView extends ListView implements OnScrollListener {
    private float mLastY = -1;
    private RelativeLayout footView, headView;
    private View footMainView;
    private Scroller mScroller;
    private OnScrollListener mScrollListener;
    private onLoadMoreListener loadMoreListener;
    private int mScrollBack;
    private int mTotalItemCount;
    private final static int SCROLLBACK_HEADER = 0;
    private final static int SCROLLBACK_FOOTER = 1;
    private final static int SCROLL_DURATION = 400;
    private final static int PULL_LOAD_MORE_DELTA = 50;
    private final static float OFFSET_RADIO = 1.8f;
    private Constant.FootViewType footViewType = Constant.FootViewType.loading;
    private boolean isScroll;
    private Timer timer;


    public void setFootbar_no_data_text(String footbar_no_data_text) {
        this.footbar_no_data_text = footbar_no_data_text;
    }

    private String footbar_no_data_text = "--没有更多数据--";

    public void setScroll(boolean isScroll) {
        this.isScroll = isScroll;
    }

    public WListView(Context context) {
        this(context, null);
    }

    public WListView(Context context, AttributeSet attrs) {
        this(context, attrs, context.getResources().getIdentifier("listViewStyle", "attr", "com.android.internal"));

    }

    public WListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {

        mScroller = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);
        footView = new RelativeLayout(context);
        footView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        this.addFooterView(footView);
        headView = new RelativeLayout(context);
        headView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        this.addHeaderView(headView);
    }

    public void setFootViewType(Constant.FootViewType _footViewType) {
        footViewType = _footViewType;
        footView.removeAllViews();
        switch (_footViewType) {
            case blank:
                break;
            case loading:
                //addProgressFootView();
                addAnimFootView();
                //addMutiFootView();
                break;
            case unable:
                break;
            case load_over:
                addNoDataFootView();
                break;
            case no_data:
                addNoDataFootView();
                break;
            case no_more_data:
                addNoDataFootView();
                break;
        }
    }


    int lastPosition = 0;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mScrollListener != null) {
            mScrollListener.onScrollStateChanged(view, scrollState);
        }
        int position = getLastVisiblePosition();
        if (footViewType == Constant.FootViewType.load_over) {
            if (position > mTotalItemCount - 3) {
                if (loadMoreListener != null) {
                    setFootViewType(Constant.FootViewType.loading);
                    loadMoreListener.onLoadMore();
                    AutoStopRefresh();
                }
            }
        }
//        if (footViewType == RequestUrl.FootViewType.blank && position > lastPosition && position == mTotalItemCount - 1) {
//            resetFooterHeight();
//        }
        lastPosition = position;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mTotalItemCount = totalItemCount;
        if (mScrollListener != null) {
            mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (mScrollBack == SCROLLBACK_HEADER) {
                //headView.setVisiableHeight(mScroller.getCurrY());
            } else {
                footView.setPadding(0, 0, 0, mScroller.getCurrY());
            }
            postInvalidate();
            invokeOnScrolling();
        }
        super.computeScroll();
    }

    private void invokeOnScrolling() {
        if (mScrollListener instanceof OnWScrollListener) {
            OnWScrollListener l = (OnWScrollListener) mScrollListener;
            l.onWScrolling(this);
        }
    }

    private void updateFooterHeight(float delta) {
        int height = footView.getPaddingBottom() + (int) delta;
        footView.setPadding(0, 0, 0, height);
    }

    private void resetFooterHeight() {
        int bottomMargin = footView.getPaddingBottom();
        if (bottomMargin > 0) {
            mScrollBack = SCROLLBACK_FOOTER;
            mScroller.startScroll(0, bottomMargin, 0, -bottomMargin, SCROLL_DURATION);
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                if (getLastVisiblePosition() == mTotalItemCount - 1 && (footView.getPaddingBottom() > 0 || deltaY < 0)) {
                    if (footViewType != Constant.FootViewType.unable) {
                        updateFooterHeight(-deltaY / OFFSET_RADIO);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mLastY = -1; // reset
                if (footViewType != Constant.FootViewType.unable) {
                    resetFooterHeight();
                }
        }
        return super.onTouchEvent(ev);
    }


    private void addProgressFootView() {
        footMainView = new ProgressBar(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        footMainView.setLayoutParams(layoutParams);
        footView.addView(footMainView);
    }

    private void addAnimFootView() {
        footMainView = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.setMargins(0, 50, 0, 50);
        footMainView.setLayoutParams(layoutParams);
        footView.addView(footMainView);
        ((ImageView) footMainView).setBackgroundResource(R.drawable.load_anim2);
        AnimationDrawable anim = (AnimationDrawable) ((ImageView) footMainView).getBackground();
        anim.start();
    }

    private void addMutiFootView() {
        footMainView = new LinearLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        footMainView.setLayoutParams(layoutParams);
        ((LinearLayout) footMainView).setOrientation(LinearLayout.HORIZONTAL);
        ProgressBar progressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleSmall);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(params);
        ((LinearLayout) footMainView).addView(progressBar);
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(params);
        textView.setText("正在加载中...");
        textView.setTextColor(Color.parseColor("#999999"));
        textView.setPadding(16, 0, 0, 0);
        ((LinearLayout) footMainView).addView(textView);
        footView.addView(footMainView);
    }

    private void addNoDataFootView() {
        footMainView = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.setMargins(0, 50, 0, 50);
        footMainView.setLayoutParams(layoutParams);
        ((TextView) footMainView).setTextColor(Color.parseColor("#999999"));
        if (footViewType == Constant.FootViewType.load_over) {
            ((TextView) footMainView).setText("上拉加载下一页");
        } else if (footViewType == Constant.FootViewType.no_data){
            ((TextView) footMainView).setText(footbar_no_data_text);
        }else{
            ((TextView) footMainView).setText("--抱歉,没有相关数据--");
        }
        footView.addView(footMainView);
    }

    public interface OnWScrollListener extends OnScrollListener {

        public void onWScrolling(View view);
    }

    public interface onLoadMoreListener {
        public void onLoadMore();
    }

    public void setLoadMoreListener(onLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    private void AutoStopRefresh() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                myHandler.sendEmptyMessage(0);
                cancel();
            }
        }, 8000, 8000);
    }

    private Handler myHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            setFootViewType(footViewType);
            return false;
        }
    });

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        if (!isScroll) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, expandSpec);
        }

    }
}
