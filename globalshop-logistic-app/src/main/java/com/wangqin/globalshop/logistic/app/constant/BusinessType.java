package com.wangqin.globalshop.logistic.app.constant;

/**
 * 海关报文业务类型
 *
 * @author angus
 * @date 2018/8/21
 */
public interface BusinessType {
    /**
     * 商品订单
     */
    String IMPORTORDER = "IMPORTORDER";

    /**
     * 进口企业备案
     */
    String IMPORT_COMPANY = "IMPORT_COMPANY";

    /**
     * 清单
     */
    String PERSONAL_GOODS_DECLAR = "PERSONAL_GOODS_DECLAR";

    /**
     * 回执
     */
    String RESULT = "RESULT";

    /**
     * 进口企业备案回执
     */
    String CHECKRESULT = "CHECKRESULT";

    String CHECK_RESULT = "checkResult";

    /**
     * 进口运单出区回执
     */
    String IMPORTBILLRESULT = "IMPORTBILLRESULT";

    /**
     * 税款是否应征信息发送企业
     */
    String TAXISNEED = "TAXISNEED";

    /**
     * 退单
     */
    String IMPORT_ORDER_RETURN = "IMPORT_ORDER_RETURN";

    /**
     * 删单
     */
    String MODIFY_CANCEL = "MODIFY_CANCEL";
}
