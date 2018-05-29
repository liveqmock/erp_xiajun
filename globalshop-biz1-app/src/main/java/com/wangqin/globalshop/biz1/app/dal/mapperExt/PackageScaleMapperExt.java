package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ShippingPackingScaleDOMapper;



public interface PackageScaleMapperExt extends ShippingPackingScaleDOMapper {
	
	//Integer queryPackageScalesCount(@Param("name") String name, @Param("enName") String enName);
	
	List<ShippingPackingScaleDO> queryPackageScales();
}
