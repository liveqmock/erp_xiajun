package com.wangqin.globalshop.order.app.kuaidi_bean;

import java.util.ArrayList;

/**
 * 快递100查询结果对应的实体类
 * <p>
 * 快递100查询结果返回示例：
 * <pre>
 * {
 *  "message":"ok",
 *  "nu":"3101738504868",
 *  "ischeck":"1",
 *  "condition":"F00",
 *  "com":"yunda",
 *  "status":"200",
 *  "state":"3",
 *  "data":[
 *         {
 *              "time":"2018-05-15 15:39:04",
 *              "ftime":"2018-05-15 15:39:04",
 *              "context":"[江西主城区公司赣州市政府服务部]快件已被 本人 签收"
 *          },
 *          {
 *              "time":"2018-05-15 12:19:02",
 *              "ftime":"2018-05-15 12:19:02",
 *              "context":"[江西主城区公司赣州市政府服务部]进行派件扫描；派送业务员：陈道龙；联系电话：18172779142"
 *          }
 *  ]
 * }
 * </pre>
 */
public class Result {
    /**
     * 无意义字段
     */
    private String message = "";
    /**
     * 物流单号
     */
    private String nu = "";
    /**
     * 无意义字段
     */
    private String ischeck = "0";
    /**
     * 无意义字段
     */
    private String condition = "";
    /**
     * 物流公司编号
     */
    private String com = "";
    /**
     * 查询结果状态：
     * <li>0：物流单暂无结果</li>
     * <li>1：查询成功</li>
     * <li>2：接口出现异常</li>
     */
    private String status = "0";
    /**
     * 快递单当前的状态：
     * <li>0：在途，即货物处于运输过程中</li>
     * <li>1：揽件，货物已由快递公司揽收并且产生了第一条跟踪信息</li>
     * <li>2：疑难，货物寄送过程出了问题</li>
     * <li>3：签收，收件人已签收</li>
     * <li>4：退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收</li>
     * <li>5：派件，即快递正在进行同城派件</li>
     * <li>6：退回，货物正处于退回发件人的途中</li>
     */
    private String state = "0";
    /**
     * 物流轨迹信息
     */
    private ArrayList<ResultItem> data = new ArrayList<>();

    @Override
    public Result clone() {
        Result r = new Result();
        r.setCom(this.getCom());
        r.setIscheck(this.getIscheck());
        r.setMessage(this.getMessage());
        r.setNu(this.getNu());
        r.setState(this.getState());
        r.setStatus(this.getStatus());
        r.setCondition(this.getCondition());
        r.setData((ArrayList<ResultItem>) this.getData().clone());

        return r;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public ArrayList<ResultItem> getData() {
        return data;
    }

    public void setData(ArrayList<ResultItem> data) {
        this.data = data;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return JacksonHelper.toJSON(this);
    }
}
