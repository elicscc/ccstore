package com.ccstay.ccstore.service.ex;

/**
 * 删除地址异常
 */
public class DeleteException extends ServiceException {

	/**
     * 
     */
    private static final long serialVersionUID = 7991875652328476596L;

    public DeleteException() {
		super();
	}

	public DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeleteException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeleteException(String message) {
		super(message);
	}

	public DeleteException(Throwable cause) {
		super(cause);
	}

}
