package org.youngtao.blog.data.domain.user;

import java.io.Serializable;
import java.util.List;

public class FinalMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Permissions firstMenu;
	
	private List<Permissions> secondMenu;

	public Permissions getFirstMenu() {
		return firstMenu;
	}

	public void setFirstMenu(Permissions firstMenu) {
		this.firstMenu = firstMenu;
	}

	public List<Permissions> getSecondMenu() {
		return secondMenu;
	}

	public void setSecondMenu(List<Permissions> secondMenu) {
		this.secondMenu = secondMenu;
	}
	
	
}
