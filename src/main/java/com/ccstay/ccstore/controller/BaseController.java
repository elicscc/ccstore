package com.ccstay.ccstore.controller;

import javax.servlet.http.HttpSession;

import com.ccstay.ccstore.controller.ex.*;
import com.ccstay.ccstore.service.ex.*;
import com.ccstay.ccstore.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 所用控制器类的父类
 */
public abstract class BaseController {

    protected static final String SESSION_UID = "uid";
    protected static final String SESSION_USERNAME = "username";

    protected static final Integer SUCCESS = 20;
    // 静态常量的命名：所有字母都大写，单词用_隔开，尽量说明白，不要嫌长
    protected static final Integer ERROR_USERNAME_DUPLICATE = 30;

    /**
     * 对控制器中的异常进行统一处理
     * 
     * @param e 异常对象
     * @return JsonResult封装响应信息
     */
    @ExceptionHandler({ ServiceException.class, FileUploadException.class })
    @ResponseBody
    public JsonResult<Void> handlerException(Throwable e) {
        // 根据不同异常的类型提供不同的处理方式
        // 现在的处理方式是根据不同的类型，返回不同的状态码
        JsonResult<Void> jr = new JsonResult<>(e.getMessage());

        if (e instanceof UsernameDuplicateException) {
            jr.setState(ERROR_USERNAME_DUPLICATE);
        } else if (e instanceof UserNotFoundException) {
            jr.setState(31);
        } else if (e instanceof PasswordNotMatchException) {
            jr.setState(32);
        } else if (e instanceof AddressCountLimitException) {
            jr.setState(33);
        } else if (e instanceof AccessDeniedException) {
            jr.setState(34);
        } else if (e instanceof ProductNotFoundException) {
            jr.setState(35);
        } else if (e instanceof AddressNotFoundException) {
            jr.setState(36);
        } else if (e instanceof CartNotFoundException) {
            jr.setState(37);
        } else if (e instanceof OrderNotFoundException) {
            jr.setState(38);
        } else if (e instanceof ProductOutOfStockException) {
            jr.setState(39);
        } else if (e instanceof InsertException) {
            jr.setState(40);
        } else if (e instanceof UpdateException) {
            jr.setState(41);
        } else if (e instanceof FileEmptyException) {
            jr.setState(50);
        } else if (e instanceof FileSizeException) {
            jr.setState(51);
        } else if (e instanceof FileTypeException) {
            jr.setState(52);
        } else if (e instanceof FileStateException) {
            jr.setState(53);
        } else if (e instanceof FileIOException) {
            jr.setState(54);
        }

        return jr;
    }

    public Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute(SESSION_UID).toString());
    }

    public String getUsernameFromSession(HttpSession session) {
        return session.getAttribute(SESSION_USERNAME).toString();
    }

}
