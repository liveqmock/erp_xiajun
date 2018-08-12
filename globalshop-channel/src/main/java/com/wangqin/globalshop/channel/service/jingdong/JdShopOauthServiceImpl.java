package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.JdShopOauthDOMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/6/11
 */
@Service
public class JdShopOauthServiceImpl implements JdShopOauthService{

	@Autowired
	private JdShopOauthDOMapperExt jdShopOauthDOMapperExt;


	@Override
    public int deleteByPrimaryKey(Long id){
		return jdShopOauthDOMapperExt.deleteByPrimaryKey(id);
	}

	@Override
    public int insert(JdShopOauthDO record){
		return jdShopOauthDOMapperExt.insert(record);
	}

	@Override
    public int insertSelective(JdShopOauthDO record){
		return jdShopOauthDOMapperExt.insertSelective(record);
	}

	@Override
    public JdShopOauthDO selectByPrimaryKey(Long id){
		return jdShopOauthDOMapperExt.selectByPrimaryKey(id);
	}

	@Override
    public int updateByPrimaryKeySelective(JdShopOauthDO record){
		return jdShopOauthDOMapperExt.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKey(JdShopOauthDO record){
		return jdShopOauthDOMapperExt.updateByPrimaryKey(record);
	}

	@Override
    public JdShopOauthDO searchShopOauth(JdShopOauthDO jdShopOauthDO){
		return jdShopOauthDOMapperExt.searchShopOauth(jdShopOauthDO);
	}

	@Override
    public List<JdShopOauthDO> searchShopOauthList(JdShopOauthDO jdShopOauthDO){
		return jdShopOauthDOMapperExt.searchShopOauthList(jdShopOauthDO);
	}

	@Override
    public Long searchShopOauthCount(JdShopOauthDO jdShopOauthDO){
		return jdShopOauthDOMapperExt.searchShopOauthCount(jdShopOauthDO);
	}

	@Override
    public JdShopOauthDO searchShopOauthByCCS(String channelNo, String companyNo, String shopCode){
		JdShopOauthDO jdShopOauthDO = new JdShopOauthDO();
		jdShopOauthDO.setChannelNo(channelNo);
		jdShopOauthDO.setCompanyNo(companyNo);
		jdShopOauthDO.setShopCode(shopCode);
		return jdShopOauthDOMapperExt.searchShopOauth(jdShopOauthDO);
	}


	/**
	 * jdShopOauthDo做成统一标准
	 * @param jdShopOauthDO
	 */
	@Override
    public void createOrUpdateShopOauth(ChannelType channelType, JdShopOauthDO jdShopOauthDO){
		JdShopOauthDO so = new JdShopOauthDO();
		so.setChannelNo(String.valueOf(channelType.getValue()));
		so.setShopCode(jdShopOauthDO.getShopCode());

		JdShopOauthDO existShopOauth = jdShopOauthDOMapperExt.searchShopOauth(so);

		if(existShopOauth == null){
			jdShopOauthDO.setGmtCreate(new Date());
			jdShopOauthDO.setIsDel(false);
			jdShopOauthDO.setVersion(0L);
			jdShopOauthDO.init4NoLogin();
			jdShopOauthDO.setOpen(true);
			jdShopOauthDOMapperExt.insert(jdShopOauthDO);
		}else {
			existShopOauth.setAccessToken(jdShopOauthDO.getAccessToken());
			existShopOauth.setRefreshToken(jdShopOauthDO.getRefreshToken());
			existShopOauth.setExpiresTime(jdShopOauthDO.getExpiresTime());
			existShopOauth.setOpen(true);
			existShopOauth.setGmtModify(new Date());
			jdShopOauthDOMapperExt.updateByPrimaryKey(existShopOauth);
		}
	}




}
