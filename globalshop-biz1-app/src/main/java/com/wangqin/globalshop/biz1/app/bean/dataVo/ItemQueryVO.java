package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 商品
 *
 * @author xiajun
 */
@Getter
@Setter
@ToString
public class ItemQueryVO extends PageQueryVO {

	/**
	 * 商品列表：按条件查询
	 */
	private String itemCode;

    private String name;

    private String categoryCode;

    /**
     * 前端传来的
     */
    private String startTime;

    private String endTime;

    private Integer status;

    /**
     * 转化成数据库使用的
     */
    private Date startDate;

    private Date endDate;

    /**
     * 0808新加字段
     */
    private Integer isAbroad;

    private Integer shelfMethod;

    private Long id;

    private String companyNo;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 商品类目ID
     */
    private Long categoryId;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 采购站点
     */
    private String buySite;

    /**
     * 创建时间开始
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startGmt;
    /**
     * 创建时间end
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endGmt;

    /**
     * 商品状态
     */
    private Integer purchaseStatus;

    private Integer hasInventory;

    private Integer hasVirtualInventory;

    private String owners;

    private String categoryName;

    private Integer isNew;

    private Integer saleType;

    private String mainPic;

    private Integer mainPicNum;

    private String itemShort;

    private String country;

    private Integer currency;

    private String origin;

    private Double freight;

    private Double weight;

    private String priceRange;

    private String unit;

    private String source;

    private String promotion;

    private String contactPerson;

    private String contactTel;

    private Integer idCard;

    private Integer isSale;

    private String userCreate;

    private String userModify;

    private List<ItemSkuAddVO> itemSkus = new ArrayList<ItemSkuAddVO>();

    private String skuList;

    /**
     * 商品描述信息
     */
    private String remark;

    /**
     * 商品详情，同步到有赞
     */
    private String detail;

    /**
     * 规格
     */
    private String spec;

    /**
     * 型号
     */
    private String model;

    private Integer logisticType;

    /**
     * 有赞商品链接
     */
    private String outerAlias;

    /**
     * 男女款式
     */
    private String sexStyle;

    private String dimensionCodePic;

    private String bookingDate;

    private Integer wxisSale;

    private String refuseReason;

    private Long buyerId;

    private String buyerName;

    /**
     * 男女款式
     */
    private String imageIds;

    private Integer thirdSale;

    /**
     * 在有赞售卖
     */
    private Integer saleOnYouzan;

    /**
     * skucode
     */
    private String skuCode;

    private String salePrice;

    private String skuColor;

    private Integer itemQuantity;

    private List<BuyerDO> wxList;

    private String saleOnChannels;

    private Date gmtModify;

    public List<Integer> generateSaleOnChannels() {
        List<Integer> list = new ArrayList<Integer>();
        if (this.getSaleOnYouzan() == 1) {
            list.add(ChannelType.YouZan.getValue());
        }
        if (this.getThirdSale() == 1) {
            list.add(ChannelType.HaiHu.getValue());
        }
        return list;
    }
}
