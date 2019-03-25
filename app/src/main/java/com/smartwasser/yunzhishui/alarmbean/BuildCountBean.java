package com.smartwasser.yunzhishui.alarmbean;

import com.smartwasser.yunzhishui.bean.RBResponse;

import java.util.List;

/**
 * Created by 15810 on 2019/3/25.
 */

public class BuildCountBean  extends RBResponse {
    /**
     * data : [{"dataDate":"2018-12-13","indeMap":{"A2174":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"1","indeCode":"A2174","indeValue":"10256","unitCode":"DC0001"},"A2175":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"8","indeCode":"A2175","indeValue":"85000","unitCode":"DC0001"},"A2200":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"2","indeCode":"A2200","indeValue":"11435","unitCode":"DC0001"},"A2219":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"3","indeCode":"A2219","indeValue":"12000","unitCode":"DC0001"},"A2233":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"4","indeCode":"A2233","indeValue":"10234","unitCode":"DC0001"},"A2244":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"5","indeCode":"A2244","indeValue":"8600","unitCode":"DC0001"},"A2248":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"6","indeCode":"A2248","indeValue":"9700","unitCode":"DC0001"},"A2280":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"7","indeCode":"A2280","indeValue":"92568","unitCode":"DC0001"}},"sumMap":{"sumValue":"62225.0"},"unitId":"DC0001"},{"dataDate":"2018-12-13","indeMap":{"A2174":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-13","id":"9","indeCode":"A2174","indeValue":"10100","unitCode":"DC0001"},"A2175":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-13","id":"16","indeCode":"A2175","indeValue":"85200","unitCode":"DC0001"},"A2200":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-13","id":"10","indeCode":"A2200","indeValue":"11235","unitCode":"DC0001"},"A2219":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-13","id":"11","indeCode":"A2219","indeValue":"12200","unitCode":"DC0001"},"A2233":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-13","id":"12","indeCode":"A2233","indeValue":"10135","unitCode":"DC0001"},"A2244":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-13","id":"13","indeCode":"A2244","indeValue":"8875","unitCode":"DC0001"},"A2248":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-13","id":"14","indeCode":"A2248","indeValue":"9620","unitCode":"DC0001"},"A2280":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-13","id":"15","indeCode":"A2280","indeValue":"91123","unitCode":"DC0001"}},"sumMap":{"sumValue":"62165.0"},"unitId":"DC0001"}]
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
         * dataDate : 2018-12-13
         * indeMap : {"A2174":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"1","indeCode":"A2174","indeValue":"10256","unitCode":"DC0001"},"A2175":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"8","indeCode":"A2175","indeValue":"85000","unitCode":"DC0001"},"A2200":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"2","indeCode":"A2200","indeValue":"11435","unitCode":"DC0001"},"A2219":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"3","indeCode":"A2219","indeValue":"12000","unitCode":"DC0001"},"A2233":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"4","indeCode":"A2233","indeValue":"10234","unitCode":"DC0001"},"A2244":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"5","indeCode":"A2244","indeValue":"8600","unitCode":"DC0001"},"A2248":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"6","indeCode":"A2248","indeValue":"9700","unitCode":"DC0001"},"A2280":{"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"7","indeCode":"A2280","indeValue":"92568","unitCode":"DC0001"}}
         * sumMap : {"sumValue":"62225.0"}
         * unitId : DC0001
         */

        private String dataDate;
        private IndeMapBean indeMap;
        private SumMapBean sumMap;
        private String unitId;

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

        public static class IndeMapBean {
            /**
             * A2174 : {"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"1","indeCode":"A2174","indeValue":"10256","unitCode":"DC0001"}
             * A2175 : {"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"8","indeCode":"A2175","indeValue":"85000","unitCode":"DC0001"}
             * A2200 : {"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"2","indeCode":"A2200","indeValue":"11435","unitCode":"DC0001"}
             * A2219 : {"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"3","indeCode":"A2219","indeValue":"12000","unitCode":"DC0001"}
             * A2233 : {"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"4","indeCode":"A2233","indeValue":"10234","unitCode":"DC0001"}
             * A2244 : {"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"5","indeCode":"A2244","indeValue":"8600","unitCode":"DC0001"}
             * A2248 : {"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"6","indeCode":"A2248","indeValue":"9700","unitCode":"DC0001"}
             * A2280 : {"createtime":"2019-02-26 11:56:42","dataDate":"2018-12-12","id":"7","indeCode":"A2280","indeValue":"92568","unitCode":"DC0001"}
             */

            private A2174Bean A2174;
            private A2175Bean A2175;
            private A2200Bean A2200;
            private A2219Bean A2219;
            private A2233Bean A2233;
            private A2244Bean A2244;
            private A2248Bean A2248;
            private A2280Bean A2280;

            public A2174Bean getA2174() {
                return A2174;
            }

            public void setA2174(A2174Bean A2174) {
                this.A2174 = A2174;
            }

            public A2175Bean getA2175() {
                return A2175;
            }

            public void setA2175(A2175Bean A2175) {
                this.A2175 = A2175;
            }

            public A2200Bean getA2200() {
                return A2200;
            }

