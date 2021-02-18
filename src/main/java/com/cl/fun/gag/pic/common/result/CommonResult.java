package com.cl.fun.gag.pic.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult  {
    private Integer code;
    private String message;
    private Object data;

    public CommonResult ( Integer code, String message) {
        this(code, message, null);
    }

    public static CommonResult success(Object t){
        return new CommonResult(1, "处理成功", t);
    }

    public static CommonResult fail(){
        return new CommonResult(0, "处理失败");
    }

    public static CommonResult fail(String msg){
        return new CommonResult(0, msg);
    }

    public static CommonResult error (String msg){
        return new CommonResult(-1, msg);
    }

    public static CommonResult forbidden(String msg){
        return new CommonResult(403, msg);
    }


    public static CommonResult unauthorized(String msg){
        return new CommonResult(401, msg);
    }
}
