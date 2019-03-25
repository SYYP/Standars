package com.smartwasser.yunzhishui.utils;

/**
 * Created by huangmengjie on 2016/4/27 0027.
 */
public class ConstantsYunZhiShui {
    /**
     * 新接口
     **/
    public static final String HTTP_URL = "http://ghce.5166.info:9180/bzjc/";
    public static final String BJCX = HTTP_URL + "appsso/alarm/realAlramDataSearch";
    public static final int REQUEST_CODE_BJQUERY = 150;
    /**
     *  历史报警查询
     */
    public static final String HISTORY_BJ_QUERY = HTTP_URL + "appsso/alarm/ hisAlramDataSearch";
    public static final int REQUEST_CODE_HISTORY_QUERY = 151;
    /**
     * 数据填报系统
     */
    public static final String URL_SERVER = "http://www.yunzhishui.com/scsj/appsso/";
    /**
     * 登录页请求
     */
    public static final String URL_LOGIN = "http://ghce.5166.info:9180/bzjc/appsso/login/";
//      public static final String URL_LOGIN="http://www.yunzhishui.com/portal/appsso/login/";
    /**
     * 登录页请求码
     */
    public static final int REQUEST_CODE_LOGIN = 0;
    /**
     * 修改密码页
     */
    public static final String URL_FORGETPASSWORD = URL_SERVER + "updateUser/";
    /**
     * 修改密码页请求码
     */
    public static final int REQUEST_CODE_FORGETPASSWORD = 1;
    /**
     * 生产数据首页
     */
    public static final String URL_DATAMAIN = URL_SERVER + "info/";
    /**
     * 生产数据首页请求码
     */
    public static final int REQUEST_CODE_DATAMAIN = 2;
    /**
     * 通告栏详情页
     */
    public static final String URL_TONGGAO = URL_SERVER + "view?";
    /**
     * 生产数据首页左侧菜单栏
     */
    public static final String URL_SITE = URL_SERVER + "/dh/menuList/";
    /**
     * 生产数据首页左侧菜单栏请求码
     */
    public static final int REQUEST_CODE_SITE = 3;
    /**
     * 水厂日报帮助添加页面的uRL
     */
    public static final String URL_DAILY = URL_SERVER + "daily/toList/";
    public static final int REQUEST_CODE_DAILY = 5;
    /**
     * 水厂日报的URL
     */
    public static final String URL_DAILYLIST = URL_SERVER + "daily/dailyList/";
    public static final int REQUEST_CODE_DAILYLIST = 6;
    /**
     * 数据上报添加的接口
     */
    public static final String URL_MASTERlIST = URL_SERVER + "daily/masterList/";
    public static final int REQUEST_CODE_MASTERlIST = 7;
    /**
     * 权限接口的URL
     */
    public static final String URL_HASADD = URL_SERVER + "daily/hasAdd/";
    public static final int REQUEST_CODE_HASADD = 8;
    /**
     * 下拉框填报界面
     */
    public static final String URL_ADDUNit = URL_SERVER + "daily/addUnit/";
    public static final int REQUEST_CODE_ADDUNit = 9;
    /**
     * 保存的接口
     */
    public static final String URL_SAVE = URL_SERVER + "daily/daliySave/";
    public static final int REQUEST_CODE_SAVE = 10;
    /**
     * 详情页接口
     */
    public static final String URL_LOOKDATA = URL_SERVER + "daily/indeMasterView/";
    public static final int REQUEST_CODE_LOOKDATA = 11;
    /**
     * 如果有修改权限就调用这个接口
     */
    public static final String URL_TOEDIT = URL_SERVER + "daily/toEdit/";
    public static final int REQUEST_CODE_TOEDIT = 12;
    /**
     * 修改显示界面接口
     */
    public static final String URL_AMEND = URL_SERVER + "daily/indeMasterEdit/";
    public static final int REQUEST_CODE_AMEND = 13;
    /**
     * 审核界面接口
     */
    public static final String URL_AUDIT = URL_SERVER + "daily/indeMasterCheck/";
    public static final int REQUEST_CODE_AUDIT = 14;
    /**
     * 审核提交接口
     */
    public static final String URL_AUDIT_SUBMIT = URL_SERVER + "daily/doCheck/";
    public static final int REQUEST_CODE_AUDIT_SUBMIT = 15;
    /**
     * 修改保存的接口
     */
    public static final String URL_UPDATA_SAVE = URL_SERVER + "daily/update/";
    public static final int REQUEST_CODE_UPDATA_SAVE = 16;
    /**
     * 修改提交的接口
     */
    public static final String URL_UPDATA_SUBMIT = URL_SERVER + "daily/sub/";
    public static final int REQUEST_CODE_UPDATA_SUBMIT = 17;
    /**
     * 删除接口
     */
    public static final String URL_DELETE = URL_SERVER + "daily/delete/";
    public static final int REQUEST_CODE_DELETE = 18;
    /**
     * 搜索中单位的接口
     */
    public static final String URL_FINDBUCOM = URL_SERVER + "daily/findBuComboxList/";
    public static final int REQUEST_CODE_FINDBUCOM = 19;
    /**
     * 按指标查询
     */
    public static final String URL_INDEFIND = URL_SERVER + "Query/indeFind/";
    public static final int REQUEST_CODE_INDEFIND = 20;
    /**
     * 按指标查询联想
     */
    public static final String URL_LXINDE = URL_SERVER + "Query/lxInde/";
    public static final int REQUEST_CODE_LXINDE = 21;
    /**
     * 按指标组查询
     */
    public static final String URL_INDEGROPFIND = URL_SERVER + "Query/indegrpFind/";
    public static final int REQUEST_CODE_INDEGROPFIND = 22;
    /**
     * 按指标组查询联想
     */
    public static final String URL_LXGROPINDE = URL_SERVER + "Query/lxIndeGrp/";
    public static final int REQUEST_CODE_LXGROPINDE = 23;
    /**
     * 单指标历史同期对比
     */
    public static final String URL_DANZLS = URL_SERVER + "Query/danzls/";
    public static final int REQUEST_CODE_DANZLS = 24;
    /**
     * 单指标日数据对比
     */
    public static final String URL_DCZBRI = URL_SERVER + "Query/dczbri/";
    public static final int REQUEST_CODE_DCZBRI = 25;
    /**
     * 多厂站单指标趋势对比
     */
    public static final String URL_DCDANZ = URL_SERVER + "Query/Dcdanz/";
    public static final int REQUEST_CODE_DCDANZ = 26;