            public void setA2200(A2200Bean A2200) {
                this.A2200 = A2200;
            }

            public A2219Bean getA2219() {
                return A2219;
            }

            public void setA2219(A2219Bean A2219) {
                this.A2219 = A2219;
            }

            public A2233Bean getA2233() {
                return A2233;
            }

            public void setA2233(A2233Bean A2233) {
                this.A2233 = A2233;
            }

            public A2244Bean getA2244() {
                return A2244;
            }

            public void setA2244(A2244Bean A2244) {
                this.A2244 = A2244;
            }

            public A2248Bean getA2248() {
                return A2248;
            }

            public void setA2248(A2248Bean A2248) {
                this.A2248 = A2248;
            }

            public A2280Bean getA2280() {
                return A2280;
            }

            public void setA2280(A2280Bean A2280) {
                this.A2280 = A2280;
            }

            @Override
            public String toString() {
                return "IndeMapBean{" +
                        "A2174=" + A2174 +
                        ", A2175=" + A2175 +
                        ", A2200=" + A2200 +
                        ", A2219=" + A2219 +
                        ", A2233=" + A2233 +
                        ", A2244=" + A2244 +
                        ", A2248=" + A2248 +
                        ", A2280=" + A2280 +
                        '}';
            }

            public static class A2174Bean {
                /**
                 * createtime : 2019-02-26 11:56:42
                 * dataDate : 2018-12-12
                 * id : 1
                 * indeCode : A2174
                 * indeValue : 10256
                 * unitCode : DC0001
                 */

                private String createtime;
                private String dataDate;
                private String id;
                private String indeCode;
                private String indeValue;
                private String unitCode;

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

                @Override
                public String toString() {
                    return "A2174Bean{" +
                            "createtime='" + createtime + '\'' +
                            ", dataDate='" + dataDate + '\'' +
                            ", id='" + id + '\'' +
                            ", indeCode='" + indeCode + '\'' +
                            ", indeValue='" + indeValue + '\'' +
                            ", unitCode='" + unitCode + '\'' +
                            '}';
                }
            }

            public static class A2175Bean {
                /**
                 * createtime : 2019-02-26 11:56:42
                 * dataDate : 2018-12-12
                 * id : 8
                 * indeCode : A2175
                 * indeValue : 85000
                 * unitCode : DC0001
                 */

                private String createtime;
                private String dataDate;
                private String id;
                private String indeCode;
                private String indeValue;
                private String unitCode;

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
            }

            public static class A2200Bean {
                /**
                 * createtime : 2019-02-26 11:56:42
                 * dataDate : 2018-12-12
                 * id : 2
                 * indeCode : A2200
                 * indeValue : 11435
                 * unitCode : DC0001
                 */

                private String createtime;
                private String dataDate;
                private String id;
                private String indeCode;
                private String indeValue;
                private String unitCode;

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
            }

            public static class A2219Bean {
                /**
                 * createtime : 2019-02-26 11:56:42
                 * dataDate : 2018-12-12
                 * id : 3
                 * indeCode : A2219
                 * indeValue : 12000
                 * unitCode : DC0001
                 */

                private String createtime;
                private String dataDate;
                private String id;
                private String indeCode;
                private String indeValue;
                private String unitCode;

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
            }

            public static class A2233Bean {
                /**
                 * createtime : 2019-02-26 11:56:42
                 * dataDate : 2018-12-12
                 * id : 4
                 * indeCode : A2233
                 * indeValue : 10234
                 * unitCode : DC0001
                 */

                private String createtime;
                private String dataDate;
                private String id;
                private String indeCode;
                private String indeValue;
                private String unitCode;

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
            }

            public static class A2244Bean {
                /**
                 * createtime : 2019-02-26 11:56:42
                 * dataDate : 2018-12-12
                 * id : 5
                 * indeCode : A2244
                 * indeValue : 8600
                 * unitCode : DC0001
                 */

                private String createtime;
                private String dataDate;
                private String id;
                private String indeCode;
                private String indeValue;
                private String unitCode;

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
            }

            public static class A2248Bean {
                /**
                 * createtime : 2019-02-26 11:56:42
                 * dataDate : 2018-12-12
                 * id : 6
                 * indeCode : A2248
                 * indeValue : 9700
                 * unitCode : DC0001
                 */

                private String createtime;
                private String dataDate;
                private String id;
                private String indeCode;
                private String indeValue;
                private String unitCode;

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
            }

            public static class A2280Bean {
                /**
                 * createtime : 2019-02-26 11:56:42
                 * dataDate : 2018-12-12
                 * id : 7
                 * indeCode : A2280
                 * indeValue : 92568
                 * unitCode : DC0001
                 */

                private String createtime;
                private String dataDate;
                private String id;
                private String indeCode;
                private String indeValue;
                private String unitCode;

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
            }
        }

        public static class SumMapBean {
            /**
             * sumValue : 62225.0
             */

            private String sumValue;

            public String getSumValue() {
                return sumValue;
            }

            public void setSumValue(String sumValue) {
                this.sumValue = sumValue;
            }
        }
    }
}
