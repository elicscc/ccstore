package com.ccstay.ccstore.service.ex;

/**
 * 商品数量小于0抛出异常
 */
public class ProductOutOfStockException extends ServiceException {

	/**
     * 
     */
    private static final long serialVersionUID = 7991875652328476596L;

    public ProductOutOfStockException() {
		super();
	}

	public ProductOutOfStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductOutOfStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductOutOfStockException(String message) {
		super(message);
	}

	public ProductOutOfStockException(Throwable cause) {
		super(cause);
	}

}
