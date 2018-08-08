package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyDetailVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyItemVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.CompanyQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.CompanyDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create by 777 on 2018/5/25
 */
public interface CompanyDOMapperExt extends CompanyDOMapper {

    public List<CompanyDO> queryPoList(CompanyDO company);


    CompanyDO selectByCompanyNo(String companyNo);

    /**
     * 根据 companyNo 查询商家
     *
     * @param companyNo
     * @return
     */
    CompanyDetailVO getCompanyDetailVO(String companyNo);

    /**
     * 根据指定条件分页查询商家
     *
     * @param companyQueryVO
     * @param pageQueryParam
     * @return
     */
    List<CompanyItemVO> listCompanies(@Param("companyQueryVO") CompanyQueryVO companyQueryVO,
                                      @Param("pageQueryParam") PageQueryParam pageQueryParam);

    /**
     * 根据指定条件查询商家数目
     *
     * @param companyQueryVO
     * @return
     */
    int countCompanies(@Param("companyQueryVO") CompanyQueryVO companyQueryVO);

    /**
     * 根据 companyNo 更新指定商家
     *
     * @param companyDO
     * @return
     */
    int updateByCompanyNo(CompanyDO companyDO);
}
