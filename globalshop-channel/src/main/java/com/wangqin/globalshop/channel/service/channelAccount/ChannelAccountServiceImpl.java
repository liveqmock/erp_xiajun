package com.wangqin.globalshop.channel.service.channelAccount;


import java.util.Date;
import java.util.List;

import com.wangqin.globalshop.channel.dal.mapperExt.CAChannelAccountDOMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelAccountDOMapper;
import com.wangqin.globalshop.channel.dal.dataSo.ChannelAccountSo;

@Service("channelAccountService")
public class ChannelAccountServiceImpl  implements IChannelAccountService {

	@Autowired CAChannelAccountDOMapperExt channelAccountDOMapper;


	public ChannelAccountDOMapper getChannelAccountMapper() {
		return channelAccountDOMapper;
	}

//	public void setChannelAccountMapper(CAChannelAccountDOMapperExt channelAccountMapper) {
//		this.channelAccountDOMapperExt = channelAccountMapper;
//	}

	@Override
	public Integer queryPoCount(ChannelAccountSo so) {
		return this.channelAccountDOMapper.queryPoCount(so);
	}

	@Override
	public ChannelAccountDO queryPo(ChannelAccountSo so){
		return this.channelAccountDOMapper.queryPo(so);
	}

	@Override
	public List<ChannelAccountDO> queryPoList(ChannelAccountSo so) {
		return this.channelAccountDOMapper.queryPoList(so);
	}



	public int deleteByPrimaryKey(Long id){
		return this.channelAccountDOMapper.deleteByPrimaryKey(id);
	}

	public int insert(ChannelAccountDO record){
		return this.channelAccountDOMapper.insert(record);
	}

	public int insertSelective(ChannelAccountDO record){
		return this.channelAccountDOMapper.insertSelective(record);
	}

	public ChannelAccountDO selectByPrimaryKey(Long id){
		return this.channelAccountDOMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ChannelAccountDO record){
		return this.channelAccountDOMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(ChannelAccountDO record){
		return this.channelAccountDOMapper.updateByPrimaryKey(record);
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
		ChannelAccountDO channelAccount = channelAccountDOMapper.queryPo(entitySo);

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

			channelAccountDOMapper.insert(channelAccount);
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

			channelAccountDOMapper.updateByPrimaryKey(channelAccount);
		}

	}

}
