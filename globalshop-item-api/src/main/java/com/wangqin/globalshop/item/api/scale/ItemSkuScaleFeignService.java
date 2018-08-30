package com.wangqin.globalshop.item.api.scale;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "itemApi")
public interface ItemSkuScaleFeignService extends ItemSkuScaleService {

}
