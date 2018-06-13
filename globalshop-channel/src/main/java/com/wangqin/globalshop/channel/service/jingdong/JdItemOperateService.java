package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemOperateDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;

import java.util.List;

/**
 * Create by 777 on 2018/6/13
 */
public interface JdItemOperateService {

	public int deleteByPrimaryKey(Long id);

	public int insert(JdItemOperateDO record);

	public int insertSelective(JdItemOperateDO record);

	public JdItemOperateDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(JdItemOperateDO record);

	public int updateByPrimaryKeyWithBLOBs(JdItemOperateDO record);

	public int updateByPrimaryKey(JdItemOperateDO record);

	public JdItemOperateDO searchJdItemOperate(JdItemOperateDO jdItemOperateDO);

	public List<JdItemOperateDO> searchJdItemOperateList(JdItemOperateDO jdItemOperateDO);

	public Long searchJdItemOperateCount(JdItemOperateDO jdItemOperateDO);

	public void createItemOpreate(JdShopOauthDO shopOauth, String operateType, String itemCode, String syncStatus, String sendStatus);


	public void queryItemThenSync2Jd4Task(JdItemOperateDO jdItemOperateDO, JdShopOauthDO shopOauth);


}
