package com.wangqin.globalshop.biz1.app.service.channel;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.common.utils.ClassUtil;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelFactory {
	// 公司_渠道 : 渠道实例
	static Map<String, IChannelService> channelMap = new HashMap<String, IChannelService>(5);
	
	static Map<Integer, Constructor<?>> constructorMap = new HashMap<Integer, Constructor<?>>();
	
	static {
		// 扫描渠道包下的class, 保存各个渠道的构造函数
		List<Class<IChannelService>> classList = ClassUtil.getAllClassByInterface(IChannelService.class);
        for (Class<IChannelService> clazz : classList) {
            Channel channelAnno = clazz.getAnnotation(Channel.class);
            if (channelAnno != null) {
            	ChannelType type = channelAnno.type();
            	
            	try {
            		Constructor<?> constructor = clazz.getConstructor(ChannelAccountDO.class);
            		constructorMap.put(type.getValue(), constructor);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }
	}
	
//	synchronized static public IChannelService getChannel(Long companyId, ChannelType channelType) throws Exception {
//		String keyStr = companyId + "_" + channelType;
//		IChannelService service = channelMap.get(keyStr);
//		if (service != null) {
//			return service;
//		}
//		Constructor<?> constructor = constructorMap.get(channelType.getValue());
//		if (constructor == null) {
//			throw new Exception("找不到构造函数!! companyId: " + companyId + " , channel: " + channelType.getName());
//		}
//		IChannelAccountService channelAccountService = SpringUtils.getBean(IChannelAccountService.class);
//		ChannelAccountDO tmEntity = new ChannelAccountDO();
//		tmEntity.setCompanyNo(companyId+"");
//		tmEntity.setType(channelType.getValue());
//		ChannelAccountDO selectEntity = channelAccountService.selectOne(tmEntity);
//		if(selectEntity != null) {
//			service = (IChannelService)constructor.newInstance(selectEntity);
//		}else {
//			throw new Exception("无对应渠道 " + companyId + " , channel: " + channelType.getName());
//		}
//
//
//		if (service != null) {
//			channelMap.put(keyStr, service);
//		}
//
//		return service;
//	}
	
	/**
	 * 获取单个渠道授权新方法，companyId+channel+shopCode才能确定渠道唯一性
	 * @param companyId
	 * @param channelType
	 * @param shopCode
	 * @return
	 * @throws Exception
	 */
//	synchronized static public IChannelService getChannel(Long companyId, ChannelType channelType, String shopCode) throws Exception {
//		String keyStr = companyId + "_" + channelType;
//		IChannelService service = channelMap.get(keyStr);
//		if (service != null) {
//			return service;
//		}
//		Constructor<?> constructor = constructorMap.get(channelType.getValue());
//		if (constructor == null) {
//			throw new Exception("找不到构造函数!! companyId: " + companyId + " , channel: " + channelType.getName());
//		}
//		IChannelAccountService channelAccountService = SpringUtils.getBean(IChannelAccountService.class);
//		ChannelAccountDO tmEntity = new ChannelAccountDO();
//		tmEntity.setCompanyNo(companyId+"");
//		tmEntity.setType(channelType.getValue());
//		ChannelAccountDO selectEntity = channelAccountService.selectOne(tmEntity);
//		if(selectEntity != null) {
//			service = (IChannelService)constructor.newInstance(selectEntity);
//		}else {
//			throw new Exception("无对应渠道 " + companyId + " , channel: " + channelType.getName() + " , shopCode: "+shopCode);
//		}
//
//		if (service != null) {
//			channelMap.put(keyStr, service);
//		}
//
//		return service;
//	}

	/**
	 * 获取单个渠道授权新方法，channelAccount直接拉出来
	 * @param channelAccount
	 * @return
	 * @throws Exception
	 */
	synchronized static public IChannelService getChannel(ChannelAccountDO channelAccount) throws Exception {
		String companyNo = channelAccount.getCompanyNo();
		ChannelType channelType = ChannelType.getChannelType(channelAccount.getType());
		String keyStr = companyNo + "_" + channelType;
		IChannelService service = channelMap.get(keyStr);//缓存service，只在第一次调用的时候初始化一次
		if (service != null) {
			return service;
		}
		Constructor<?> constructor = constructorMap.get(channelType.getValue());
		if (constructor == null) {
			throw new Exception("找不到构造函数!! companyNo: " + companyNo + " , channel: " + channelType.getName());
		}				
		service = (IChannelService)constructor.newInstance(channelAccount);
		if (service != null) {
			channelMap.put(keyStr, service);
		}
		
		return service;
	}
	

}
