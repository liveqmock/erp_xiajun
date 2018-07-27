package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.channel.service.channel.Channel;
import com.wangqin.globalshop.common.utils.ClassUtil;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by 777 on 2018/6/12
 */
public class JdShopFactory {

	// 公司_渠道 : 渠道实例
	static Map<String, JdShopService> channelMap = new HashMap<String, JdShopService>(5);

	static Map<Integer, Constructor<?>> constructorMap = new HashMap<Integer, Constructor<?>>();

	static {
		// 扫描渠道包下的class, 保存各个渠道的构造函数
		List<Class<JdShopService>> classList = ClassUtil.getAllClassByInterface(JdShopService.class);
		for (Class<JdShopService> clazz : classList) {
			Channel channelAnno = clazz.getAnnotation(Channel.class);
			if (channelAnno != null) {
				ChannelType type = channelAnno.type();

				try {
					Constructor<?> constructor = clazz.getConstructor(JdShopOauthDO.class);
					constructorMap.put(type.getValue(), constructor);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}



	/**
	 *
	 * @param jdShopOauthDo
	 * @return
	 * @throws Exception
	 */
	synchronized static public JdShopService getChannel(JdShopOauthDO jdShopOauthDo) throws Exception {
		String companyNo = jdShopOauthDo.getCompanyNo();
		ChannelType channelType = ChannelType.getChannelType(Integer.valueOf(jdShopOauthDo.getChannelNo()));
		String shopCode = jdShopOauthDo.getShopCode();
		String keyStr = companyNo + "_" + channelType + "_" + shopCode;
		JdShopService service = channelMap.get(keyStr);//缓存service，只在第一次调用的时候初始化一次
		if (service != null) {
			return service;
		}
		Constructor<?> constructor = constructorMap.get(channelType.getValue());
		if (constructor == null) {
			throw new Exception("找不到构造函数!! companyNo: " + companyNo + " , channel: " + channelType.getName());
		}
		service = (JdShopService)constructor.newInstance(jdShopOauthDo);
		if (service != null) {
			channelMap.put(keyStr, service);
		}

		return service;
	}



}
