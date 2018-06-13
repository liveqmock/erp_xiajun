package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemOperateDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.JdItemOperateDOMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 777 on 2018/6/13
 */
@Service
public class JdItemOperateServiceImpl implements JdItemOperateService {

	@Autowired
	private JdItemOperateDOMapperExt jdItemOperateDOMapperExt;


	public int deleteByPrimaryKey(Long id){
		return jdItemOperateDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(JdItemOperateDO record){
		return jdItemOperateDOMapperExt.insert(record);
	}

	public int insertSelective(JdItemOperateDO record){
		return jdItemOperateDOMapperExt.insertSelective(record);
	}

	public JdItemOperateDO selectByPrimaryKey(Long id){
		return jdItemOperateDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(JdItemOperateDO record){
		return jdItemOperateDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(JdItemOperateDO record){
		return jdItemOperateDOMapperExt.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(JdItemOperateDO record){
		return jdItemOperateDOMapperExt.updateByPrimaryKey(record);
	}

	public JdItemOperateDO searchJdItemOperate(JdItemOperateDO jdItemOperateDO){
		return jdItemOperateDOMapperExt.searchJdItemOperate(jdItemOperateDO);
	}

	public List<JdItemOperateDO> searchJdItemOperateList(JdItemOperateDO jdItemOperateDO){
		return jdItemOperateDOMapperExt.searchJdItemOperateList(jdItemOperateDO);
	}

	public Long searchJdItemOperateCount(JdItemOperateDO jdItemOperateDO){
		return jdItemOperateDOMapperExt.searchJdItemOperateCount(jdItemOperateDO);
	}

	public void createItemOpreate(JdShopOauthDO shopOauth, String operateType, String itemCode, String syncStatus, String sendStatus){
		JdItemOperateDO jdItemOperateDO = new JdItemOperateDO();
		jdItemOperateDO.setChannelNo(shopOauth.getChannelNo());
		jdItemOperateDO.setCompanyNo(shopOauth.getCompanyNo());
		jdItemOperateDO.setShopCode(shopOauth.getShopCode());
		jdItemOperateDO.setItemCode(itemCode);
		jdItemOperateDO.setOperateType(operateType);
		jdItemOperateDO.setIsDel(false);

		jdItemOperateDO.setSyncStatus(syncStatus);
		jdItemOperateDO.setSendStatus(sendStatus);
		jdItemOperateDOMapperExt.insert(jdItemOperateDO);
	}

	public void queryItemThenSync2Jd4Task(JdItemOperateDO jdItemOperateDO, JdShopOauthDO shopOauth){



	}


}
