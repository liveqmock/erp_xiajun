package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.common.utils.AppUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 订单管理查询表单对应的 VO
 * <p>
 * 前端查询参数示例：
 * <pre>
 *  orderNo: 1
 *  channelOrderNo: 2
 *  status: 10
 *  receiver: 3
 *  telephone: 4
 *  startGmtCreate: 2018-08-08
 *  endGmtCreate: 2018-08-26
 * </pre>
 *
 * @author liuhui
 * @date 2018/8/3
 */
@Data
public class MallOrderQueryVO {

    /**
     * 主订单号
     * <p>
     * 对应 mall_order 表的 order_no 字段
     */
    private String orderNo;

    /**
     * 外部订单号
     * <p>
     * 对应 mall_order 表的 channel_order_no 字段
     */
    private String channelOrderNo;

    /**
     * 订单状态
     * <p>
     * 对应 mall_order 表的 status 字段
     */
    private Integer status;

    /**
     * 收件人
     * <p>
     * 对应 mall_sub_order 表的 receiver 字段
     */
    private String receiver;

    /**
     * 联系电话
     * <p>
     * 对应 mall_sub_order 表的 telephone 字段
     */
    private String telephone;

    /**
     * 创建时间范围（起）
     * <p>
     * 对应 mall_order 表的 gmt_create 字段
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startGmtCreate;

    /**
     * 创建时间范围（止）
     * <p>
     * 对应 mall_order 表的 gmt_create 字段
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endGmtCreate;

    /**
     * 公司编号
     * <p>
     * 对应 mall_order 表的 company_no 字段
     */
    private String companyNo;

    public MallOrderQueryVO() {
        this.companyNo = AppUtil.getLoginUserCompanyNo();
    }
}
