package org.youngtao.blog.controller.request;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.youngtao.blog.validation.ErrorMsg;
import org.youngtao.blog.validation.Valid;

public class PageRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Min(value = 1,message = ErrorMsg.PARAMS_NOT_LESS_ONE,groups = {Valid.page.class})
	private int currentPage;
	
	@Min(value = 1,message = ErrorMsg.PARAMS_NOT_LESS_ONE,groups = {Valid.page.class})
	private int pageSize;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
