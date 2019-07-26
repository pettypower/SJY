package com.fxkj.publicframework.beans;

import java.util.ArrayList;
import java.util.HashMap;

public class CallBackObject {
    private int rowCount;
    private ArrayList<Object> list = new ArrayList<>();
    private Object object;
    private HashMap params = new HashMap();

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public ArrayList<Object> getList() {
        return list;
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public HashMap getParams() {
        return params;
    }

    public void setParams(HashMap params) {
        this.params = params;
    }
}
