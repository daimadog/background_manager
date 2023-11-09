package com.background.manager.exception;

import cn.dev33.satoken.exception.*;
import com.background.manager.exception.base.IBaseException;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.exception.enums.SecurityCodeEnum;
import com.background.manager.exception.utils.ThrowableUtil;
import com.background.manager.response.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestControllerAdvice(
        annotations = {RequestMapping.class}
        ,basePackages = {"com.background.manager"}
)
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${spring.profiles.active}")
    private String profile;

    private static final String DEV_PROFILE = "dev";

    /**
     * Description: 全局异常类构造器
     * @return
     * @author 杜黎明
     * @date 2022/11/10 15:00:36
     */
    public GlobalExceptionHandler() {

    }

    @ExceptionHandler({BadRequestException.class})
    public ApiResponse<?> badRequestExceptionHandler(BadRequestException e) {
        log.warn(ThrowableUtil.getStackTrace(e));
        if (StringUtils.isBlank(e.getErrorMsg())) {
            if (log.isDebugEnabled()) {
                log.debug("定义异常时,错误消息不能为空");
            }
            return this.buildResponseEntity(ResultCodeEnum.ENUM_DEFINITION_ERROR);
        } else if (StringUtils.isBlank(StringUtils.substringBeforeLast(e.getErrorCode(), "_"))) {
            if (log.isDebugEnabled()) {
                log.debug("定义异常时,模块名称不能为空");
            }
            return this.buildResponseEntity(ResultCodeEnum.ENUM_DEFINITION_ERROR);
        } else {
            String suffixErrorCode = StringUtils.substringAfterLast(e.getErrorCode(), "_");
            Pattern pattern = Pattern.compile("^[A-D]\\d{4}$");
            if (Objects.equals(ResultCodeEnum.SUCCESS.getCode(), suffixErrorCode)) {
                return ApiResponse.ok();
            } else if (!StringUtils.isBlank(suffixErrorCode) && pattern.matcher(suffixErrorCode).matches()) {
                return this.buildResponseEntity(e.getErrorCode(), e.getErrorMsg());
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("errorCode不满足规则(大写字母A/B/C加4位数字，如：A0001),请重新定义");
                }
                return this.buildResponseEntity(ResultCodeEnum.ENUM_DEFINITION_ERROR);
            }
        }
    }

    @ExceptionHandler({NullPointerException.class})
    public ApiResponse<?> nullPointerExceptionHandler(NullPointerException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return this.buildResponseEntity(ResultCodeEnum.UNKNOWN_ERROR);
    }

    @ExceptionHandler({BindException.class})
    public ApiResponse<?> bindExceptionHandler(BindException ex) {
        log.warn(ThrowableUtil.getStackTrace(ex));
        List<ObjectError> errors = ex.getAllErrors();
        ObjectError error = (ObjectError)errors.get(0);
        String errorMsg = error.getDefaultMessage();
        String var10001 = ResultCodeEnum.PARAM_ERROR.getErrorCode();
        String var10002 = ResultCodeEnum.PARAM_ERROR.getMessage();
        return this.buildResponseEntity(var10001, var10002 + ":" + errorMsg);
    }

    @ExceptionHandler({EntityExistException.class})
    public ApiResponse<?> entityExistExceptionHandler(EntityExistException e) {
        log.warn(ThrowableUtil.getStackTrace(e));
        return this.buildResponseEntity(ResultCodeEnum.ENTITY_EXIST.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ApiResponse<?> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        log.warn(ThrowableUtil.getStackTrace(e));
        return this.buildResponseEntity(ResultCodeEnum.ENTITY_NOT_EXIST.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(ThrowableUtil.getStackTrace(e));
        String message = (String)e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return this.buildResponseEntity(ResultCodeEnum.PARAM_ERROR.getErrorCode(), message);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ApiResponse<?> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error(ThrowableUtil.getStackTrace(exception));
        return this.buildResponseEntity(ResultCodeEnum.PARAM_ERROR.getErrorCode(), exception.getMessage());
    }

    @ExceptionHandler({DataAccessException.class})
    public ApiResponse<?> handleDataAccessException(DataAccessException exception) {
        log.error(ThrowableUtil.getStackTrace(exception));
        return this.buildResponseEntity(ResultCodeEnum.UNKNOWN_ERROR);
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse<?> handleAccessDeniedException(AccessDeniedException e) {
        log.warn(ThrowableUtil.getStackTrace(e));
        return this.buildResponseEntity(ResultCodeEnum.FORBIDDEN_ERROR);
    }

    @ExceptionHandler({NotLoginException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<?> handleNotLoginException(NotLoginException e) {
        log.warn(ThrowableUtil.getStackTrace(e));
        return this.buildResponseEntity(SecurityCodeEnum.NOT_LOGIN_ERROR.getErrorCode(), "Token无效,请重新登录");
    }

    @ExceptionHandler({NotRoleException.class})
    public ApiResponse<?> handleNotRoleException(NotRoleException e) {
        log.warn(ThrowableUtil.getStackTrace(e));
        String var10001 = SecurityCodeEnum.NOT_ROLE_ERROR.getErrorCode();
        String var10002 = SecurityCodeEnum.NOT_ROLE_ERROR.getCode();
        return this.buildResponseEntity(var10001, var10002 + ":" + e.getRole());
    }

    @ExceptionHandler({NotPermissionException.class})
    public ApiResponse<?> handleNotPermissionException(NotPermissionException e) {
        log.warn(ThrowableUtil.getStackTrace(e));
        String var10001 = SecurityCodeEnum.NOT_PERMISSION_ERROR.getErrorCode();
        String var10002 = SecurityCodeEnum.NOT_PERMISSION_ERROR.getCode();
        String var1003=SecurityCodeEnum.NOT_PERMISSION_ERROR.getMessage();
        return this.buildResponseEntity(var10001, var10002 + ":"+var1003);
    }

    @ExceptionHandler({DisableLoginException.class})
    public ApiResponse<?> handleDisableLoginException(DisableLoginException e) {
        log.warn(ThrowableUtil.getStackTrace(e));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String endDateTime;
        if (e.getDisableTime() == -1L) {
            endDateTime = LocalDateTime.MAX.format(dateTimeFormatter);
        } else {
            endDateTime = LocalDateTime.now().plusNanos(e.getDisableTime()).format(dateTimeFormatter);
        }

        String var10001 = SecurityCodeEnum.DISABLE_LOGIN_ERROR.getErrorCode();
        String var10002 = SecurityCodeEnum.DISABLE_LOGIN_ERROR.getMessage();
        return this.buildResponseEntity(var10001, var10002 + ":截止时间 " + endDateTime);
    }

    @ExceptionHandler({IdTokenInvalidException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<?> handleIdTokenInvalidException(IdTokenInvalidException e) {
        log.warn(ThrowableUtil.getStackTrace(e));
        return this.buildResponseEntity(SecurityCodeEnum.TOKEN_INVALID_ERROR);
    }

    @ExceptionHandler({ApiDisabledException.class})
    public ApiResponse<?> handleApiDisabledException(ApiDisabledException e) {
        log.warn(ThrowableUtil.getStackTrace(e));
        return this.buildResponseEntity(SecurityCodeEnum.API_DISABLED_ERROR.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler({SaTokenException.class})
    public ApiResponse<?> handleSaTokenException(SaTokenException e) {
        log.warn(ThrowableUtil.getStackTrace(e));
        return this.buildResponseEntity(SecurityCodeEnum.AUTH_ERROR.getErrorCode(), e.getMessage());
    }

    private ApiResponse<?> buildResponseEntity(String code, String errorMessage) {
        return ApiResponse.error(code, errorMessage);
    }

    private ApiResponse<?> buildResponseEntity(IBaseException iBaseException, String errorMessage) {
        return ApiResponse.error(iBaseException.getCode(), errorMessage);
    }

    private ApiResponse<?> buildResponseEntity(IBaseException iBaseException) {
        return ApiResponse.error(iBaseException.getErrorCode(), iBaseException.getMessage());
    }


}
