package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.constants.enums.AccountConfigKey;
import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.JdShopConfigDOMapperExt;
import com.wangqin.globalshop.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/6/11
 */
@Service
public class JdShopConfigServiceImpl implements JdShopConfigService {

	@Autowired
	private JdShopConfigDOMapperExt jdShopConfigDOMapperExt;


	public int deleteByPrimaryKey(Long id){
		return jdShopConfigDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(JdShopConfigDO record){
		return jdShopConfigDOMapperExt.insert(record);
	}

	public int insertSelective(JdShopConfigDO record){
		return jdShopConfigDOMapperExt.insertSelective(record);
	}

	public JdShopConfigDO selectByPrimaryKey(Long id){
		return jdShopConfigDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(JdShopConfigDO record){
		return jdShopConfigDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(JdShopConfigDO record){
		return jdShopConfigDOMapperExt.updateByPrimaryKey(record);
	}

	public JdShopConfigDO searchShopOauth(JdShopConfigDO JdShopConfigDO){
		return jdShopConfigDOMapperExt.searchShopConfig(JdShopConfigDO);
	}

	public List<JdShopConfigDO> searchShopOauthList(JdShopConfigDO jdShopConfigDO){
		return jdShopConfigDOMapperExt.searchShopConfigList(jdShopConfigDO);
	}

	public Long searchShopOauthCount(JdShopConfigDO jdShopConfigDO){
		return jdShopConfigDOMapperExt.searchShopConfigCount(jdShopConfigDO);
	}



	/**
	 * jdShopOauthDo做成统一标准
	 * @param jdShopOauthDO
	 */
	public void initShopConfig(ChannelType channelType, JdShopOauthDO jdShopOauthDO){

		Date nowTime = new Date();
		Date startTime = DateUtil.getDateByCalculate(nowTime, Calendar.DAY_OF_MONTH, -2);

		String startValue = DateUtil.formatDate(startTime,DateUtil.formateStr19);

		//订单
		initShopCondig4SingleKey(String.valueOf(channelType.getValue()),
				jdShopOauthDO.getShopCode(),AccountConfigKey.LAST_TRADES_GET_TIME.getDescription(),
				startValue);

		//商品
		initShopCondig4SingleKey(String.valueOf(channelType.getValue()),
				jdShopOauthDO.getShopCode(),AccountConfigKey.LAST_ITEM_GET_TIME.getDescription(),
				startValue);
	}

	private void initShopCondig4SingleKey(String channelNo, String shopConde, String key, String value){
		JdShopConfigDO so = new JdShopConfigDO();
		so.setChannelNo(channelNo);
		so.setShopCode(shopConde);
		so.setConfigKey(key);

		JdShopConfigDO existShopConfig = jdShopConfigDOMapperExt.searchShopConfig(so);

		//没有就创建，存在就不管，可以考虑后续优化
		if(existShopConfig == null){
			existShopConfig = new JdShopConfigDO();
			existShopConfig.setChannelNo(channelNo);
			existShopConfig.setShopCode(shopConde);
			existShopConfig.setConfigKey(key);
			existShopConfig.setConfigValue(value);
			jdShopConfigDOMapperExt.insert(existShopConfig);
		}

	}



}
