package com.wangqin.globalshop.deal.app.controller;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerDO;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.deal.app.service.IDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 
 * Title: DealerController.java
 * Description: Dealer Controller
 *
 * @author jc
 * Mar 18, 2017
 *
 */

@Controller
@RequestMapping("/seller")
public class DealerController {

	@Autowired
	private IDealerService iDealerService;

//    @Autowired
//    private IUserService userService;

	@RequestMapping("/add")
	@ResponseBody
	public Object add(DealerDO seller) {
		JsonResult<String> result = new JsonResult<>();
		iDealerService.insert(seller);
//
//		ShiroUser shiroUser = ShiroUtil.getShiroUser();
//		if (shiroUser == null) {
//			return result.buildIsSuccess(false).buildMsg("未登陆");
//		}else{
//			seller.setUserId(shiroUser.getId());
//			if(null ==shiroUser.getCompanyId())
//			{
//				seller.setCompanyId(shiroUser.getCompanyId());
//			}
//		}
//
//        if (null == seller.getCompanyId() && null != seller.getUserId()) {
//            User user = userService.selectById(seller.getUserId());
//            if (null != user) {
//                seller.setCompanyId(user.getCompanyId());
//            }
//        }
//
//		if (null == seller.getId()) {
//			DealerQueryVO sellerQueryVO = new DealerQueryVO();
//			sellerQueryVO.setCode(seller.getCode());
//			//Check code is unique
//			Integer count = iDealerService.queryDealersCount(sellerQueryVO);
//			if (count > 0) {
//				result.buildMsg("经销商代码不可以重复").buildIsSuccess(false);
//			} else {
//				seller.setGmtCreate(new Date());
//				seller.setGmtModify(new Date());
//				result.buildIsSuccess(iDealerService.insert(seller));
//			}
//		} else {
//			result.buildMsg("错误数据").buildIsSuccess(false);
//		}
//
//
//        return result;
		return result.buildIsSuccess(true);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(DealerDO seller) {
//		JsonResult<String> result = new JsonResult<>();
//
//		if (null != seller.getId()) {
//			DealerDO tjDealer = new Dealer();
//			tjDealer.setCode(seller.getCode());
//			DealerDO selDealer = iDealerService.selectOne(tjDealer);
//			if(selDealer!=null && selDealer.getId().equals(seller.getId())) {
//				return result.buildMsg("经销商代码不可以重复").buildIsSuccess(false);
//			}
//
//
//			seller.setGmtCreate(null);
//			seller.setGmtModify(new Date());
//			result.buildIsSuccess(iDealerService.updateSelectiveById(seller));
//		} else {
//			result.buildMsg("错误数据").buildIsSuccess(false);
//		}
//
//		return result;
		return null;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(DealerDO seller) {
//		JsonResult<String> result = new JsonResult<>();
//
//		if (null != seller.getId()) {
//			Integer num = iDealerService.querySaleCountInOrder(seller.getId());
//			if(num!=null && num>0) {
//				result.buildMsg("此销售人员已产生订单，不能删除！").buildIsSuccess(false);
//			} else {
//				result.buildIsSuccess(iDealerService.deleteSelective(seller));
//			}
//		} else {
//			result.buildMsg("错误数据").buildIsSuccess(false);
//		}
		
//		return result;
		return null;
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Object query(Long id) {
		JsonResult<DealerDO> result = new JsonResult<>();
//		return result.buildData(iDealerService.selectById(id)).buildIsSuccess(true);
		return null;
	}
	
	@RequestMapping("/querySellerList")
	@ResponseBody
	public Object queryDealerList() {
		JsonResult<List<DealerDO>> result = new JsonResult<>();
		List<DealerDO> list = iDealerService.list();
		result.setData(list);
//		try {
//			sellerQueryVO.setCompanyId(ShiroUtil.getShiroUser().getCompanyId());
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
		
		//如果是代理
//		ShiroUser shiroUser = this.getShiroUser();
//		Set<String> roles = shiroUser.getRoles();
//		if(roles.contains("irhdaili")) {
//			String[] logingNameArr = shiroUser.getLoginName().split("_");
//			if(logingNameArr.length<2 || StringUtils.isBlank(logingNameArr[1])) {
//				throw new ErpCommonException("用户权限异常");
//			}
//			sellerQueryVO.setId(Integer.parseInt(logingNameArr[1]));
//		}
//		JsonPageResult<List<Dealer>> result = iDealerService.queryDealerList(sellerQueryVO);
//
//		return result.buildIsSuccess(true);
		return result.buildIsSuccess(true);
	}
}