    /**
     * 设备管理系统
     */
    public static final String URL_SERVERs = "http://www.yunzhishui.com/sbgl/appsso/";
    /**
     * 设备管理导航
     */
    public static final String URL_SBGLMENULIST = URL_SERVERs + "dh/menuList/";
    public static final int REQUEST_CODE_SBGLMENULIST = 209;
    /**
     * 保养工单列表
     */
    public static final String URL_GDLB = URL_SERVERs + "eqMaintain/list/";
    public static final int REQUEST_CODE_GDLB = 29;
    /**
     * 工单添加
     */
    public static final String URL_GDADD = URL_SERVERs + "eqMaintain/save/";
    public static final int REQUEST_CODE_GDADD = 30;
    /**
     * 工单修改回显
     */
    public static final String URL_GDUPDATAHX = URL_SERVERs + "eqMaintain/typeUpdate/";
    public static final int REQUEST_CODE_GDUPDATAHX = 31;
    /**
     * 工单修改保存
     */
    public static final String URL_GDUPDATABC = URL_SERVERs + "eqMaintain/update/";
    public static final int REQUEST_CODE_GDUPDATABC = 32;
    /**
     * 工单删除
     */
    public static final String URL_GDDELETE = URL_SERVERs + "eqMaintain/delete/";
    public static final int REQUEST_CODE_GDDELETE = 33;
    /**
     * 工单明细列表
     */
    public static final String URL_GDMXLIST = URL_SERVERs + "eqMaintain/gdmxList/";
    public static final int REQUEST_CODE_GDMXLIST = 34;
    /**
     * 工单明细删除
     */
    public static final String URL_GDMXDELETE = URL_SERVERs + "eqMaintain/gdmxDelete/";
    public static final int REQUEST_CODE_GDMXDELETE = 35;
    /**
     * 工单明细保存
     */
    public static final String URL_GDMXSAVE = URL_SERVERs + "eqMaintain/gdmxSave/";
    public static final int REQUEST_CODE_GDMXSAVE = 60;
    /**
     * 工单明细修改回显
     */
    public static final String URL_GDMXUPSATEHX = URL_SERVERs + "eqMaintain/gdmxTypeUpdate/";
    public static final int REQUEST_CODE_GDMXUPSATEHX = 36;
    /**
     * 工单明细修改保存
     */
    public static final String URL_GDMXUPDATE = URL_SERVERs + "eqMaintain/gdmxUpdate/";
    public static final int REQUEST_CODE_GDMXUPDATE = 37;
    /**
     * 设备管理工单派发
     */
    public static final String URL_GDPF = URL_SERVERs + "eqMaintain/gdpf/";
    public static final int REQUEST_CODE_GDPF = 27;
    /**
     * 设备工单反馈
     */
    public static final String URL_GDFK = URL_SERVERs + "eqMaintain/gdfk/";
    public static final int REQUEST_CODE_GDFK = 28;


