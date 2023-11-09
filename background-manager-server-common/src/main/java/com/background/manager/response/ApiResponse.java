package com.background.manager.response;

import com.background.manager.exception.base.IBaseException;
import com.background.manager.exception.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@ApiModel(value = "API接口响应体")
public class ApiResponse<T> {

    @ApiModelProperty("调用结果")
    protected Boolean success = false;

    @ApiModelProperty("响应编码")
    private String code;

    @ApiModelProperty("错误信息")
    private String errorMsg;

    @ApiModelProperty("响应数据")
    private T data;

    /**
     * 返回成功
     */
    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> r = new ApiResponse<>();
        r.setSuccess(true);
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setData(data);
        return r;
    }

    /**
     * 返回成功
     */
    public static <T> ApiResponse<T> ok() {
        ApiResponse<T> r = new ApiResponse<>();
        r.setSuccess(true);
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        return r;
    }

    /**
     * 返回失败
     *
     * @return
     */
    public static ApiResponse<?> error(String code,String message) {
        ApiResponse<?> r = new ApiResponse<>();
        r.setSuccess(false);
        r.setCode(code);
        r.setErrorMsg(message);
        return r;
    }

    /**
     * 返回失败
     *
     * @return
     */
    public static ApiResponse<?> error(Integer code,String message) {
        ApiResponse<?> r = new ApiResponse<>();
        r.setSuccess(false);
        r.setCode(String.valueOf(code));
        r.setErrorMsg(message);
        return r;
    }

    /**
     * 返回失败
     *
     * @return
     */
    public static ApiResponse error(ResultCodeEnum resultCodeEnum) {
        ApiResponse<?> r = new ApiResponse<>();
        r.setSuccess(false);
        r.setCode(resultCodeEnum.getErrorCode());
        r.setErrorMsg(resultCodeEnum.getMessage());
        return r;
    }

    /**
     * @Description: 返回失败信息
     * @Param: baseExceptionInterface
     * @Return: com.nbicc.common.core.response.ApiResponse
     * @Author: chenbinjie
     * @Date: 2022/1/25 11:15
     */
    public static ApiResponse error(IBaseException iBaseException) {
        ApiResponse<?> r = new ApiResponse<>();
        r.setSuccess(false);
        r.setCode(iBaseException.getErrorCode());
        r.setErrorMsg(iBaseException.getMessage());
        return r;
    }

}

