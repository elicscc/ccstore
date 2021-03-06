package com.ccstay.ccstore.service.ex;

/**
 * 地址数据不存在出现异常
 */
public class OrderNotFoundException extends ServiceException {

	/**
     * 
     */
    private static final long serialVersionUID = 7991875652328476596L;

    public OrderNotFoundException() {
		super();
	}

	public OrderNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrderNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderNotFoundException(String message) {
		super(message);
	}

	public OrderNotFoundException(Throwable cause) {
		super(cause);
	}

}
