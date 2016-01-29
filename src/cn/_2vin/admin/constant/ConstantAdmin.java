package cn._2vin.admin.constant;

/**
 * 常量类
 * 
 * @author liuxuewen
 * @date 2014-1-22 
 */
public class ConstantAdmin {

	/**
	 * 管理员 session 键
	 */
	public static final String SESSION_ADMIN_INFO = "cn._2vin.admininfo";
	public static final String KAPTCHA_ADMIN_SESSION_KEY = "KAPTCHA_ADMIN_SESSION_KEY";

	
	
	public static final String PHONE_PATTERN = "^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}$";
	public static final String USERNAME_PATTERN = "^[a-z]([a-z0-9_]){3,31}";// 以a~z打头，后跟3~31位小写字母或数字或下划线
	/**
	 * 登录错误信息
	 */
	public static final String LOGIN_ERROR="LOGIN_ERROR";

	
	public static final String LOGIN_ERROR_NOT_LOGIN_ERROR="noLoginError";
	public static final String LOGIN_ERROR_VALIDATE_CODE_ERROR="validateCodeError";
	public static final String LOGIN_ERROR_VALIDATE_CODE_NULL="validateCodeNull";
	public static final String LOGIN_ERROR_LOGINNAME_OR_PASSWORD_ERROR="loginNameOrPasswordError";
	public static final String LOGIN_ERROR_LOGINNAME_OR_PASSWORD_NULL="loginNameOrPasswordNull";
	/**
	 * 默认超级管理员登录名
	 */
	public static final String DEFAULT_SUPER_ADMINISTRATOR = "admin";
	public static final String DEFALUT_ADMIN_LOGIN_PASSWORD = "admin";
	/**
	 * 默认模块路径
	 */
	public static final String DEFAULT_MODULE_URL = "#";
	/**
	 * 分类
	 */
	public static final String TOP_CATEGORY_VALUE="0";
	public static final String TOP_CATEGORY_NAME="顶级分类";
	/**
	 * 省略的后缀
	 */
	public static final String OMIT_THE_SUFFIX = "...";


}
