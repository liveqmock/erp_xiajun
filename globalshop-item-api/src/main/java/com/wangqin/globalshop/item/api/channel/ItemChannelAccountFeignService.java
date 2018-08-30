package com.wangqin.globalshop.item.api.channel;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "itemApi")
public interface ItemChannelAccountFeignService extends ItemChannelAccountService{

}
