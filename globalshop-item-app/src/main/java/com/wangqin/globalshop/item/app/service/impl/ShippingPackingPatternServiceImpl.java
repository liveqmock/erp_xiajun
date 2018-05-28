package com.wangqin.globalshop.item.app.service.impl;


import src.main.java.com.wangqin.globalshop.item.app.service.IShippingPackingPatternService;





@Service
public class ShippingPackingPatternServiceImpl  implements IShippingPackingPatternService {

	@Autowired
	private ShippingPackingPatternDOMapperExt packageLevelMapper;	
	
	@Override
	public Integer queryPackageLevelsCount(ShippingPackingPatternQueryVO packageLevelQueryVO)
	{
		return packageLevelMapper.queryPackageLevelsCount(packageLevelQueryVO);
	}
	
	@Override
	public JsonPageResult<List<ShippingPackingPatternDO>> queryPackageLevelList(ShippingPackingPatternQueryVO packageLevelQueryVO) {
		JsonPageResult<List<ShippingPackingPatternDO>> packageLevelResult = new JsonPageResult<>();
		
		Integer totalCount = packageLevelMapper.queryPackageLevelsCount(packageLevelQueryVO);
		
		if ((null != totalCount) && (0L != totalCount)) {
			packageLevelResult.buildPage(totalCount, packageLevelQueryVO);
			
			List<ShippingPackingPatternDO> packageLevels = packageLevelMapper.queryPackageLevels(packageLevelQueryVO);
			packageLevelResult.setData(packageLevels);			
		} else {
			List<ShippingPackingPatternDO> packageLevels = new ArrayList<>();
			packageLevelResult.setData(packageLevels);
		}
		return packageLevelResult;
	}
	
	/**这个方法在Controller层根本没有用到
	public Boolean updatePackageScaleByEnName(Long packageId, String newEnName) {
				
		ShippingPackingPatternQueryVO packageLevelQueryVO = new ShippingPackingPatternQueryVO();
		packageLevelQueryVO.setFirstStart(0);
		packageLevelQueryVO.setPackageId(packageId);;
		List<ShippingPackingPatternDO> list = packageLevelMapper.queryPackageLevels(packageLevelQueryVO);
		
		if (list.size() > 0) {

			for (ShippingPackingPatternDO packageLevel : list) {
				packageLevel.setNameEn(newEnName);
			}
			
			 Integer count = packageLevelMapper.updateBatchById(list);
			 
			 return ((null != count) && (count > 0));
		} else {
			return true;
		}
	}
	**/
	
	@Override
	public ShippingPackingPatternDO selectByPrimaryKey(Long id) {
		return packageLevelMapper.selectByPrimaryKey(id);
		
	}
	
	

}
