package com.wangqin.globalshop.biz1.app.service.channelAccount;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountSo;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelAccountDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelAccountDOMapperExt;

@Service("channelAccountService")
public class ChannelAccountServiceImpl  implements IChannelAccountService {

	@Resource ChannelAccountDOMapperExt channelAccountDOMapperExt;


	public ChannelAccountDOMapper getChannelAccountMapper() {
		return channelAccountDOMapperExt;
	}

	public void setChannelAccountMapper(ChannelAccountDOMapperExt channelAccountMapper) {
		this.channelAccountDOMapperExt = channelAccountDOMapperExt;
	}

	@Override
	public Integer queryPoCount(ChannelAccountSo so) {
		return this.channelAccountDOMapperExt.queryPoCount(so);
	}

	@Override
	public ChannelAccountDO queryPo(ChannelAccountSo so){
		return this.channelAccountDOMapperExt.queryPo(so);
	}

	@Override
	public List<ChannelAccountDO> queryPoList(ChannelAccountSo so) {
		return this.channelAccountDOMapperExt.queryPoList(so);
	}


	/**
	 * 淘宝授权暂时写死，没有所属哪个company的信息，也么有channel信息
	 * @param shopCode
	 * @param cookie
	 */
	public void createOrupdateAccount4Taobao(String shopCode, String cookie){

		ChannelAccountSo entitySo = new ChannelAccountSo();
		entitySo.setShopCode(shopCode);
		entitySo.setChannelNo(ChannelType.TaoBao.getValue()+"");
		ChannelAccountDO channelAccount =	channelAccountDOMapperExt.queryPo(entitySo);

		if(channelAccount == null) {
			channelAccount = new ChannelAccountDO();

			//渠道信息
			channelAccount.setChannelId(Long.valueOf(ChannelType.TaoBao.getValue()));
			channelAccount.setChannelNo(ChannelType.TaoBao.getValue()+"");
			channelAccount.setType(ChannelType.TaoBao.getValue());
			channelAccount.setChannelName(ChannelType.TaoBao.getName());

			//company信息，所属域
			channelAccount.setCompanyNo(1+"");

			//授权信息
			channelAccount.setShopCode(shopCode);
			channelAccount.setShopName(shopCode);
			channelAccount.setCookie(cookie);

			//其他信息配置
			channelAccount.setStatus(0);//0正常，1关闭
			channelAccount.setIsDel(false);
			channelAccount.setCreator("-1");
			channelAccount.setGmtCreate(new Date());

			channelAccountDOMapperExt.insert(channelAccount);
		}else {

			//渠道信息
			channelAccount.setChannelId(Long.valueOf(ChannelType.TaoBao.getValue()));
			channelAccount.setChannelNo(ChannelType.TaoBao.getValue()+"");
			channelAccount.setType(ChannelType.TaoBao.getValue());
			channelAccount.setChannelName(ChannelType.TaoBao.getName());

			//company信息，所属域
			channelAccount.setCompanyNo(1+"");

			//授权信息
			channelAccount.setShopCode(shopCode);
			channelAccount.setShopName(shopCode);
			channelAccount.setCookie(cookie);

			//其他信息配置
			channelAccount.setStatus(0);//0正常，1关闭
			channelAccount.setIsDel(false);
			channelAccount.setModifier("-1");
			channelAccount.setGmtModify(new Date());

			channelAccountDOMapperExt.updateByPrimaryKey(channelAccount);
		}

	}

	@Override
	public ChannelAccountDO queryByChannelNo(String channelNo) {
		return channelAccountDOMapperExt.queryByChannelNo(channelNo);
	}

	@Override
	public ChannelAccountDO selectOne(ChannelAccountDO tmEntity) {
		return channelAccountDOMapperExt.queryByTypeAndCompanyNo(tmEntity);
	}

}
