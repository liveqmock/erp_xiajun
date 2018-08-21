package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdLogisticsDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.JdLogisticsDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.EasyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 777 on 2018/8/20
 */
@Service
public class JdLogisticsServiceImpl implements JdLogisticsService {

	@Autowired
	private JdLogisticsDOMapperExt logisticsDOMapperExt;


	public int deleteByPrimaryKey(Long id){
		return logisticsDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(JdLogisticsDO record){
		return logisticsDOMapperExt.insert(record);
	}

	public int insertSelective(JdLogisticsDO record){
		return logisticsDOMapperExt.insertSelective(record);
	}

	public JdLogisticsDO selectByPrimaryKey(Long id){
		return logisticsDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(JdLogisticsDO record){
		return logisticsDOMapperExt.updateByPrimaryKeySelective(record);
	}
	public int updateByPrimaryKey(JdLogisticsDO record){
		return logisticsDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public JdLogisticsDO queryPo(JdLogisticsDO record){
		return logisticsDOMapperExt.queryPo(record);
	}

	public Long queryPoCount(JdLogisticsDO record){
		return logisticsDOMapperExt.queryPoCount(record);
	}

	public List<JdLogisticsDO> queryPoList(JdLogisticsDO record){
		return logisticsDOMapperExt.queryPoList(record);
	}


	/**
	 * 有赞：支持子订单发货，一条运单号记录对应一条发货记录，海狐只能全部发货，因此没有channel_sub_order
	 * @param mallSubOrderDOS:一定是同一个订单
	 * @param shippingOrder
	 *
	 * 如果需要拆单发货，使用该字段指定要发货的商品交易明细编号，多个明细编号用半角逗号“,”分隔； 不需要拆单发货，则该字段不传或值为空；
	 */
	@Override
	public void dealLogistics(ChannelShopDO channelShop, List<MallSubOrderDO> mallSubOrderDOS, ShippingOrderDO shippingOrder){

		if(EasyUtil.isListEmpty(mallSubOrderDOS) || shippingOrder == null || channelShop == null){
             return;
		}

		if(EasyUtil.isStringEmpty(shippingOrder.getLogisticCompany()) || EasyUtil.isStringEmpty(shippingOrder.getLogisticNo())){
            throw new ErpCommonException("logistics_no or logistics_company error","运单号或快递公司名为空");
		}

		JdLogisticsDO logisticsDO = new JdLogisticsDO();
		logisticsDO.setChannelNo(channelShop.getChannelNo());
		logisticsDO.setCompanyNo(channelShop.getCompanyNo());
		logisticsDO.setShopCode(channelShop.getShopCode());

		//logisticsDO.setLogisticCode();
		logisticsDO.setLogisticName(shippingOrder.getLogisticCompany());
		logisticsDO.setLogisticNo(shippingOrder.getLogisticNo());

		logisticsDO.setChannelOrderNo(mallSubOrderDOS.get(0).getChannelOrderNo());

		logisticsDO.setSendStatus(SendStatus.REQUEST);

		if(channelShop.getChannelNo().equalsIgnoreCase(""+ChannelType.YouZan.getValue())){
             //有赞,写子订单号
			String channelSubOrderNos = "";
			for(MallSubOrderDO mallSubOrder : mallSubOrderDOS){
                 if(EasyUtil.isStringEmpty(channelSubOrderNos)){
					 channelSubOrderNos += mallSubOrder.getChannelSubOrderNo();
				 }else {
					 channelSubOrderNos += "," + mallSubOrder.getChannelSubOrderNo();
				 }
			}
			logisticsDO.setChannelSubOrderNo(channelSubOrderNos);

		}else if(channelShop.getChannelNo().equalsIgnoreCase(""+ChannelType.HaiHu.getValue())){
			//海狐 写skuCodes
			String skuCodes = "";
			for(MallSubOrderDO mallSubOrder : mallSubOrderDOS){
				if(EasyUtil.isStringEmpty(skuCodes)){
					skuCodes += mallSubOrder.getSkuCode();
				}else {
					skuCodes += "," + mallSubOrder.getSkuCode();
				}
			}
			logisticsDO.setChannelSubOrderNo(skuCodes);
		}else {
			throw new ErpCommonException("channelno not suppourt","当前渠道不支持渠道自动发货");
		}

		logisticsDO.init4NoLogin();

		logisticsDOMapperExt.insert(logisticsDO);
	}



}
