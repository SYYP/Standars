package com.smartwasser.yunzhishui.bean;

import java.util.List;
/**
 * Created by huangmengjie on 2016/7/29 0029.
 */
public class RmonSelectResponse extends RBResponse {
    private DataEntity data;
    /**
     * data : {"dataList":[null,"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1",null,"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1",null],"dateArray":["2016-03-01 00:00:00","2016-04-01 00:55:00","2016-04-01 00:55:00","2016-04-01 01:56:00","2016-04-01 01:56:00","2016-04-01 02:56:00","2016-04-01 02:56:00","2016-04-01 03:56:00","2016-04-01 03:56:00","2016-04-01 04:56:00","2016-04-01 04:56:00","2016-04-01 05:56:00","2016-04-01 05:56:00","2016-04-01 06:56:00","2016-04-01 06:56:00","2016-04-01 08:56:00","2016-04-01 08:56:00","2016-04-01 10:56:00","2016-04-01 10:56:00","2016-04-01 12:56:00","2016-04-01 12:56:00","2016-04-01 16:56:00","2016-04-01 16:56:00","2016-04-01 17:56:00","2016-04-01 17:56:00","2016-04-01 19:55:00","2016-04-01 19:55:00","2016-04-01 20:56:00","2016-04-01 20:56:00","2016-04-01 23:56:00","2016-04-01 23:56:00","2016-04-02 00:56:00","2016-04-02 00:56:00","2016-04-02 01:56:00","2016-04-02 01:56:00","2016-04-02 02:56:00","2016-04-02 02:56:00","2016-04-02 03:56:00","2016-04-02 03:56:00","2016-04-02 04:56:00","2016-04-02 04:56:00","2016-04-02 05:56:00","2016-04-02 05:56:00","2016-04-02 07:30:00","2016-04-02 07:30:00","2016-04-02 07:31:00","2016-04-02 10:56:00","2016-04-02 10:56:00","2016-04-02 12:56:00","2016-04-02 12:56:00","2016-04-02 13:55:00","2016-04-02 13:55:00","2016-04-02 15:51:00","2016-04-02 15:51:00","2016-04-02 17:56:00","2016-04-02 17:56:00","2016-04-02 18:55:00","2016-04-02 18:55:00","2016-04-02 19:56:00","2016-04-02 19:56:00","2016-04-02 20:51:00","2016-04-02 20:51:00","2016-07-01 23:59:59"]}
     * errorCode : 00000
     * errorMsg : success
     */
    private String errorCode;
    private String errorMsg;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataEntity getData() {
        return data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public static class DataEntity {
        private List<String> dataList;
        private List<String> dateArray;

        public void setDataList(List<String> dataList) {
            this.dataList = dataList;
        }

        public void setDateArray(List<String> dateArray) {
            this.dateArray = dateArray;
        }

        public List<String> getDataList() {
            return dataList;
        }

        public List<String> getDateArray() {
            return dateArray;
        }
    }
}
