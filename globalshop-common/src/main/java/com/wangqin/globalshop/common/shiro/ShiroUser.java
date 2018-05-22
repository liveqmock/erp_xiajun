/**
 *
 */
package com.wangqin.globalshop.common.shiro;

import java.io.Serializable;
import java.util.Set;

/**
 * @description：自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = -1373760761780840081L;
    
    private final Long id;
    private final String loginName;
    private final String name;
    private final Set<String> urlSet;
    private Set<String> roles;
    private Set<String> resCodes;
    private final Long companyId;

    public ShiroUser(Long id, Long companyId, String loginName, String name, Set<String> urlSet) {
        this.id = id;
        this.companyId = companyId;
        this.loginName = loginName;
        this.name = name;
        this.urlSet = urlSet;
    }

    public Long getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getName() {
        return name;
    }

    public Set<String> getUrlSet() {
        return urlSet;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName;
    }

	public Set<String> getResCodes() {
		return resCodes;
	}

	public void setResCodes(Set<String> resCodes) {
		this.resCodes = resCodes;
	}

	public Long getCompanyId() {
		return companyId;
	}
    
    
}