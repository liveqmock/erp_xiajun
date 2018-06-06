package com.wangqin.globalshop.item.app.channel;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;

@Retention(RetentionPolicy.RUNTIME)
public @interface Channel {
	ChannelType type();
}