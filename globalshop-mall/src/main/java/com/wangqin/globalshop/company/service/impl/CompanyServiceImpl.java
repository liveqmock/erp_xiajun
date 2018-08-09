package com.wangqin.globalshop.company.service.impl;

import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyDetailVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyItemVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CompanyDOMapperExt;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.company.service.*;
import com.wangqin.globalshop.item.app.service.IAppletConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void addCompany(CompanyDetailVO companyDetailVO) {
        // 校验表单
        validateForm(companyDetailVO, false);

        // 基础信息
        String companyNo = CodeGenUtil.getCompanyNo();
        String creator = AppUtil.getLoginUserId();
        String modifier = AppUtil.getLoginUserId();

        // 创建用户（公司管理员）
        String adminNo = CodeGenUtil.genUserNo();
        Long adminId = addAuthUser(companyDetailVO, companyNo, adminNo, creator, modifier);

        // 创建商户
        addCompany(companyDetailVO, companyNo, adminNo, creator, modifier);

        // 创建部门（公司总部）
        addAuthOrganization(companyDetailVO, companyNo, creator, modifier);

        // 创建角色（所有）
        Long roleId = CodeGenUtil.getRoleId();
        addAuthRole(companyNo, roleId, creator, modifier);

        // 创建用户与角色对应关系（公司管理员所属）
        addAuthUserRole(companyNo, adminId, roleId, creator, modifier);

        // 创建资源（公司管理员所属）
        addAuthRoleResource(companyNo, roleId, creator, modifier);

        // 添加 applet_config
        addAppletConfig(companyDetailVO, companyNo, creator, modifier);
    }

    @Override
    public CompanyDO getCompany(String companyNo) {
        if (companyNo == null) {
            throw new BizCommonException("信息不完整！");
        }
        CompanyDO companyDO = companyDOMapper.selectByCompanyNo(companyNo);
        companyDO.setLogoUrl(ImgUtil.initImg2Json(companyDO.getLogoUrl()));
        return companyDO;
    }

    @Override
    public CompanyDetailVO getCompanyDetailVO(String companyNo) {
        if (companyNo == null) {
            throw new BizCommonException("信息不完整！");
        }
        return companyDOMapper.getCompanyDetailVO(companyNo);
    }

    @Override
    public List<CompanyItemVO> listCompanies(CompanyQueryVO companyQueryVO, PageQueryParam pageQueryParam) {
        pageQueryParam.calculateRowIndex();
        return companyDOMapper.listCompanies(companyQueryVO, pageQueryParam);
    }

    @Override
    public int countCompanies(CompanyQueryVO companyQueryVO) {
        return companyDOMapper.countCompanies(companyQueryVO);
    }

    @Override
    public void updateCompany(CompanyDO companyDO) {
        if (companyDO.getCompanyNo() == null) {
            throw new BizCommonException("数据不完整！");
        }

        int effectedNum = companyDOMapper.updateByCompanyNo(companyDO);

        if (effectedNum <= 0) {
            throw new BizCommonException("数据库中无此记录！");
        }
    }

    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public void updateCompany(CompanyDetailVO companyDetailVO) {
        validateForm(companyDetailVO, true);
        // 更新商家信息
        CompanyDO companyDO = new CompanyDO();
        companyDO.setCompanyNo(companyDetailVO.getCompanyNo());
        companyDO.setLogoUrl(companyDetailVO.getLogoUrl());
        companyDO.setCompanyName(companyDetailVO.getCompanyName());
        companyDO.setIntro(companyDetailVO.getIntro());
        companyDO.setState(companyDetailVO.getState());
        companyDO.setCity(companyDetailVO.getCity());
        companyDO.setDistrict(companyDetailVO.getDistrict());
        companyDO.setFullAddress(companyDetailVO.getFullAddress());
        companyDO.setCountry(companyDetailVO.getCountry());
        companyDO.setOverseaAddress(companyDetailVO.getOverseaAddress());
        companyDO.setMainCategory(companyDetailVO.getMainCategory());
        companyDO.setOfflineAnnualSale(companyDetailVO.getOfflineAnnualSale());
        companyDO.setOnlineAnnualSale(companyDetailVO.getOnlineAnnualSale());
        updateCompany(companyDO);

        // 更新商家管理员/联系人信息
        AuthUserDO authUserDO = new AuthUserDO();
        authUserDO.setUserNo(companyDetailVO.getAdminNo());
        authUserDO.setCompanyNo(companyDetailVO.getCompanyNo());
        authUserDO.setLoginName(companyDetailVO.getLoginName());
        authUserDO.setName(companyDetailVO.getName());
        authUserDO.setPhone(companyDetailVO.getPhone());
        authUserDO.setEmail(companyDetailVO.getEmail());
        authUserService.updateAuthUser(authUserDO);

        // 更新 Applet config 信息
        AppletConfigDO appletConfigDO = new AppletConfigDO();
        appletConfigDO.setCompanyNo(companyDetailVO.getCompanyNo());
        appletConfigDO.setStatus(companyDetailVO.getStatus());
        appletConfigDO.setMchId(companyDetailVO.getMchId());
        appletConfigDO.setPayKey(companyDetailVO.getPayKey());
        appletConfigService.updateAppletConfig(appletConfigDO);
    }

    @Override
    public void disableCompany(String companyNo) {
        CompanyDO companyDO = new CompanyDO();
        companyDO.setCompanyNo(companyNo);
        // 将状态改为 1（关闭）
        companyDO.setStatus(1);
        updateCompany(companyDO);
    }

    @Override
    public void deleteCompany(String companyNo) {
        CompanyDO companyDO = new CompanyDO();
        companyDO.setCompanyNo(companyNo);
        // 软删除
        companyDO.setIsDel(true);
        updateCompany(companyDO);
    }

    /**
     * 校验表单
     *
     * @param companyDetailVO
     * @param isUpdate
     */
    private void validateForm(CompanyDetailVO companyDetailVO, boolean isUpdate) {
        // 判断是否为更新，更新时需要多校验两个字段
        if (isUpdate) {
            if (StringUtil.isBlank(companyDetailVO.getCompanyNo())
                    || StringUtil.isBlank(companyDetailVO.getAdminNo())) {
                throw new BizCommonException("信息不完整！");
            }
        } else {
            // 若为新增，则需要判断密码
            if (StringUtil.isBlank(companyDetailVO.getPassword())) {
                throw new BizCommonException("信息不完整！");
            }
        }
        // 以下是新增和更新都需要判断的字段
        if (StringUtil.isBlank(companyDetailVO.getCompanyName())
                || StringUtil.isBlank(companyDetailVO.getLoginName())
                || StringUtil.isBlank(companyDetailVO.getPhone())
                || StringUtil.isBlank(companyDetailVO.getName())
                || StringUtil.isBlank(companyDetailVO.getStatus())
                || StringUtil.isBlank(companyDetailVO.getMchId())) {
            throw new BizCommonException("信息不完整！");
        }
        // 商户模式
        final String companyStatus = "1";
        // 服务商模式
        final String serverStatus = "2";
        if (companyStatus.equals(companyDetailVO.getStatus())
                && StringUtil.isBlank(companyDetailVO.getPayKey())) {
            // 需要根据接入模式判断是否需要填 PayKey
            throw new BizCommonException("商户模式下 PayKey 必填！");
        }
    }

    /**
     * 创建商户
     *
     * @param companyDetailVO
     * @param companyNo
     * @param adminNo
     * @param creator
     * @param modifier
     */
    private void addCompany(CompanyDetailVO companyDetailVO, String companyNo, String adminNo, String creator, String modifier) {
        CompanyDO companyDO = new CompanyDO();
        try {
            // 商户必需字段
            companyDO.setCompanyNo(companyNo);
            companyDO.setAdminNo(adminNo);
            companyDO.setCreator(creator);
            companyDO.setModifier(modifier);
            companyDO.setCompanyName(companyDetailVO.getCompanyName());
            companyDO.setStatus(0);
            companyDO.setForceIdcard(1);
            companyDO.setTel(companyDetailVO.getPhone());
            companyDO.setIm("微信");
            // 商户可选字段
            companyDO.setLogoUrl(companyDetailVO.getLogoUrl());
            companyDO.setIntro(companyDetailVO.getIntro());
            companyDO.setState(companyDetailVO.getState());
            companyDO.setCity(companyDetailVO.getCity());
            companyDO.setDistrict(companyDetailVO.getDistrict());
            companyDO.setFullAddress(companyDetailVO.getFullAddress());
            companyDO.setCountry(companyDetailVO.getCountry());
            companyDO.setOverseaAddress(companyDetailVO.getOverseaAddress());
            companyDO.setMainCategory(companyDetailVO.getMainCategory());
            companyDO.setOfflineAnnualSale(companyDetailVO.getOfflineAnnualSale());
            companyDO.setOnlineAnnualSale(companyDetailVO.getOnlineAnnualSale());
            companyDOMapper.insertSelective(companyDO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizCommonException("公司新增失败！");
        }
    }

    /**
     * 创建部门（公司总部）
     *
     * @param companyDetailVO
     * @param companyNo
     * @param creator
     * @param modifier
     */
    private void addAuthOrganization(CompanyDetailVO companyDetailVO, String companyNo, String creator, String modifier) {
        AuthOrganizationDO authOrganizationDO = new AuthOrganizationDO();
        String orgId = CodeGenUtil.getOrgId();
        authOrganizationDO.setCompanyNo(companyNo);
        authOrganizationDO.setCreator(creator);
        authOrganizationDO.setModifier(modifier);
        authOrganizationDO.setOrgId(orgId);
        authOrganizationDO.setCode(orgId);
        authOrganizationDO.setName("公司总部");
        authOrganizationDO.setAddress(companyDetailVO.getState() + "-"
                + companyDetailVO.getCity() + "-"
                + companyDetailVO.getDistrict() + "-"
                + companyDetailVO.getFullAddress());
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
     * @param companyDetailVO
     * @param companyNo
     * @param creator
     * @param modifier
     */
    private Long addAuthUser(CompanyDetailVO companyDetailVO, String companyNo, String userNo, String creator, String modifier) {
        // 判断用户登录账户是否合法
        AuthUserDO authUserDO = authUserService.getByLoginName(companyDetailVO.getLoginName());
        if (authUserDO != null) {
            throw new BizCommonException("管理员账号已存在！");
        }

        AuthUserDO adminAuthUserDO = new AuthUserDO();
        adminAuthUserDO.setCompanyNo(companyNo);
        adminAuthUserDO.setCreator(creator);
        adminAuthUserDO.setModifier(modifier);
        adminAuthUserDO.setUserNo(userNo);
        adminAuthUserDO.setLoginName(companyDetailVO.getLoginName());
        adminAuthUserDO.setPassword(Md5Util.getMD5(companyDetailVO.getPassword()));
        adminAuthUserDO.setPhone(companyDetailVO.getPhone());
        adminAuthUserDO.setName(companyDetailVO.getName());
        adminAuthUserDO.setEmail(companyDetailVO.getEmail());
        authUserService.addAuthUser(adminAuthUserDO);

        return adminAuthUserDO.getId();
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
     * @param companyDetailVO
     * @param companyNo
     * @param creator
     * @param modifier
     */
    private void addAppletConfig(CompanyDetailVO companyDetailVO, String companyNo, String creator, String modifier) {
        AppletConfigDO appletConfigDO = new AppletConfigDO();
        appletConfigDO.setCompanyNo(companyNo);
        appletConfigDO.setCreator(creator);
        appletConfigDO.setModifier(modifier);
        appletConfigDO.setStatus(companyDetailVO.getStatus());
        appletConfigDO.setMchId(companyDetailVO.getMchId());
        appletConfigDO.setPayKey(companyDetailVO.getPayKey());
        appletConfigDO.setAppletType("2");
        appletConfigService.addAppletConfig(appletConfigDO);
    }
}
