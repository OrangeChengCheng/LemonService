package org.cheng.lemon.entity;

import org.cheng.lemon.unit.Tools;

public class ResultData {

    public String time;
    public Object data;
    public String errorCode;
    public String errorMsg;

    public ResultData () {
        this.time = Tools.getCurrentTimeFormat();;
        this.errorCode = "";
        this.errorMsg = "";
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