    /**
     * 设备故障列表
     */
    public static final String URL_SBGZLIST = URL_SERVERs + "eqFailure/list/";
    public static final int REQUEST_CODE_SBGZLIST = 38;
    /**
     * 故障类别
     */
    public static final String URL_SBGZLB = URL_SERVERs + "eqFailure/gzbwfindList/";
    public static final int REQUEST_CODE_SBGZLB = 39;
    /**
     * 故障添加保存
     */
    public static final String URL_SBGZSAVE = URL_SERVERs + "eqFailure/save/";
    public static final int REQUEST_CODE_SBGZSAVE = 40;
    /**
     * 设备台站列表
     */
    public static final String URL_SBLEGERLIST = URL_SERVERs + "equipInfo/list/";
    public static final int REQUEST_CODE_SBLEGERLIST = 41;
    /**
     * 设备类别
     */
    public static final String URL_SBLB = URL_SERVERs + "equipInfo/findEqTypeList/";
    public static final int REQUEST_CODE_SBLB = 42;
    /**
     * 所属单位
     */
    public static final String URL_SBSSDW = URL_SERVERs + "unitList/findBuComboxList/";
    public static final int REQUEST_CODE_SBSSDW = 43;
    /**
     * 各部位
     */
    public static final String URL_SBBUWEI = URL_SERVERs + "equipInfo/typeMasterComboxSelector/";
    public static final int REQUEST_CODE_SBBUWEI = 44;
    /**
     * 故障现象
     */
    public static final String URL_SBGZXX = URL_SERVERs + "eqFailure/findEqTroubleAppear/";
    public static final int REQUEST_CODE_SBGZXX = 45;
    /**
     * 故障确认和未确认
     */
    public static final String URL_SBGZQRW = URL_SERVERs + "/eqFailure/typeUpdate/";
    public static final int REQUEST_CODE_SBGZQRW = 46;
    /**
     * 故障删除
     */
    public static final String URL_SBGZDELETE = URL_SERVERs + "/eqFailure/delete/";
    public static final int REQUEST_CODE_SBGZDELETE = 47;
    /**
     * 故障确认
     */
    public static final String URL_SBGZQR = URL_SERVERs + "/eqFailure/gzqr/";
    public static final int REQUEST_CODE_SBGZQR = 48;
    /**
     * 修改保存
     */
    public static final String URL_SBGZUPDATESAVE = URL_SERVERs + "/eqFailure/update/";
    public static final int REQUEST_CODE_SBGZUPDATESAVE = 49;

    /**
     * 设备台账添加保存
     */
    public static final String URL_LEDGERSAVE = URL_SERVERs + "/equipInfo/save/";
    public static final int REQUEST_CODE_LEDGERSAVE = 50;

