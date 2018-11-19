package org.youngtao.blog.common.constant;

/**
 * 
 * @author linyantao
 *
 */
public interface Constants {

	/**
	 * 验证码session key
	 */
	String KAPTCHA_SESSION_KEY = "captcha_session_key";
	
	/**
	 * 登陆异常信息回填Key
	 */
	String ERROR = "error";
	
	/**
	 * 登陆异常，回填用户名 session key
	 */
	String USERNAME_Attribute_KEY = "username";
	
	/**
	 * 用户基本信息保存进Session key
	 */
	String USER_SESSION_INFO = "userSessionInfo";
	
	/**
	 * 用户配置信息保存进session key
	 */
	String USER_SETTINGS_INFO = "userSettingsInfo";
	
	/**
	 * open代表展开侧边栏,close代表关闭
	 */
	String SIDEBAR_TOGGLE_EVEN = "SIDEBAR_TOGGLE_EVEN";
	
	/**
	 * 生成盐，随机数长度
	 */
	int GENERATE_RANDOM_LENGTH = 12;
	
	/**
	 * shiro登陆错误key
	 */
	String SHIRO_LOGIN_FAILURE = "shiroLoginFailure";
	
	/**
	 * 国际化语言包存放位置
	 */
	String I18N_MESSAGE_URL = "WEB-INF/i18n/message";
	
	/**
	 * 国际化校验异常信息存放位置
	 */
	String I18N_VALIDATE_MESSAGE_URL = "WEB-INF/i18n/validate";
	
	/**
	 * 是否超管（0：否、1：是）
	 */
	int IS_SUPPER = 1;
	
	/**
	 * 是否超管（0：否、1：是）
	 */
	int IS_NOT_SUPPER = 0;
	
	/**
	 * Session超时时间，一个月
	 */
	int SESSION_TIME_OUT_30_DAY = (30 * 24 * 3600 * 1000);
	
	/**
	 * SESSION超时时间，30分钟
	 */
	int SESSION_TIME_OUT_30_MIN = (30 * 60 * 1000);	
	
	/**
	 * 一级菜单
	 */
	int FIRST_MENU = 0;
	
	/**
	 *默认头像
	 */
	String DEFAULT_PORTRAIT = "./../../static/images/heard_img.png";
	
	/**
	 * 该用户session处于被踢出状态
	 */
	String KILL_OUT = "1";

	/**
	 * 用户被踢出会话错误码
	 */
	int KICK_OUT = 408;
	
	/**
	 * 强制踢出用户会话
	 */
	int FORCE_LOGOUT = 409;
	
	/**
	 * 用户登录提示，管理员强制踢出用户会话
	 */
	String FORCE_LOGOUT_USER = "1";
	
	/**
	 * 踢出用户session key
	 */
	String KICK_OUT_SESSION = "kickout";
	
	/**
	 * 禁用
	 */
	int IS_DISABLE = 1;
	
	/**
	 * 启用
	 */
	int IS_ENABLE = 0;
	
	/**
	 * Ehcache 保存用户登录次数信息key
	 */
	String SHIRO_KICKOUT_SESSION = "shiro-kickout-session";
	
	/**
	 * 强制用户退出session key
	 */
	String SESSION_FORCE_LOGOUT_KEY = "session_force_logout_key";
	
	/**
	 * 全局默认编码
	 */
	String GLOBAL_ENCODING = "UTF-8"; 
	
	/**
	 * 语言占位符
	 */
	String LANGUAGE_PLACEHOLDER = "{language}";
	
	/**
	 * JS国际化内容占位符
	 */
	String I18N_CONTENT_PLACEHOLDER = "{i18n.content}";
	
	/**
	 * 后缀名为properties
	 */
	String SUFFIX_PROPERTIES = "properties";
	
	/**
	 * 要生成JS国际化文件Properties目录
	 */
	String PROPERTIES_PATH = "/WEB-INF/i18n/js/";
	
	/**
	 * 生成JS国际化文件，存储路径
	 */
	String PROPERTIES_WEBAPP_PATH = "/static/js/i18n/";
	
	/**
	 * 国际化JS文件模板存放路径
	 */
	String TEMPLATE_PATH = "/WEB-INF/i18n/js/template/js_i18n_template.txt";
	
	/**
	 * 权限状态
	 */
	String PERMISSIONS_STATUS = "1";
	
	/**
	 * 接口状态
	 */
	String CONTROLLER_STATUS = "2";
	
	/**
	 * 根父节点
	 */
	int PERMISSIONS_PARENT_ID = 1;
}
