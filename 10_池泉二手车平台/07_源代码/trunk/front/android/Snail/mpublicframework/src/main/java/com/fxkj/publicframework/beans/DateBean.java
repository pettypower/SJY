package com.fxkj.publicframework.beans;

public class DateBean {
    private String date;//日期
    private String day;
    private boolean isSign;//是否签到
    private boolean isSelect;//是否选中

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {

        return day;
    }

    public boolean isSelect() {

        return isSelect;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getDate() {

        return date;
    }

    public void setSign(boolean sign) {
        isSign = sign;
    }

    public boolean isSign() {

        return isSign;
    }
}
