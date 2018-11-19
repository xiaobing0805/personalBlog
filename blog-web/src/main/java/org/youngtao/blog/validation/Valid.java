package org.youngtao.blog.validation;

/**
 * 分组校验
 * @author linyantao
 *
 */
public interface Valid {
	
	/**
	 * 查询单行数据
	 *
	 */
	interface select {}
	
	/**
	 * 查询多行数据
	 *
	 */
	interface find {}
	
	/**
	 * 查询分页数据
	 *
	 */
	interface page {}

	/**
	 * 新增
	 *
	 */
	interface add {}
	
	/**
	 * 保存
	 */
	interface save {}
	
	/**
	 * 修改
	 */
	interface update {}
	
	/**
	 * 修改密码
	 * @author linyantao
	 * @date 2017年3月30日
	 */
	interface updatePwd{}
	
	/**
	 * 删除（逻辑）
	 */
	interface remove {}
	
	/**
	 * 删除(物理)
	 */
	interface delete {}
	
	/**
	 * 禁用
	 * @author linyantao
	 * @date 2017年3月31日
	 */
	interface disable{};
}
