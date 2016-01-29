package cn._2vin.common.exception;
/**
 * 无权限异常
 * @author liuxuewen
 * @date 2014-1-22
 */
public class NotRoleRightException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NotRoleRightException(String message){
		super(message);
	}
}
