package com.wangqin.globalshop.item.api.item;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Create by 777 on 2018/8/26
 */
@FeignClient(name = "itemApi")
public interface ItemFeignService extends ItemApi {
}
