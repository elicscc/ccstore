package com.ccstay.ccstore.controller.ex;
/**
 * 商品未找到
 * @author Alex
 *
 */
public class ProductNotFoundException extends FileUploadException {

    /**
     * 
     */
    private static final long serialVersionUID = 5216328143694529891L;

    public ProductNotFoundException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public ProductNotFoundException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public ProductNotFoundException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