    /**
     * 设备台账点击item回显接口
     */
    public static final String URL_LEDGERINFO = URL_SERVERs + "/equipInfo/typeUpdate/";
    public static final int REQUEST_CODE_LEDGERINFO = 51;
    /**
     * equipInfo/delete
     */
    public static final String URL_LEDGERDELETE = URL_SERVERs + "/equipInfo/delete/";
    public static final int REQUEST_CODE_LEDGERDELETE = 52;


    /**
     * 污泥运输
     */
    public static final String URL_SERVERss = "http://www.yunzhishui.com/wnysgl/appsso/";
    /**
     * 列表页
     */
    public static final String URL_TRANPORTMENULIST = URL_SERVERss + "dh/menuList/";
    public static final int REQUEST_CODE_TRANPORTMENULIST = 202;
    /**
     * 数据称重
     */
    public static final String URL_TRANPORTINFO = URL_SERVERss + "/transportInfo/find/";
    public static final int REQUEST_CODE_TRANPORTINFO = 53;
    /**
     * 删除
     */
    public static final String URL_TRANPORTDELETE = URL_SERVERss + "/transportInfo/delete/";
    public static final int REQUEST_CODE_TRANPORTDELETE = 54;
    /**
     * w外运统计修改回显
     */
    public static final String URL_TRANPORTTOUPDATE = URL_SERVERss + "/transportInfo/toupdate/";
    public static final int REQUEST_CODE_TRANPORTTOUPDATE = 55;
    /**
     * 修改保存
     */
    public static final String URL_TRANPORTUPDATE = URL_SERVERss + "/transportInfo/update/";
    public static final int REQUEST_CODE_TRANPORTUPDATE = 56;
    /**
     * 称重点
     */
    public static final String URL_TRANPORTPOINT = URL_SERVERss + "/transporttask/WeightPointManagement/";
    public static final int REQUEST_CODE_TRANPORTPOINT = 57;
    /**
     * 污泥种类
     */
    public static final String URL_TRANPORTTYPECOM = URL_SERVERss + "/transportInfo/WnTypeCom/";
    public static final int REQUEST_CODE_TRANPORTTYPECOM = 58;
    /**
     * 称重添加保存
     */
    public static final String URL_TRANPORTTOSAVE = URL_SERVERss + "/transportInfo/tosave/";
    public static final int REQUEST_CODE_TRANPORTTOSAVE = 59;
    /**
     * 车组
     */
    public static final String URL_TRANPORTGROUP = URL_SERVERss + "/transportInfo/GroupInfo/";
    public static final int REQUEST_CODE_TRANPORTGROUP = 60;
    /**
     * 车辆
     */
    public static final String URL_TRANPORTCAR = URL_SERVERss + "/transportInfo/findcl/";
    public static final int REQUEST_CODE_TRANPORTCAR = 61;

    /**
     * 运输任务
     */
    public static final String URL_TRANPORTTASK = URL_SERVERss + "/transporttask/find/";
    public static final int REQUEST_CODE_TRANPORTTASK = 62;
    /**
     * 处置点
     */
    public static final String URL_TRANPORTMUD = URL_SERVERss + "/transporttask/mudList/";
    public static final int REQUEST_CODE_TRANPORTMUD = 63;
    /**
     * 运输任务详情
     */
    public static final String URL_TRANPORTVIEW = URL_SERVERss + "/transporttask/view/";
    public static final int REQUEST_CODE_TRANPORTVIEW = 64;
    /**
     * 实时监控
     */
    public static final String URL_TRANPORTREALTIME = URL_SERVERss + "/zjjk/realTimeData/json/";
    public static final int REQUEST_CODE_TRANPORTREALTIME = 65;
    /**
     * 主机监控
     */
    public static final String URL_TRANPORTMAIN = URL_SERVERss + "/zjjk/main/";
    public static final int REQUEST_CODE_TRANPORTMAIN = 66;
    /**
     * 轨迹
     */
    public static final String URL_TRANPORTMULTI = URL_SERVERss + "/gjcx/multi/json/";
    public static final int REQUEST_CODE_TRANPORTMULTI = 67;
    /**
     * 轨迹重现
     */
    public static final String URL_TRANPORTGJCX = URL_SERVERss + "/gjcx/list/";
    public static final int REQUEST_CODE_TRANPORTGJCX = 68;

