package com.wangqin.globalshop.common.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
	
	public static Map<String, String> getCategoryKuaiDi() {
		Map<String, String> category = new HashMap<String, String>();
		category.put("韵达快运", "YD");
		category.put("百世汇通", "huitongkuaidi");//特殊暂且不支持的公司取调取快递100 
		category.put("EMS", "EMS");
		category.put("韵达", "YD");
		category.put("shentong", "STO");
		category.put("申通", "STO");
		category.put("顺丰", "SF");
		category.put("zhongtong", "ZTO");
		category.put("tiantian", "tiantian");//特殊暂且不支持的公司取调取快递100 
		return category;

	}
	public static Map<String, Object> getCategoryMap() {
		Map<String, Object> category = new HashMap<String, Object>();
		category.put("面部护肤", "09020100004");
		category.put("美发护发", "27000000070");
		category.put("身体护理", "09020100004");
		category.put("口腔护理", "11021140001");
		category.put("女士护理", "09020100004");//
		category.put("男士护理", "09020100004");//
		category.put("彩妆香水", "09010100002");//
		category.put("潮流女包", "06010200010");
		category.put("时尚男包", "06010300003");
		category.put("精品钱包", "06010300003");
		category.put("生活用品", "11021140001");
		category.put("生活电器", "11011600001");
		category.put("厨房电器", "11019990003");
		category.put("宠物用品", "01019900067");
		category.put("精品美食", "01019900037");
		category.put("流行饰品", "08010000006");
		category.put("女装", "04019900007");
		category.put("男装", "04010400001");
		category.put("鞋靴", "06029900006");
		category.put("配饰", "08010000005");
		category.put("美妆护肤", "09020100004");
		category.put("箱包皮具", "06010100003");
		category.put("家庭日用", "11020100021");
		category.put("潮流服饰", "04010200003");
		return category;

	}
}
