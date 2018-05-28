package com.wangqin.globalshop.biz1.app.service.channel;


import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Channel {
	ChannelType type();
}
