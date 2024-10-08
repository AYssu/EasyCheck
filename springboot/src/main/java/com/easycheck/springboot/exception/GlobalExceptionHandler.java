package com.easycheck.springboot.exception;

import com.easycheck.springboot.vo.ResponseResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult<String> runtimeException(RuntimeException e) {
        e.printStackTrace();
        return ResponseResult.fail(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<String> methodArgumentNotValidException (MethodArgumentNotValidException e){
        e.printStackTrace();
        return ResponseResult.fail(e.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseResult<String> SizeExceededException(MaxUploadSizeExceededException e)
    {
        e.printStackTrace();
        return ResponseResult.fail("上传文件过大");
    }
    @ExceptionHandler(Exception.class)
    public ResponseResult<String> exceptionException(Exception e)
    {
        e.printStackTrace();
        return ResponseResult.fail(e.getMessage());
    }
}
