package com.wangqin.globalshop.item.api.sku;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "itemApi")
public interface ItemSkuFeignService extends ItemSkuService{

}
