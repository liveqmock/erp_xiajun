package com.wangqin.globalshop.item.app.channel;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountSo;

import com.wangqin.globalshop.common.scan.SpringUtils;
import com.wangqin.globalshop.common.utils.ClassUtil;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelFactory {
	// 公司_渠道 : 渠道实例
	static Map<String, IItemChannelService> channelMap = new HashMap<String, IItemChannelService>(5);
	
	static Map<Integer, Constructor<?>> constructorMap = new HashMap<Integer, Constructor<?>>();
	
	static {
		// 扫描渠道包下的class, 保存各个渠道的构造函数
		List<Class<IItemChannelService>> classList = ClassUtil.getAllClassByInterface(IItemChannelService.class);
        for (Class<IItemChannelService> clazz : classList) {
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
	


	/**
	 *
	 * @param channelAccount
	 * @return
	 * @throws Exception
	 */
	synchronized static public IItemChannelService getChannel(ChannelAccountDO channelAccount) throws Exception {
		String companyNo = channelAccount.getCompanyNo();
		ChannelType channelType = ChannelType.getChannelType(channelAccount.getType());
		String shopCode = channelAccount.getShopCode();
		String keyStr = companyNo + "_" + channelType + "_" + shopCode;
		IItemChannelService service = channelMap.get(keyStr);//缓存service，只在第一次调用的时候初始化一次
		if (service != null) {
			return service;
		}
		Constructor<?> constructor = constructorMap.get(channelType.getValue());
		if (constructor == null) {
			throw new Exception("找不到构造函数!! companyNo: " + companyNo + " , channel: " + channelType.getName());
		}				
		service = (IItemChannelService)constructor.newInstance(channelAccount);
		if (service != null) {
			channelMap.put(keyStr, service);
		}
		
		return service;
	}
	/**
	 * 有赞目前就一个店铺，先默认
	 * @param companyNo
	 * @param channelType
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	synchronized static public IItemChannelService getChannel(String companyNo, ChannelType channelType) throws Exception {
		String keyStr = companyNo + "_" + channelType ;
		IItemChannelService service = channelMap.get(keyStr);
		if (service != null) {
			return service;
		}
		Constructor<?> constructor = constructorMap.get(channelType.getValue());
		if (constructor == null) {
			throw new Exception("找不到构造函数!! companyNo: " + companyNo + " , channel: " + channelType.getName());
		}
		IItemChannelAccountService channelAccountService = SpringUtils.getBean(IItemChannelAccountService.class);
		ChannelAccountSo tmEntity = new ChannelAccountSo();
		tmEntity.setCompanyNo(companyNo);
		tmEntity.setType(channelType.getValue());
		ChannelAccountDO selectEntity = channelAccountService.queryPo(tmEntity);
		if(selectEntity != null) {
			service = (IItemChannelService)constructor.newInstance(selectEntity);
		}else {
			throw new Exception("无对应渠道 " + companyNo + " , channel: " + channelType.getName());
		}

		if (service != null) {
			channelMap.put(keyStr, service);
		}

		return service;
	}


}
