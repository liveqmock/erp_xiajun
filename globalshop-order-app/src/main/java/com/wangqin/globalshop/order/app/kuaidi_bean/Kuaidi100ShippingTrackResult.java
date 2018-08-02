package com.wangqin.globalshop.order.app.kuaidi_bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
 *          ...
 *  ]
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/7/25
 */
@Data
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

    public void setStatus(String status) {
        this.status = status;
        setStatusInfo(status);
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

    public void setState(String state) {
        this.state = state;
        setStateInfo(state);
    }

    private void setStateInfo(String state) {
        CommonShippingTrackState commonShippingTrackState = CommonShippingTrackState.of(Integer.valueOf(state));
        if (commonShippingTrackState != null) {
            this.stateInfo = commonShippingTrackState.getStateInfo();
        }
    }

    /**
     * 将快递100物流轨迹信息转换为通用物流轨迹信息
     *
     * @return 通用物流轨迹信息
     */
    public CommonShippingTrack toCommonShippingTrack() {
        List<CommonShippingTrackNode> shippingTrackInfo = new ArrayList<>();

        if (data != null) {
            data.forEach(
                    dataItem -> {
                        CommonShippingTrackNode shippingTrackNode = new CommonShippingTrackNode();
                        shippingTrackNode.setDate(dataItem.getTime());
                        shippingTrackNode.setInfo(dataItem.getContext());
                        shippingTrackInfo.add(shippingTrackNode);
                    }
            );

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