    /**
     * 车辆监控
     */
    public static final String URL_TRANPORTCLJK = URL_SERVERss + "/zjjk/getRealTimeData/";
    public static final int REQUEST_CODE_TRANPORTCLJK = 69;
    /**
     * 历史轨迹
     */
    public static final String URL_TRANPORTLSGJ = URL_SERVERss + "/transporttask/history/";
    public static final int REQUEST_CODE_TRANPORTLSGJ = 70;


    /**
     * 生产成本
     */
    public static final String URL_SERVERsss = "http://www.yunzhishui.com/cbgl/appsso/";
    /**
     * 一级列表
     */
    public static final String URL_TYPEMASTERSITE = URL_SERVERsss + "/dh/menuList/";
    public static final int REQUEST_CODE_TYPEMASTERSITE = 71;
    /**
     * 费用报销 列表
     */
    public static final String URL_TYPEMASTERFILLFIND = URL_SERVERsss + "/pay/fillFind/";
    public static final int REQUEST_CODE_TYPEMASTERFILLFIND = 73;
    /**
     * 费用报销 进入列表前传参
     **/
    public static final String URL_TYPEMASTERLIST = URL_SERVERsss + "/pay/list/";
    public static final int REQUEST_CODE_TYPEMASTERLIST = 74;
    /**
     * 各个类型报
     */
    public static final String URL_TYPEMASTERSELECT = URL_SERVERsss + "/ComboboxApp/typeMasterComboxSelector/";
    public static final int REQUEST_CODE_TYPEMASTERSELECT = 75;
    /**
     * 费用报销详情页
     */
    public static final String URL_TYPEMASTERPAYVIEW = URL_SERVERsss + "/pay/view/";
    public static final int REQUEST_CODE_TYPEMASTERPAYVIEW = 76;
    /**
     * 项目名称
     */
    public static final String URL_TYPEMASTERPAYBOXLIST = URL_SERVERsss + "/ComboboxApp/findBuComboxList/";
    public static final int REQUEST_CODE_TYPEMASTERPAYPAYBOXLIST = 77;
    /**
     * 申请进度
     */
    public static final String URL_TYPEMASTERSCHEDULE = URL_SERVERsss + "/ComboboxApp/OperationNode/";
    public static final int REQUEST_CODE_TYPEMASTERPAYSCHEDULE = 78;
    /***审核状态显示*/
    public static final String URL_TYPEMASTERREVIEW = URL_SERVERsss + "/pay/findReview/";
    public static final int REQUEST_CODE_TYPEMASTERREVIEW = 79;
    /***审核状态显示*/
    public static final String URL_TYPEMASTERCOMMIT = URL_SERVERsss + "/pay/tj/";
    public static final int REQUEST_CODE_TYPEMASTERCOMMIT = 80;
    /***审核添加保存*/
    public static final String URL_TYPEMASTERSAVE = URL_SERVERsss + "/pay/save/";
    public static final int REQUEST_CODE_TYPEMASTERSAVE = 81;
    /**
     * 费用报销修改
     */
    public static final String URL_TYPEMASTERUPDATE = URL_SERVERsss + "/pay/update/";
    public static final int REQUEST_CODE_TYPEMASTERUPDATE = 210;
    /***经理审核*/
    public static final String URL_TYPEMASTERREVIEWs = URL_SERVERsss + "/pay/review/";
    public static final int REQUEST_CODE_TYPEMASTERREVIEWs = 100;
    /**
     * 业务科目校验
     */
    public static final String URL_TYPEMASTERYWKMJY = URL_SERVERsss + "/pay/isMasterFill/";
    public static final int REQUEST_CODE_TYPEMASTERYWKMJY = 81;
    /**
     * 进入添加页之前调的接口
     */
    public static final String URL_TYPEMASTERADDTO = URL_SERVERsss + "/pay/addto/";
    public static final int REQUEST_CODE_TYPEMASTERADDTO = 82;
    /**
     * 业务科目校验成功,展示列表的接口
     */
    public static final String URL_TYPEMASTERADDTOINDE = URL_SERVERsss + "/pay/addtoinde/";
    public static final int REQUEST_CODE_TYPEMASTERADDTOINDE = 83;
    /**
     * 职能部门
     **/
    public static final String URL_TYPEMASTERZNBM = URL_SERVERsss + "/ComboboxApp/findZn/";
    public static final int REQUEST_CODE_TYPEMASTERZNBM = 101;
    /**
     * 职能部门审核
     */
    public static final String URL_TYPEMASTERZNBMFIND = URL_SERVERsss + "/pay/znbmFind/";
    public static final int REQUEST_CODE_TYPEMASTERZNBMFIND = 102;
    /**
     * 分管领导
     */
    public static final String URL_TYPEMASTERFGLDFIND = URL_SERVERsss + "/pay/fgldFind/";
    public static final int REQUEST_CODE_TYPEMASTERFGLDFIND = 103;
    /**
     * 收款通知
     */
    public static final String URL_TYPEMASTERFEED = URL_SERVERsss + "/fee/find/";
    public static final int REQUEST_CODE_TYPEMASTERFEED = 91;
    /**
     * 合同类型
     */
    public static final String URL_TYPEMASTERTYPEMASTER = URL_SERVERsss + "/ComboboxApp/typeMasterComboxSelector/";
    public static final int REQUEST_CODE_TYPEMASTERTYPEMASTER = 92;

