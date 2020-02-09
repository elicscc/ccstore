package com.ccstay.ccstore.service.ex;

/**
 * 添加的用户名已存在时抛出的异常
 */
public class UsernameDuplicateException extends ServiceException {

	/**
     * 
     */
    private static final long serialVersionUID = -1224474172375139228L;

    public UsernameDuplicateException() {
		super();
	}

	public UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsernameDuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameDuplicateException(String message) {
		super(message);
	}

	public UsernameDuplicateException(Throwable cause) {
		super(cause);
	}

}
