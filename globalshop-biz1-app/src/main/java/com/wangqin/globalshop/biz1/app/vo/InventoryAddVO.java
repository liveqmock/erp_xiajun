package com.wangqin.globalshop.biz1.app.vo;

import java.io.Serializable;
import java.util.Date;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * 库存
 * 
 * @author liuhui
 *
 */
public class InventoryAddVO implements Serializable {

	//private static final long serialVersionUID = -8193543821896053616L;

	private String companyNo;
	
	private String creator;
	 
	private String modifier;
	
	private String itemCode;
	
	
	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	/**
	 * 主键id
	 */

	private Long id;
	/**
	 * 商品id
	 */

	private Long itemId;
	/**
	 * skuid
	 */

	private Long skuId;
	/**
	 * 商品名称
	 */

	private String itemName;
	/**
	 * 商品条码
	 */

	private String upc;
	/**
	 * sku码
	 */

	private String skuCode;
	/**
	 * 锁定库存
	 */

	private Integer lockedInv;
	/**
	 * 锁定在途库存量
	 */

	private Integer lockedTransInv;
	/**
	 * 实际库存
	 */

	private Integer inventory;
	/**
	 * 虚拟库存
	 */
	
	private Integer virtualInv;
	/**
	 * 虚拟占用库存
	 */

	private Integer lockedVirtualInv;
	/**
	 * 在途库存
	 */

	private Integer transInv;
	/**
	 * 创建时间
	 */

	private Date gmtCreate;
	/**
	 * 修改时间
	 */

	private Date gmtModify;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Integer getLockedInv() {
		if(lockedInv == null){
			lockedInv = 0;
		}
		return lockedInv;
	}

	public void setLockedInv(Integer lockedInv) {
		this.lockedInv = lockedInv;
	}

	public Integer getInventory() {
		if(inventory == null){
			inventory = 0;
		}
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getVirtualInv() {
		if(virtualInv == null){
			virtualInv = 0;
		}
		return virtualInv;
	}

	public void setVirtualInv(Integer virtualInv) {
		this.virtualInv = virtualInv;
	}
	
	public Integer getLockedVirtualInv() {
		if(lockedVirtualInv == null) {
			lockedVirtualInv = 0;
		}
		return lockedVirtualInv;
	}

	public void setLockedVirtualInv(Integer lockedVirtualInv) {
		this.lockedVirtualInv = lockedVirtualInv;
	}

	public Integer getTransInv() {
		if(transInv == null){
			transInv = 0;
		}
		return transInv;
	}

	public void setTransInv(Integer transInv) {
		this.transInv = transInv;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}
	
	public Integer calcAvailableInv(){
		return this.getInventory() - this.getLockedInv();
	}

	public Integer getLockedTransInv() {
		if(lockedTransInv == null){
			lockedTransInv = 0;
		}
		return lockedTransInv;
	}

	public void setLockedTransInv(Integer lockedTransInv) {
		this.lockedTransInv = lockedTransInv;
	}
	
	/**
	 * 在途可用库存
	 * @return
	 */
	public Integer getAvailableTransInv() {
		return this.getTransInv() - this.getLockedTransInv();
	}
	/**
	 * (现货)可用库存
	 * @return
	 */
	public Integer getAvailableInv() {
		return this.getInventory() - this.getLockedInv();
	}
	
	/**
	 * (总共)可用库存
	 * @return
	 */
	public Integer getTotalAvailableInv() {
		return getAvailableInv()+getAvailableTransInv();
	}
	/**
	 * 同步到第三方平台(有赞)的库存数量
	 * @return
	 */
	public Integer getSysInventory() {
		int virtualInv = getVirtualInv();				//虚拟库存
		int totalAvailableInv = getTotalAvailableInv();	//总可用库存
		int lockedVirtualInv = getLockedVirtualInv();   //虚拟锁定库存
		int resultInv = 0;
		
		if(virtualInv>0) {
			resultInv = virtualInv - lockedVirtualInv;
		} else {
			resultInv = totalAvailableInv - lockedVirtualInv;
		}
		return (resultInv>0)?resultInv:0;
	}


	
}
