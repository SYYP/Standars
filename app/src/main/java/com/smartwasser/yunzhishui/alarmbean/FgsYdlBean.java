package com.smartwasser.yunzhishui.alarmbean;

import com.smartwasser.yunzhishui.bean.RBResponse;

import java.util.List;

/**
 * Name: FgsYdlBean
 * Author: Administrator
 * Comment: //TODO
 * Date: 2019-03-26 14:04
 */
public class FgsYdlBean extends RBResponse {

    /**
     * data : [{"createtime":"2019-02-20 14:51:01","dataDate":"2014","id":"1","indeCode":"A000001","indeValue":"300000","unitCode":"DC0001"},{"createtime":"2019-02-20 14:51:01","dataDate":"2015","id":"2","indeCode":"A000001","indeValue":"350000","lyDiffValue":"50000.0","lyValue":"300000","unitCode":"DC0001"},{"createtime":"2019-02-20 14:51:01","dataDate":"2016","id":"3","indeCode":"A000001","indeValue":"380000","lyDiffValue":"30000.0","lyValue":"350000","unitCode":"DC0001"},{"createtime":"2019-02-20 14:51:01","dataDate":"2017","id":"4","indeCode":"A000001","indeValue":"370000","lyDiffValue":"-10000.0","lyValue":"380000","unitCode":"DC0001"},{"createtime":"2019-02-20 14:51:01","dataDate":"2018","id":"5","indeCode":"A000001","indeValue":"400000","lyDiffValue":"30000.0","lyValue":"370000","unitCode":"DC0001"}]
     * errorCode : 00000
     * errorMsg : success
     */

    private String errorCode;
    private String errorMsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2019-02-20 14:51:01
         * dataDate : 2014
         * id : 1
         * indeCode : A000001
         * indeValue : 300000
         * unitCode : DC0001
         * lyDiffValue : 50000.0
         * lyValue : 300000
         */

        private String createtime;
        private String dataDate;
        private String id;
        private String indeCode;
        private String indeValue;
        private String unitCode;
        private String lyDiffValue;
        private String lyValue;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDataDate() {
            return dataDate;
        }

        public void setDataDate(String dataDate) {
            this.dataDate = dataDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIndeCode() {
            return indeCode;
        }

        public void setIndeCode(String indeCode) {
            this.indeCode = indeCode;
        }

        public String getIndeValue() {
            return indeValue;
        }

        public void setIndeValue(String indeValue) {
            this.indeValue = indeValue;
        }

        public String getUnitCode() {
            return unitCode;
        }

        public void setUnitCode(String unitCode) {
            this.unitCode = unitCode;
        }

        public String getLyDiffValue() {
            return lyDiffValue;
        }

        public void setLyDiffValue(String lyDiffValue) {
            this.lyDiffValue = lyDiffValue;
        }

        public String getLyValue() {
            return lyValue;
        }

        public void setLyValue(String lyValue) {
            this.lyValue = lyValue;
        }
    }
}
