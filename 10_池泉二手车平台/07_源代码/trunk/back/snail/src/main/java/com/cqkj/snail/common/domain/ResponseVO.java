package com.cqkj.snail.common.domain;

import java.io.Serializable;
import java.util.HashMap;

public class ResponseVO extends HashMap<String, Object> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public ResponseVO status(boolean status) {
        this.put("status", status);
        return this;
    }

    public ResponseVO message(String message) {
        this.put("message", message);
        return this;
    }

    public ResponseVO data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public ResponseVO put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
