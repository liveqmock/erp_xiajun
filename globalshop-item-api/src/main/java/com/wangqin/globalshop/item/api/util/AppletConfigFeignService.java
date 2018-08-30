package com.wangqin.globalshop.item.api.util;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "itemApi")
public interface AppletConfigFeignService extends AppletConfigService{

}