    /**
     * 收款通知详情
     */
    public static final String URL_TYPEMASTERSKFIND = URL_SERVERsss + "/fee/skfind/";
    public static final int REQUEST_CODE_TYPEMASTERSKFIND = 93;
    /**
     * 收款通知分项列表
     */
    public static final String URL_TYPEMASTERFINDFEESK = URL_SERVERsss + "/fee/findFeeSk/";
    public static final int REQUEST_CODE_TYPEMASTERFINDFEESK = 94;
    /**
     * 收款通知分项详情
     */
    public static final String URL_TYPEMASTERVIEWSK = URL_SERVERsss + "/fee/viewSk/";
    public static final int REQUEST_CODE_TYPEMASTERVIEWSK = 95;
    /**
     * 业务科目
     */
    public static final String URL_TYPEMASTERMASTBYID = URL_SERVERsss + "/fee/findindeMasterById/";
    public static final int REQUEST_CODE_TYPEMASTERMASTBYID = 96;
    /**
     * 分项提交
     */
    public static final String URL_TYPEMASTERFEETJ = URL_SERVERsss + "/fee/tj/";
    public static final int REQUEST_CODE_TYPEMASTERFEETJ = 97;
    /**
     * 支付分项列表
     */
    public static final String URL_TYPEMASTERFINDFEEZF = URL_SERVERsss + "/fee/findFeeZf/";
    public static final int REQUEST_CODE_TYPEMASTERFINDFEEZF = 98;
    /**
     * 支付分项提交
     */
    public static final String URL_TYPEMASTERFEETJZF = URL_SERVERsss + "/fee/tjZf/";
    public static final int REQUEST_CODE_TYPEMASTERFEETJZF = 99;

    /**
     * 支付分项详情
     */
    public static final String URL_TYPEMASTERVIEWZF = URL_SERVERsss + "/fee/viewZf/";
    public static final int REQUEST_CODE_TYPEMASTERVIEWZF = 99;
    /**
     * 收款通知添加保存
     */
    public static final String URL_TYPEMASTERSAVESK = URL_SERVERsss + "/fee/saveSk";
    public static final int REQUEST_CODE_TYPEMASTERSAVESK = 123;
    /**
     * 收款通知修改
     */
    public static final String URL_TYPEMASTERUPDATESk = URL_SERVERsss + "/fee/saveUpdataSk";
    public static final int REQUEST_CODE_TYPEMASTERUPDATESk = 211;
    /**
     * 支付添加保存
     */

