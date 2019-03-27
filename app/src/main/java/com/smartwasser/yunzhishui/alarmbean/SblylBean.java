package com.smartwasser.yunzhishui.alarmbean;

import com.smartwasser.yunzhishui.bean.RBResponse;

import java.util.List;

/**
 * Name: SblylBean
 * Author: Administrator
 * Comment: //TODO
 * Date: 2019-03-26 13:04
 */
public class SblylBean extends RBResponse {

    /**
     * data : [{"dataDate":"2018-01","indeMap":{"col1":"0.86","col10":"0.86","col11":"0.88","col12":"0.86","col2":"0.86","col3":"0.86","col4":"0.83","col5":"0.88","col6":"0.85","col7":"0.84","col8":"0.89","col9":"0.82"},"sumMap":{"avgValue":"0.858"},"unitId":"CZWS0622","unitName":"第一污水处理厂一分厂"},{"dataDate":"2018-01","indeMap":{"col1":"0.85","col10":"0.82","col11":"0.81","col12":"0.85","col2":"0.82","col3":"0.83","col4":"0.81","col5":"0.84","col6":"0.86","col7":"0.87","col8":"0.83","col9":"0.84"},"sumMap":{"avgValue":"0.836"},"unitId":"DC0001","unitName":"铁岭发电厂"}]
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
         * dataDate : 2018-01
         * indeMap : {"col1":"0.86","col10":"0.86","col11":"0.88","col12":"0.86","col2":"0.86","col3":"0.86","col4":"0.83","col5":"0.88","col6":"0.85","col7":"0.84","col8":"0.89","col9":"0.82"}
         * sumMap : {"avgValue":"0.858"}
         * unitId : CZWS0622
         * unitName : 第一污水处理厂一分厂
         */

        private String dataDate;
        private IndeMapBean indeMap;
        private SumMapBean sumMap;
        private String unitId;
        private String unitName;

        public String getDataDate() {
            return dataDate;
        }

        public void setDataDate(String dataDate) {
            this.dataDate = dataDate;
        }

        public IndeMapBean getIndeMap() {
            return indeMap;
        }

        public void setIndeMap(IndeMapBean indeMap) {
            this.indeMap = indeMap;
        }

        public SumMapBean getSumMap() {
            return sumMap;
        }

        public void setSumMap(SumMapBean sumMap) {
            this.sumMap = sumMap;
        }

        public String getUnitId() {
            return unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public static class IndeMapBean {
            /**
             * col1 : 0.86
             * col10 : 0.86
             * col11 : 0.88
             * col12 : 0.86
             * col2 : 0.86
             * col3 : 0.86
             * col4 : 0.83
             * col5 : 0.88
             * col6 : 0.85
             * col7 : 0.84
             * col8 : 0.89
             * col9 : 0.82
             */

            private String col1;
            private String col10;
            private String col11;
            private String col12;
            private String col2;
            private String col3;
            private String col4;
            private String col5;
            private String col6;
            private String col7;
            private String col8;
            private String col9;

            public String getCol1() {
                return col1;
            }

            public void setCol1(String col1) {
                this.col1 = col1;
            }

            public String getCol10() {
                return col10;
            }

            public void setCol10(String col10) {
                this.col10 = col10;
            }

            public String getCol11() {
                return col11;
            }

            public void setCol11(String col11) {
                this.col11 = col11;
            }

            public String getCol12() {
                return col12;
            }

            public void setCol12(String col12) {
                this.col12 = col12;
            }

            public String getCol2() {
                return col2;
            }

            public void setCol2(String col2) {
                this.col2 = col2;
            }

            public String getCol3() {
                return col3;
            }

            public void setCol3(String col3) {
                this.col3 = col3;
            }

            public String getCol4() {
                return col4;
            }

            public void setCol4(String col4) {
                this.col4 = col4;
            }

            public String getCol5() {
                return col5;
            }

            public void setCol5(String col5) {
                this.col5 = col5;
            }

            public String getCol6() {
                return col6;
            }

            public void setCol6(String col6) {
                this.col6 = col6;
            }

            public String getCol7() {
                return col7;
            }

            public void setCol7(String col7) {
                this.col7 = col7;
            }

            public String getCol8() {
                return col8;
            }

            public void setCol8(String col8) {
                this.col8 = col8;
            }

            public String getCol9() {
                return col9;
            }

            public void setCol9(String col9) {
                this.col9 = col9;
            }
        }

        public static class SumMapBean {
            /**
             * avgValue : 0.858
             */

            private String avgValue;

            public String getAvgValue() {
                return avgValue;
            }

            public void setAvgValue(String avgValue) {
                this.avgValue = avgValue;
            }
        }
    }
}
