package com.wangqin.globalshop.mall.service.impl;

import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.vo.ItemQueryVO;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.PicModel;
import com.wangqin.globalshop.mall.service.MallItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Create by 777 on 2018/6/19
 */
@Service
public class MallItemServiceImpl implements MallItemService {

	@Autowired
	private ItemDOMapperExt itemDOMapperExt;


	@Override
    public ItemDTO itemqueryshare(String itemCode, String companyNo){

		ItemQueryVO itemQueryVO = new ItemQueryVO();
		itemQueryVO.setItemCode(itemCode);
		itemQueryVO.setCompanyNo(companyNo);

		List<ItemDTO> itemDTOList = itemDOMapperExt.queryMallItems(itemQueryVO);
		if(itemDTOList == null || itemDTOList.size() != 1 ){
			return null;
		}
		ItemDTO itemDTO = itemDTOList.get(0);
		dealMainPicUrl(itemDTO);
		return itemDTO;
	}

	@Override
    public List<ItemDTO> queryOneDay(String companyNo){
		Date endTime = new Date();
		Date startTime = DateUtil.getDateByCalculate(endTime, Calendar.DAY_OF_MONTH, -1);

		ItemQueryVO itemQueryVO = new ItemQueryVO();

		itemQueryVO.setCompanyNo(companyNo);

		itemQueryVO.setEndGmt(DateUtil.convertDate2Str(endTime,DateUtil.formateStr19));
		itemQueryVO.setStartGmt(DateUtil.convertDate2Str(startTime,DateUtil.formateStr19));

		List<ItemDTO> itemDTOList = itemDOMapperExt.queryMallItems(itemQueryVO);

		for(ItemDTO itemDTO : itemDTOList){
			dealMainPicUrl(itemDTO);
		}
		return itemDTOList;
	}


	private void dealMainPicUrl(ItemDTO itemDTO){
		if(EasyUtil.isStringEmpty(itemDTO.getMainPic())) {
			PicModel pm = HaiJsonUtils.toBean(itemDTO.getMainPic(), PicModel.class);
			if(pm.getMainPicNum() == null) {pm.setMainPicNum("1");}
			String imgSrc = pm.getPicList().get(0).getUrl();
			itemDTO.setMainPic(imgSrc);
		}
	}




}
