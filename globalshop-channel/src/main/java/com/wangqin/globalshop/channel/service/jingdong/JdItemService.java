package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;

import java.util.List;

/**
 * Create by 777 on 2018/6/13
 */
public interface JdItemService {

	public int deleteByPrimaryKey(Long id);

	public int insert(JdItemDO record);

	public int insertSelective(JdItemDO record);

	public JdItemDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(JdItemDO record);

	public int updateByPrimaryKeyWithBLOBs(JdItemDO record);

	public int updateByPrimaryKey(JdItemDO record);

	public JdItemDO searchJdItem(JdItemDO jdItemDO);

	public List<JdItemDO> searchJdItemList(JdItemDO jdItemDO);

	public Long searchJdItemCount(JdItemDO jdItemDO);

	public void saveItems4Task(List<JdItemDO> jdItemDOS);

	public void sendJdItem2globalshop4Task(JdItemDO jdItemDO, JdShopOauthDO shopOauth);
}
