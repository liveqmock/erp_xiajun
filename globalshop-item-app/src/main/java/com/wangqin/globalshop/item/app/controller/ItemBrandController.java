package com.wangqin.globalshop.item.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.bean.dataVo.BuyerStorageDetailVo;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.item.app.feign.BaseResponseDto;
import com.wangqin.globalshop.item.app.feign.ItemApiFeignClient;
import com.wangqin.globalshop.item.app.feign.ItemBrandDto;
import org.apache.poi.hssf.record.formula.functions.T;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemBrandQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.item.app.service.IItemBrandService;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(value = "/item/brand")
@Authenticated
public class ItemBrandController {

	@Autowired
	private IItemBrandService itemBrandService;


	//@Autowired ItemApiFeignClient itemApiFeignClient; //第一种方案


	@Autowired RestTemplate restTemplate;  //第二种方案

	/**
	 * 第一种方案：@FeignClient 证明没弄好
	 * 根据name模糊匹配
	 *
	 * @param
	 * @return
	 */
//	@RequestMapping("/queryByName")
//	@ResponseBody
//	public Object query(String name) {
//		JsonResult<List<ItemBrandDto>> result = new JsonResult<>();
//		BaseResponseDto<List<ItemBrandDto>> itemApiResult = itemApiFeignClient.brandName(name);
//		List<ItemBrandDto> dataList = itemApiResult.getData();
//		result.setData(dataList);
//		return result.buildIsSuccess(true);
//	}


	@GetMapping("/health")
	public String home(){
		System.out.println(new Date());
		return "hello world";
	}

	/**
	 *第二种方案：直接访问地址
	 * @return
	 */
	@GetMapping("/itemApi")
	@ResponseBody
	public Object itemApi(){
		JsonResult<String> result = new JsonResult<>();
		String demoApi =  restTemplate.getForObject("http://itemApi/health", String.class);
		result.setData(demoApi);
		return result.buildIsSuccess(true);
	}


	/**
	 *第二种方案：直接访问地址，参数传递不过去
	 * @return
	 */
	@GetMapping("/itemApiname")
	@ResponseBody
	public Object itemApiname(){
		JsonResult<BaseResponseDto> result = new JsonResult<>();
		Map<String,String> param = new HashMap<>();
		param.put("name","迪奥");
		String name = "迪奥";
		String resultStr =  restTemplate.getForObject("http://itemApi/api/brand", String.class,name);
		BaseResponseDto<List<ItemBrandDto>> data = BaseDto.fromJson(resultStr,new TypeReference<BaseResponseDto<List<ItemBrandDto>>>(){});
		result.setData(data);
		return result.buildIsSuccess(true);
	}


	/**
	 *第二种方案：直接访问地址，搞定
	 * @return
	 */
	@GetMapping("/postbrand")
	@ResponseBody
	public Object postbrand(){
		JsonResult<BaseResponseDto> result = new JsonResult<>();
		Map<String,String> param = new HashMap<>();
		param.put("name","迪奥");
		String name = "迪奥";

		MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("name", "迪奥");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);
		String resultStr =  restTemplate.postForObject("http://itemApi/api/brand",r,String.class);
		BaseResponseDto<List<ItemBrandDto>> data = BaseDto.fromJson(resultStr,new TypeReference<BaseResponseDto<List<ItemBrandDto>>>(){});
		result.setData(data);
		return result.buildIsSuccess(true);
	}


	/**
	 * 添加品牌(fin)
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object add(ItemBrandDO brand) {
		JsonResult<ItemBrandDO> result = new JsonResult<>();
		brand.setBrandNo("b"+RandomUtils.getTimeRandom());
		brand.setCreator(AppUtil.getLoginUserId());
		brand.setModifier(AppUtil.getLoginUserId());
		 if(itemBrandService.selectBrandNoByName(brand.getName()) != null) {
			 return result.buildMsg("添加失败，品牌已存在").buildIsSuccess(false);
		 }
		itemBrandService.insertBrandSelective(brand);
		return result.buildIsSuccess(true);
	}

	/**
	 * 根据id查找品牌(fin)
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(Long id) {
		JsonResult<ItemBrandDO> result = new JsonResult<>();
		ItemBrandDO brand = itemBrandService.selectByPrimaryKey(id);
		result.setData(brand);
		return result.buildIsSuccess(true);
	}

	/**
	 * 修改品牌(fin)
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object update(ItemBrandDO brand) {
		JsonResult<ItemBrandDO> result = new JsonResult<>();
		if (StringUtil.isBlank(brand.getName())) {
			return result.buildMsg("皮牌英文名不能为空").buildIsSuccess(false);
		}
//		/**zhangziyang**/
//		if(itemBrandService.selectBrandNoByName(brand.getName()) != null || !"".equals(itemBrandService.selectBrandNoByName(brand.getName()))) {
//			 return result.buildMsg("添加失败，品牌已存在").buildIsSuccess(false);
//		 }
		List<Long> idList = itemBrandService.queryIdListByBrandName(brand.getName());
		for(Long id:idList) {
			if(!id.equals(brand.getId())) {
				return result.buildIsSuccess(false).buildMsg("品牌英文名不能和已有的品牌重合");
			}
		}
		brand.setModifier(AppUtil.getLoginUserId());
		itemBrandService.updateBrand(brand);
		return result.buildIsSuccess(true);
	}

	/**
	 * 删除品牌(fin)
	 *
	 * @param
	 * @return
	 */
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long id) {
		JsonResult<ItemBrandDO> result = new JsonResult<>();
		itemBrandService.deleteItemBrandById(id);
		return result.buildIsSuccess(true);
	}

	/**
	 * 分页查询(fin)，用在品牌管理部分
	 */
	@RequestMapping("/queryBrands")
	@ResponseBody
	public Object queryItemBrandDOs(ItemBrandQueryVO brandQueryVO) {
		JsonPageResult<List<ItemBrandDO>> result = new JsonPageResult<>();
		result = itemBrandService.queryBrands(brandQueryVO);
		return result.buildIsSuccess(true);
	}

	/**
	 * 总查询，用在商品列表(fin)
	 */
	
	@RequestMapping("/queryAllBrand")
	@ResponseBody
	public Object queryItemBrandDOpage(ItemBrandDO brand) {
		JsonResult<List<ItemBrandDO>> result = itemBrandService.queryAllBrand();
		return result.buildIsSuccess(true);
	}
	

}
