package com.wangqin.globalshop.item.app.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Create by 777 on 2018/8/23
 */

@FeignClient(name="itemApi") //服务器名称,与consul注册一致
public interface ItemApiFeignClient {

	@RequestMapping(value="/api/brand", method = RequestMethod.GET)
	public BaseResponseDto<List<ItemBrandDto>> brandName(String name);


}
