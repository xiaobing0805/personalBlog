package org.youngtao.blog.data.domain.user;

import java.io.Serializable;
import java.util.List;

import org.youngtao.blog.data.domain.BaseDomain;

public class Permissions extends BaseDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	/**
	 * 中文简体权限名
	 */
	private String zhCNName;
	
	/**
	 * 中文繁体权限名
	 */
	private String zhHKName;
	
	/**
	 * 英文名
	 */
	private String enName;
	
	/**
	 * 权限名
	 */
	private String permissionsName;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 访问地址
	 */
	private String url;
	
	/**
	 * 父级菜单ID
	 */
	private int parentId;
	
	/**
	 * 是否禁用（0：否，1：禁用）
	 */
	private int isDisable;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * 1：该权限是permissions,2:该权限是controller
	 */
	private String pmsOrCtl;
	
	/**
	 * 子权限
	 */
	private List<Permissions> childPermissions;
	
	public List<Permissions> getChildPermissions() {
        return childPermissions;
    }

    public void setChildPermissions(List<Permissions> childPermissions) {
        this.childPermissions = childPermissions;
    }

    public String getPmsOrCtl() {
        return pmsOrCtl;
    }

    public void setPmsOrCtl(String pmsOrCtl) {
        this.pmsOrCtl = pmsOrCtl;
    }

    public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(int isDisable) {
		this.isDisable = isDisable;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZhCNName() {
		return zhCNName;
	}

	public void setZhCNName(String zhCNName) {
		this.zhCNName = zhCNName;
	}

	public String getZhHKName() {
		return zhHKName;
	}

	public void setZhHKName(String zhHKName) {
		this.zhHKName = zhHKName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getPermissionsName() {
		return permissionsName;
	}

	public void setPermissionsName(String permissionsName) {
		this.permissionsName = permissionsName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
}