    public static final String URL_TYPEMASTERSAVEZF = URL_SERVERsss + "/fee/saveZF";
    public static final int REQUEST_CODE_TYPEMASTERSAVEZF = 212;
    /**
     * 支付编辑保存
     */
    public static final String URL_TYPEMASTERUPDATEZF = URL_SERVERsss + "/fee/saveUpdataZf";
    public static final int REQUEST_CODE_TYPEMASTERUPDATEZF = 213;


    /**
     * 防汛系统
     */
    public static final String URL_SERVERssss = "http://www.yunzhishui.com/fxxt/appsso/";
    /**
     * 一级列表
     */
    public static final String URL_FXXTSITE = URL_SERVERssss + "/dh/menuList/";
    public static final int REQUEST_CODE_FXXTSITE = 84;
    /**
     * 雨量监测
     */
    public static final String URL_FXXTRAIN = URL_SERVERssss + "/rain/ylRealTimeData/";
    public static final int REQUEST_CODE_FXXTRAIN = 86;
    /**
     * 点击雨点弹出pop
     */
    public static final String URL_FXXTLSCXRAIN = URL_SERVERssss + "/lscx/ylcxFind/";
    public static final int REQUEST_CODE_FXXTLSCXRAIN = 213;
    /**
     * 雨强查询
     */
    public static final String URL_FXXTYQCXRAIN = URL_SERVERssss + "/lscx/yqcxFind/";
    public static final int REQUEST_CODE_FXXTYQCXRAIN = 214;
    /**
     * 泵站查询
     */
    public static final String URL_FXXTBZRAIN = URL_SERVERssss + "/lscx/bzFind/";
    public static final int REQUEST_CODE_FXXTBZRAIN = 215;
    /**
     * 雨强
     */
    public static final String URL_FXXTRAINQIANG = URL_SERVERssss + "/rain/yqRealTimeDataForKdb/";
    public static final int REQUEST_CODE_FXXTRAINQIANG = 87;
    /**
     * 雨量排行
     */
    public static final String URL_FXXTRAINTOP = URL_SERVERssss + "/rain/rainTop/";
    public static final int REQUEST_CODE_FXXTRAINTOP = 88;
    /**
     * 场雨排行
     */
    public static final String URL_FXXTRAINCITYTOP = URL_SERVERssss + "/rain/cityTop/";
    public static final int REQUEST_CODE_FXXTRAINCITYTOP = 89;
    /**
     * 场雨监测
     */
    public static final String URL_FXXTRAINCITYJSON = URL_SERVERssss + "/rain/cityJson/";
    public static final int REQUEST_CODE_FXXTRAINCITYJSON = 90;
    /**
     * 调蓄池
     */
    public static final String URL_FXXTRAINPUMPFOR = URL_SERVERssss + "/rain/pumpForGz/";
    public static final int REQUEST_CODE_FXXTRAINPUMPFOR = 91;
    /**
     * 初期池
     */
    public static final String URL_FXXTRAINPUMPFORCQ = URL_SERVERssss + "/rain/pumpForCq/";
    public static final int REQUEST_CODE_FXXTRAINPUMPFORCQ = 92;
    /**
     * 泵站
     */
    public static final String URL_FXXTRAINPUMP = URL_SERVERssss + "/rain/pump/";
    public static final int REQUEST_CODE_FXXTRAINPUMP = 93;


