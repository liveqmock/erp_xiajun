package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
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


	public int deleteByPrimaryKey(Long id){
		return jdShopOauthDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(JdShopOauthDO record){
		return jdShopOauthDOMapperExt.insert(record);
	}

	public int insertSelective(JdShopOauthDO record){
		return jdShopOauthDOMapperExt.insertSelective(record);
	}

	public JdShopOauthDO selectByPrimaryKey(Long id){
		return jdShopOauthDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(JdShopOauthDO record){
		return jdShopOauthDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(JdShopOauthDO record){
		return jdShopOauthDOMapperExt.updateByPrimaryKey(record);
	}

	public JdShopOauthDO searchShopOauth(JdShopOauthDO jdShopOauthDO){
		return jdShopOauthDOMapperExt.searchShopOauth(jdShopOauthDO);
	}

	public List<JdShopOauthDO> searchShopOauthList(JdShopOauthDO jdShopOauthDO){
		return jdShopOauthDOMapperExt.searchShopOauthList(jdShopOauthDO);
	}

	public Long searchShopOauthCount(JdShopOauthDO jdShopOauthDO){
		return jdShopOauthDOMapperExt.searchShopOauthCount(jdShopOauthDO);
	}



	/**
	 * jdShopOauthDo做成统一标准
	 * @param jdShopOauthDO
	 */
	public void createOrUpdateShopOauth(ChannelType channelType, JdShopOauthDO jdShopOauthDO){
		JdShopOauthDO so = new JdShopOauthDO();
		so.setChannelNo(String.valueOf(channelType.getValue()));
		so.setShopCode(jdShopOauthDO.getShopCode());

		JdShopOauthDO existShopOauth = jdShopOauthDOMapperExt.searchShopOauth(so);

		if(existShopOauth == null){
			jdShopOauthDO.setGmtCreate(new Date());
			jdShopOauthDO.setIsDel(false);
			jdShopOauthDO.setVersion(0L);
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
