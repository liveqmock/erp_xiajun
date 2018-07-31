package com.wangqin.globalshop.order.app.kuaidi_bean;

import com.wangqin.globalshop.order.app.util.JacksonHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 快递100物流轨迹信息对应的实体类
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
 *
 * @author angus
 * @date 2018/7/25
 */
public class Kuaidi100ShippingTrackResult {
    /**
     * 查询失败时返回false，查询成功无此字段
     */
    private Boolean result;
    /**
     * 查询失败时返回的状态码，查询成功无此字段
     */
    private String returnCode;
    /**
     * 查询结果
     */
    private String message;
    /**
     * 物流单号
     */
    private String nu;
    /**
     * 无意义字段
     */
    private String ischeck;
    /**
     * 无意义字段
     */
    private String condition;
    /**
     * 物流公司编号
     */
    private String com;
    /**
     * 查询结果状态：
     * <li>0：物流单暂无结果</li>
     * <li>1：查询成功</li>
     * <li>2：接口出现异常</li>
     */
    private String status;

    private String statusInfo;
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
    private String state;

    private String stateInfo;
    /**
     * 物流轨迹信息
     */
    private List<Kuaidi100ShippingTrackResultNode> data;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
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

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        setStatusInfo(status);
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    private void setStatusInfo(String status) {
        switch (status) {
            case "0": {
                this.statusInfo = "物流单暂无结果";
                break;
            }
            case "1": {
                this.statusInfo = "查询成功";
                break;
            }
            case "2": {
                this.statusInfo = "接口出现异常";
                break;
            }
            case "200": {
                this.statusInfo = "查询成功";
                break;
            }
            default:
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        setStateInfo(state);
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private void setStateInfo(String state) {
        switch (state) {
            case "0": {
                this.stateInfo = "在途，即货物处于运输过程中";
                break;
            }
            case "1": {
                this.stateInfo = "揽件，货物已由快递公司揽收并且产生了第一条跟踪信息";
                break;
            }
            case "2": {
                this.stateInfo = "疑难，货物寄送过程出了问题";
                break;
            }
            case "3": {
                this.stateInfo = "签收，收件人已签收";
                break;
            }
            case "4": {
                this.stateInfo = "退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收";
                break;
            }
            case "5": {
                this.stateInfo = "派件，即快递正在进行同城派件";
                break;
            }
            case "6": {
                this.stateInfo = "退回，货物正处于退回发件人的途中";
                break;
            }
            default:
        }
    }


    public List<Kuaidi100ShippingTrackResultNode> getData() {
        return data;
    }

    public void setData(List<Kuaidi100ShippingTrackResultNode> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JacksonHelper.toJSON(this);
    }


    /**
     * 将快递100物流轨迹信息转换为通用物流轨迹信息
     *
     * @return
     */
    public CommonShippingTrack toCommonShippingTrack() {
        List<CommonShippingTrackNode> shippingTrackInfo = new ArrayList<>();

        if (data != null) {
            data.forEach(dataItem -> {
                CommonShippingTrackNode shippingTrackNode = new CommonShippingTrackNode();
                shippingTrackNode.setDate(dataItem.getTime());
                shippingTrackNode.setInfo(dataItem.getContext());
                shippingTrackInfo.add(shippingTrackNode);
            });

            return new CommonShippingTrack
                    .Builder(shippingTrackInfo)
                    .platform("kuaidi100")
                    .status(status)
                    .statusInfo(statusInfo)
                    .state(state)
                    .stateInfo(stateInfo)
                    .logisticNo(nu)
                    .logisticCompanyCode(com)
                    .build();
        }

        return null;
    }
}