    /**
     * 远程监控
     */
//    public static final String URL_SERVERssssss="http://www.yunzhishui.com/zxjc/appsso/";
    public static final String URL_SERVERssssss = "http://ghce.5166.info:9180/bzjc/appsso";
    public static final String BASE_URL = "http://ghce.5166.info:9180/bzjc/appsso";
    /**
     * 导航
     */
    public static final String URL_ZXJCMENULIST = BASE_URL + "/dh/menuList/";
    public static final int REQUEST_CODE_ZXJCMENULIST = 106;
    /**
     * 清河web页
     */
    public static final String URL_ZXJCMONITOR = URL_SERVERssssss;
    /**
     * 单位
     */
    public static final String URL_ZXJCBUSINESS = URL_SERVERssssss + "/ComboboxApp/findBuComboxList/";
    public static final int REQUEST_CODE_ZXJCBUSINESS = 107;
    /**
     * 构建物
     */
    public static final String URL_ZXJCBUILDING = URL_SERVERssssss + "/ComboboxApp/getBuildCodeCombobox/";
    public static final int
            REQUEST_CODE_ZXJCBUILDING = 108;
    /**
     * 指标
     **/
    public static final String URL_ZXJCQUOTA = URL_SERVERssssss + "/ComboboxApp/getBEquipIndeCodeCombobox/";
    public static final int REQUEST_CODE_ZXJCQUOTA = 109;
    /**
     * 运行数据查询
     */
    public static final String URL_ZXJCRUNDATA = URL_SERVERssssss + "/historySearch/runningDataSearch/";
    public static final int REQUEST_CODE_ZXJCRUNDATA = 110;
    /**
     * 设备运行状态查询
     */
    public static final String URL_ZXJCRMONSELECT = URL_SERVERssssss + "/historySearch/equipRunningStateSearch/";
    public static final int REQUEST_CODE_ZXJCRMONSELECT = 111;

    /***采购系统*/
    public static final String URL_SERVERsssssss = "http://www.yunzhishui.com/cggl/appsso/";
    public static final String URL_SERVERssssssss = "http://192.168.1.161:8805/";
    /***导航页*/
    public static final String URL_ONLINEMENULIST = URL_SERVERsssssss + "/dh/menuList/";
    public static final int REQUEST_CODE_ONLINEMENULIST = 120;
    /**
     * 报名管理列表页
     */
    public static final String URL_ONLINEFIND = URL_SERVERsssssss + "/online/applyManage/find/";
    public static final int REQUEST_CODE_ONLINEFIND = 112;
    /**
     * 报名管理详细信息
     */
    public static final String URL_ONLINEFINDDETAIL = URL_SERVERsssssss + "/online/applyManage/findSupplierInfo/";
    public static final int REQUEST_CODE_ONLINEFINDDETAIL = 113;
    /**
     * 项目基本信息
     */
    public static final String URL_ONLINEFPROJECTFIND = URL_SERVERsssssss + "/online/project/find/";
    public static final int REQUEST_CODE_ONLINEFPROJECTFIND = 114;
    /**
     * 报价管理列表
     */
    public static final String URL_ONLINEQUOTELIST = URL_SERVERsssssss + "/online/quoteManage/find/";
    public static final int REQUEST_CODE_ONLINEQUOTELIST = 115;
    /**
     * 供应商报价信息
     */
    public static final String URL_ONLINEQUOTEDETAIL = URL_SERVERsssssss + "/online/quoteManage/findQuoteListByProjId/";
    public static final int REQUEST_CODE_ONLINEQUOTEDETAIL = 116;
    /**
     * 供应商报名详细信息
     */
    public static final String URL_ONLINESUPPLIER = URL_SERVERsssssss + "/supplier/toSupplierDetailPage/";
    public static final int REQUEST_CODE_ONLINESUPPLIER = 117;
    /**
     * 产品目录
     */
    public static final String URL_ONLINECATALOGUE = URL_SERVERsssssss + "/supplier/findSupplierProdcutInfo/";
    public static final int REQUEST_CODE_ONLINECATALOGUE = 118;
    /**
     * 资质资料
     */
    public static final String URL_ONLINEQUALIFI = URL_SERVERsssssss + "/supplier/findSupplierFileInfo/";
    public static final int REQUEST_CODE_ONLINEQUALIFI = 119;
    /**
     * 主要业绩
     */
    public static final String URL_ONLINEACHIE = URL_SERVERsssssss + "/supplier/findSupplierScoreInfo/";
    public static final int REQUEST_CODE_ONLINEACHIE = 121;

    public static interface NEWSHUIZHIYUN{
        int PAGECODE=1008;
        String PAGEBASEURL=BASE_URL+"/dh/menuList";

        String BUILDCOUNT_URL=BASE_URL+"waterStat/flowStat";
        int BUILDCOUNT_CODE=1009;

    }
}
