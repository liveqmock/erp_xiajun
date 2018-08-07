package com.wangqin.globalshop.company.service.impl;

import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyEditVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CompanyDOMapperExt;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.common.utils.Md5Util;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.company.service.*;
import com.wangqin.globalshop.item.app.service.IAppletConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author angus
 * @date 2018/8/6
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDOMapperExt companyDOMapper;

    @Autowired
    private AuthOrganizationService authOrganizationService;

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthUserRoleService authUserRoleService;

    @Autowired
    private AuthRoleResourceService authRoleResourceService;

    @Autowired
    private IAppletConfigService appletConfigService;

    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public void addCompany(CompanyEditVO companyEditVO) {

        if (StringUtil.isBlank(companyEditVO.getCompanyName()) || StringUtil.isBlank(companyEditVO.getLoginName()) ||
                StringUtil.isBlank(companyEditVO.getPassword())) {
            throw new BizCommonException("信息不完整！");
        }

        // 创建商户
        CompanyDO companyDO = new CompanyDO();
        String companyNo = CodeGenUtil.getCompanyNo();
        String creator = AppUtil.getLoginUserId();
        String modifier = AppUtil.getLoginUserId();
        try {
            // 商户必需字段
            companyDO.setCompanyNo(companyNo);
            companyDO.setCreator(creator);
            companyDO.setModifier(modifier);
            companyDO.setCompanyName(companyEditVO.getCompanyName());
            companyDO.setStatus(0);
            companyDO.setForceIdcard(1);
            companyDO.setTel(companyEditVO.getPhone());
            companyDO.setIm("微信");
            // 商户可选字段
            companyDO.setLogoUrl(companyEditVO.getLogoUrl());
            companyDO.setIntro(companyEditVO.getIntro());
            companyDO.setState(companyEditVO.getState());
            companyDO.setCity(companyEditVO.getCity());
            companyDO.setDistrict(companyEditVO.getDistrict());
            companyDO.setFullAddress(companyEditVO.getFullAddress());
            companyDO.setCountry(companyEditVO.getCountry());
            companyDO.setOverseaAddress(companyEditVO.getOverseaAddress());
            companyDO.setMainCategory(companyEditVO.getMainCategory());
            companyDO.setOfflineAnnualSale(companyEditVO.getOfflineAnnualSale());
            companyDO.setOnlineAnnualSale(companyEditVO.getOnlineAnnualSale());
            companyDOMapper.insertSelective(companyDO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizCommonException("公司新增失败！");
        }

        // 创建部门（公司总部）
        addAuthOrganization(companyEditVO, companyNo, creator, modifier);

        // 创建角色（所有）
        Long roleId = CodeGenUtil.getRoleId();
        addAuthRole(companyNo, roleId, creator, modifier);

        // 创建用户（管理员）
        Long userId = addAuthUser(companyEditVO, companyNo, creator, modifier);

        // 创建用户与角色对应关系（管理员）
        addAuthUserRole(companyNo, userId, roleId, creator, modifier);

        // 创建资源（管理员）
        addAuthRoleResource(companyNo, roleId, creator, modifier);

        // 添加 applet_config
        addAppletConfig(companyEditVO, companyNo, creator, modifier);
    }


    /**
     * 创建部门（公司总部）
     *
     * @param companyEditVO
     * @param companyNo
     * @param creator
     * @param modifier
     */
    private void addAuthOrganization(CompanyEditVO companyEditVO, String companyNo, String creator, String modifier) {
        AuthOrganizationDO authOrganizationDO = new AuthOrganizationDO();
        String orgId = CodeGenUtil.getOrgId();
        authOrganizationDO.setCompanyNo(companyNo);
        authOrganizationDO.setCreator(creator);
        authOrganizationDO.setModifier(modifier);
        authOrganizationDO.setOrgId(orgId);
        authOrganizationDO.setCode(orgId);
        authOrganizationDO.setName("公司总部");
        authOrganizationDO.setAddress(companyEditVO.getState() + " "
                + companyEditVO.getCity() + " "
                + companyEditVO.getDistrict() + " "
                + companyEditVO.getFullAddress());
        authOrganizationDO.setSeq(1);
        authOrganizationService.addAuthOrganization(authOrganizationDO);
    }

    /**
     * 创建所有角色
     *
     * @param companyNo
     * @param roleId
     * @param creator
     * @param modifier
     */
    private void addAuthRole(String companyNo, Long roleId, String creator, String modifier) {
        // 创建角色（所有）
        AuthRoleDO authRoleDO = new AuthRoleDO();
        authRoleDO.setCompanyNo(companyNo);
        authRoleDO.setCreator(creator);
        authRoleDO.setModifier(modifier);
        // 角色不同字段
        String[] names = {"买手", "新成员", "买手主管", "销售代理"};
        for (String name : names) {
            authRoleDO.setRoleId(CodeGenUtil.getRoleId());
            authRoleDO.setName(name);
            authRoleService.addAuthRole(authRoleDO);
        }
        authRoleDO.setRoleId(roleId);
        authRoleDO.setName("公司管理员");
        authRoleService.addAuthRole(authRoleDO);
    }

    /**
     * 创建用户（管理员）
     *
     * @param companyEditVO
     * @param companyNo
     * @param creator
     * @param modifier
     */
    private Long addAuthUser(CompanyEditVO companyEditVO, String companyNo, String creator, String modifier) {
        AuthUserDO authUserDO = new AuthUserDO();
        authUserDO.setCompanyNo(companyNo);
        authUserDO.setCreator(creator);
        authUserDO.setModifier(modifier);
        authUserDO.setUserNo(CodeGenUtil.genUserNo());
        authUserDO.setLoginName(companyEditVO.getLoginName());
        authUserDO.setPassword(Md5Util.getMD5(companyEditVO.getPassword()));
        authUserDO.setName(companyEditVO.getName());
        authUserDO.setPhone(companyEditVO.getPhone());
        authUserDO.setEmail(companyEditVO.getEmail());
        authUserService.addAuthUser(authUserDO);
        return authUserDO.getId();
    }

    /**
     * 创建用户与角色对应关系（管理员）
     *
     * @param companyNo
     * @param userId
     * @param roleId
     * @param creator
     * @param modifier
     */
    private void addAuthUserRole(String companyNo, Long userId, Long roleId, String creator, String modifier) {
        AuthUserRoleDO authUserRoleDO = new AuthUserRoleDO();
        authUserRoleDO.setCompanyNo(companyNo);
        authUserRoleDO.setUserId(userId);
        authUserRoleDO.setRoleId(roleId);
        authUserRoleDO.setCreator(creator);
        authUserRoleDO.setModifier(modifier);
        authUserRoleService.addAuthUserRole(authUserRoleDO);
    }


    /**
     * 创建资源（管理员）
     *
     * @param companyNo
     * @param roleId
     * @param creator
     * @param modifier
     */
    private void addAuthRoleResource(String companyNo, Long roleId, String creator, String modifier) {
        AuthRoleResourceDO authRoleResourceDO = new AuthRoleResourceDO();
        authRoleResourceDO.setCompanyNo(companyNo);
        authRoleResourceDO.setCreator(creator);
        authRoleResourceDO.setModifier(modifier);
        authRoleResourceDO.setRoleId(roleId);
        Long[] resourceIds = new Long[]{1L, 11L, 111L, 112L, 113L, 114L, 12L, 121L, 122L, 123L, 124L, 125L, 13L, 131L,
                132L, 133L, 134L, 14L, 141L, 142L, 143L, 144L, 289L, 290L, 293L, 303L, 299L, 300L, 231L, 259L, 260L,
                284L, 261L, 262L, 294L, 263L, 264L, 265L, 266L, 277L, 304L, 267L, 268L, 269L, 297L, 298L, 270L, 271L,
                272L, 302L, 273L, 274L, 275L, 276L, 285L, 295L, 221L, 227L, 228L, 229L};
        for (Long resourceId : resourceIds) {
            authRoleResourceDO.setResourceId(resourceId);
            authRoleResourceService.addAuthRoleResource(authRoleResourceDO);
        }
    }

    /**
     * 添加 applet_config
     *
     * @param companyEditVO
     * @param companyNo
     * @param creator
     * @param modifier
     */
    private void addAppletConfig(CompanyEditVO companyEditVO, String companyNo, String creator, String modifier) {
        AppletConfigDO appletConfigDO = new AppletConfigDO();
        appletConfigDO.setCompanyNo(companyNo);
        appletConfigDO.setCreator(creator);
        appletConfigDO.setModifier(modifier);
        appletConfigDO.setStatus(companyEditVO.getStatus());
        appletConfigDO.setMchId(companyEditVO.getMchId());
        appletConfigDO.setPayKey(companyEditVO.getPayKey());
        appletConfigDO.setAppletType("2");
        appletConfigService.addAppletConfig(appletConfigDO);
    }
}