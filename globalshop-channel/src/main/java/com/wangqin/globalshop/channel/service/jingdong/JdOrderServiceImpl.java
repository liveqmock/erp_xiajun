package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.JdOrderDOMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.EscapeBodyTag;

import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/6/12
 */
@Service
public class JdOrderServiceImpl implements JdOrderService{

	@Autowired
	private JdOrderDOMapperExt jdOrderDOMapperExt;

	public int deleteByPrimaryKey(Long id){
		return jdOrderDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(JdOrderDO record){
		return jdOrderDOMapperExt.insert(record);
	}

	public int insertSelective(JdOrderDO record){
		return jdOrderDOMapperExt.insertSelective(record);
	}

	public JdOrderDO selectByPrimaryKey(Long id){
		return jdOrderDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(JdOrderDO record){
		return jdOrderDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(JdOrderDO record){
		return jdOrderDOMapperExt.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(JdOrderDO record){
		return jdOrderDOMapperExt.updateByPrimaryKey(record);
	}

	public JdOrderDO searchJdOrder(JdOrderDO jdOrderDO){
		return jdOrderDOMapperExt.searchJdOrder(jdOrderDO);
	}

	public List<JdOrderDO> searchJdOrderList(JdOrderDO jdOrderDO){
		return jdOrderDOMapperExt.searchJdOrderList(jdOrderDO);
	}

	public Long searchJdOrderCount(JdOrderDO jdOrderDO){
		return jdOrderDOMapperExt.searchJdOrderCount(jdOrderDO);
	}

	/**
	 *
	 * 状态修改成request，等待下发
	 * @param jdOrderDOS
	 */
	public void saveOrdes4Task(List<JdOrderDO> jdOrderDOS){
		for(JdOrderDO order : jdOrderDOS){

			order.setSendStatus(SendStatus.REQUEST);
			order.setOrderModifyTime(new Date());

			JdOrderDO so = new JdOrderDO();
			so.setChannelNo(order.getChannelNo());
			so.setShopCode(order.getShopCode());
			so.setChannelOrderNo(order.getChannelOrderNo());
			JdOrderDO result = jdOrderDOMapperExt.searchJdOrder(so);

			if(result == null){
				jdOrderDOMapperExt.insertSelective(order);
			}else {
				result.setModifier("-1");
				result.setGmtModify(new Date());
				result.setOrderJson(order.getOrderJson());
				jdOrderDOMapperExt.updateByPrimaryKey(result);
			}
		}
	}


}
