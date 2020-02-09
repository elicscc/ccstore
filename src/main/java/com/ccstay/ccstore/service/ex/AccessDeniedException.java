package com.ccstay.ccstore.service.ex;

/**
 * 地址的uid和当前操作用户的uid不一致
 */
public class AccessDeniedException extends ServiceException {

	/**
     * 
     */
    private static final long serialVersionUID = 7991875652328476596L;

    public AccessDeniedException() {
		super();
	}

	public AccessDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessDeniedException(String message) {
		super(message);
	}

	public AccessDeniedException(Throwable cause) {
		super(cause);
	}

}
