package com.grady.server.exception;

/**
 * @Author Grady
 * @Date 2020/7/27 21:04
 * @Version 1.0
 */

/**
 * 继承runtimeexception的好处就是不用捕获异常，
 * 如果直接是exception代码需要捕获，否则编译不通过
 */
public class BusinessException extends RuntimeException{

    private BusinessExceptionCode code;

    public BusinessException(BusinessExceptionCode code){
        super(code.getDesc());
        this.code = code;
    }

    public BusinessExceptionCode getCode() {
        return code;
    }

    public void setCode(BusinessExceptionCode code) {
        this.code = code;
    }

    /**
     * 不写入堆栈信息，提高性能
     * @return
     */
    @Override
    public Throwable fillInStackTrace(){
        return this;
    }
}
