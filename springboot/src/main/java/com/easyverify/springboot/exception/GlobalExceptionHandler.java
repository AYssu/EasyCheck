package com.easyverify.springboot.exception;

import com.easyverify.springboot.vo.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult<String> runtimeException(RuntimeException e) {
        e.printStackTrace();
        log.error("RuntimeException: {}",e.getMessage());
        return ResponseResult.fail(!StringUtils.hasText(e.getMessage())?"Runtime未知错误":e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<String> methodArgumentNotValidException (MethodArgumentNotValidException e){
        e.printStackTrace();
        log.error("MethodArgumentNotValidException: {}",e.getMessage());
        return ResponseResult.fail(!StringUtils.hasText(e.getAllErrors().get(0).getDefaultMessage())?"NotValid未知错误":e.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseResult<String> SizeExceededException(MaxUploadSizeExceededException e)
    {
        log.error("sizeException: {}",e.getMessage());
        e.printStackTrace();
        return ResponseResult.fail("上传文件过大");
    }



    @ExceptionHandler(value =NullPointerException.class)
    public ResponseResult exceptionHandler(HttpServletRequest req, NullPointerException e){
        e.printStackTrace();
        log.error("NullPointerException: {}",e.getMessage());
        return ResponseResult.fail(!StringUtils.hasText(e.getMessage())?"Runtime未知错误":e.getMessage());

    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<String> exceptionException(Exception e)
    {
        log.error("Exception: {}",e.getMessage());
        e.printStackTrace();
        return ResponseResult.fail(!StringUtils.hasText(e.getMessage())?"Runtime未知错误":e.getMessage());

    }


}
