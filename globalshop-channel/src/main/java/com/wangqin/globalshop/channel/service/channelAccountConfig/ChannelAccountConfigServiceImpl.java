package com.wangqin.globalshop.channel.service.channelAccountConfig;

import com.wangqin.globalshop.biz1.app.constants.enums.AccountConfigKey;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountConfigSo;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelAccountConfigDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelAccountConfigDOMapperExt;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service("channelAccountConfigService")
public class ChannelAccountConfigServiceImpl implements IChannelAccountConfigService {

	@Autowired
	private ChannelAccountConfigDOMapperExt channelAccountConfigDOMapperExt;


	public ChannelAccountConfigDOMapper getMapper() {
		return channelAccountConfigDOMapperExt;
	}

	public ChannelAccountConfigDO queryPo(ChannelAccountConfigSo channelAccountConfigSo){
		return  channelAccountConfigDOMapperExt.queryPo(channelAccountConfigSo);
	}

	public void createOrupdateConfig(String companyNo, String shopCode){
		if(StringUtils.isBlank(shopCode)){
             //throw new Exception("shopCode empty error");
		}
		ChannelAccountConfigSo so = new ChannelAccountConfigSo();
		so.setShopcode(shopCode);
		so.setCompanyNo(companyNo);
		ChannelAccountConfigDO po = channelAccountConfigDOMapperExt.queryPo(so);
		if(po == null){
			//先配置成抓2天以前的订单
			Date date = new Date();
			Date startTime = DateUtil.getDateByCalculate(date, Calendar.DAY_OF_MONTH, -2);
			po = new ChannelAccountConfigDO();
			po.setShopcode(shopCode);
			po.setCompanyNo(companyNo);
			po.setItemkey(AccountConfigKey.LAST_TRADES_GET_TIME.getDescription());
			po.setItemvalue(DateUtil.convertDate2Str(startTime,DateUtil.formateStr19));
			po.setGmtCreate(new Date());
			channelAccountConfigDOMapperExt.insert(po);
		}else {
			//先不做，可以的话把时间拉回2天以前
		}

	}

}
