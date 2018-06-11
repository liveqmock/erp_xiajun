package com.wangqin.globalshop.channel.service.channelAccount;


import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountSo;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelAccountDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelAccountDOMapperExt;
import com.wangqin.globalshop.common.utils.EasyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("channelAccountService")
public class ChannelAccountServiceImpl  implements IChannelAccountService {

	@Autowired
	private ChannelAccountDOMapperExt channelAccountDOMapper;

	@Autowired
	private AuthUserDOMapperExt authUserDOMapperExt;


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

			//授权信息
			channelAccount.setShopCode(shopCode);
			channelAccount.setShopName(shopCode);
			channelAccount.setCookie(cookie);

			//其他信息配置
			channelAccount.setStatus(0);//0正常，1关闭
			channelAccount.init();

			channelAccountDOMapper.insert(channelAccount);
		}else {

			//渠道信息
			channelAccount.setChannelId(Long.valueOf(ChannelType.TaoBao.getValue()));
			channelAccount.setChannelNo(ChannelType.TaoBao.getValue()+"");
			channelAccount.setType(ChannelType.TaoBao.getValue());
			channelAccount.setChannelName(ChannelType.TaoBao.getName());

			//company信息，所属域

			//授权信息
			channelAccount.setShopCode(shopCode);
			channelAccount.setShopName(shopCode);
			channelAccount.setCookie(cookie);

			//其他信息配置
			channelAccount.setStatus(0);//0正常，1关闭
			channelAccount.init();
			channelAccountDOMapper.updateByPrimaryKey(channelAccount);
		}

	}



	@Override
	public ChannelAccountDO selectOne(ChannelAccountDO tmEntity) {
		return channelAccountDOMapper.queryByTypeAndCompanyNo(tmEntity);
	}

	@Override
	public List<ChannelAccountDO> searchCAListByUserNo(String userNo){
		List<ChannelAccountDO> resultList = new ArrayList<>();
		if(EasyUtil.isStringEmpty(userNo)){
			return resultList;
		}
        AuthUserDO authUserDO = authUserDOMapperExt.selectByLoginName(userNo);

		ChannelAccountSo so = new ChannelAccountSo();
		so.setCompanyNo(authUserDO.getCompanyNo());
		so.setStatus(0);
		resultList = channelAccountDOMapper.queryPoList(so);
		return resultList;
	}

	/**
	 * 根据客户NO，和平台，获取所有该平台下的店铺
	 * @param companyNo
	 * @param channelType
	 * @return
	 */
	@Override
	public List<ChannelAccountDO> searchCAListByComNoChType(String companyNo,ChannelType channelType){
		List<ChannelAccountDO> resultList = new ArrayList<>();
		if(EasyUtil.isStringEmpty(companyNo)){
			return resultList;
		}
		ChannelAccountSo so = new ChannelAccountSo();
		so.setCompanyNo(companyNo);
		so.setStatus(0);
		so.setType(channelType.getValue());
		resultList = channelAccountDOMapper.queryPoList(so);
		return resultList;
	}


}
