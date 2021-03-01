package com.example.restservice.util.exception;

import com.example.restservice.util.http.ResultEntity;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Archie on 2021-03-01.
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class RestGlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(RestGlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResultEntity handleException(Exception e, HttpServletRequest servletRequest) {
        log.error("系统内部异常，异常信息", e);
        if (servletRequest != null) {
            log.error("系统异常，请求url::{}", servletRequest.getRequestURI());
            String key, name;
            Enumeration<String> enumeration = servletRequest.getParameterNames();
            while (enumeration.hasMoreElements()) {
                key = enumeration.nextElement();
                name = servletRequest.getParameter(key);
                log.error("请求参数::{},请求参数值:{}", key, name);
            }
        }
        return new ResultEntity<>(200, null, 6, "系统内部异常");
    }

    @ExceptionHandler(value = AlertException.class)
    public ResultEntity handleAlertException(AlertException e) {
        log.error("AlertException错误信息 = {{}} \n位置 = {}", e.getMessage(), Arrays.copyOfRange(e.getStackTrace(), 1, 4));
        return new ResultEntity<>(200, null, 6, e.getMessage());
//        return ResultEntity.alert(e.getMessage());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return ResultEntity
     */
    @ExceptionHandler(BindException.class)
    public ResultEntity validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new ResultEntity<>(400, null, 4, message.toString());
    }




    /**
     * 请求参数校验
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultEntity paramExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult exceptions = e.getBindingResult();
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = (FieldError) errors.get(0);
                return new ResultEntity<>(400, null, 4, fieldError.getField() + fieldError.getDefaultMessage());
            }
        }
        return new ResultEntity<>(400, null, 4, "请求参数错误");
    }



}
