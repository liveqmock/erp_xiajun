package com.wangqin.globalshop.item.app.feign;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Create by 777 on 2018/8/23
 */

@FeignClient(name="itemApi") //服务器名称,与consul注册一致
public interface ItemApiFeignClient {

	@RequestMapping(value="/api/brandEntity", method = RequestMethod.GET)
	public BaseResponseDto<List<ItemBrandDto>> brandName(@RequestParam("name")String name); //注意@RequestParam必填


	@RequestMapping(value="/api/add", method = RequestMethod.POST)
	public BaseResponseDto<String> add(@RequestParam("brand") ItemBrandDO brand);
}
