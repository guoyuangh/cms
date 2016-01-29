package cn._2vin.common.bean;


/**
 * 操作结果类[可用于ajax返回结果记录对象]
 * @author liuxuewen
 * @date 2014-1-22
 */
public class ActionResult{
	
	private boolean success;
	private String result;
	private int code;
	private Throwable exception;
	private String message;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Throwable getException() {
		return exception;
	}
	public void setException(Throwable exception) {
		this.exception = exception;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}
