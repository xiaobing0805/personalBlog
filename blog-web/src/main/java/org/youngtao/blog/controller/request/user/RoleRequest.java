package org.youngtao.blog.controller.request.user;

import java.io.Serializable;

import org.youngtao.blog.controller.request.PageRequest;

/**
 * 
 * @author linyantao
 * @date 2017年5月21日
 */
public class RoleRequest extends PageRequest implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增
     */
    private Integer id;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 备注
     */
    private String remarks;
    
    /**
     * 是否超级管理员（0：否、1：是）
     */
    private int isSupper;
    
    /**
     * 是否禁用（0：正常、1：禁用）
     */
    private int isDisable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getIsSupper() {
        return isSupper;
    }

    public void setIsSupper(int isSupper) {
        this.isSupper = isSupper;
    }

    public int getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(int isDisable) {
        this.isDisable = isDisable;
    }
}
