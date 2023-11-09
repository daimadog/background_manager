package com.background.manager.exception.base;

import cn.hutool.core.text.StrPool;
import org.apache.commons.lang3.StringUtils;

public interface IBaseException {
    /**
     * Description:获取模块名
     * @return: java.lang.String 模块名(必填)
     * @Author: chenbinjie
     * @Date: 2022/1/25 10:21
     */
    String getModuleName();

    /**
     * Description:获取错误码（后缀）
     * 错误码后缀组成： 错误来源（A/B/C）+ 4位数字
     * 错误来源：分为 A/B/C， A 表示错误来源于用户，B 表示错误来源于当前系统，C 表示错误来源于第三方服务
     * 4位数字：编号从 0001 到 9999，大类之间的步长间距预留 100
     * @return: java.lang.String 错误码后缀
     * @Author: chenbinjie
     * @Date: 2022/1/25 10:28
     */
    String getCode();

    /**
     * Description: 获取错误消息
     * @return: java.lang.String
     * @Author: chenbinjie
     * @Date: 2022/3/3 13:19
     */
    String getMessage();

    /**
     * Description: 获取完整错误码 组成：模块名_错误码后缀
     * @return: java.lang.String
     * @Author: chenbinjie
     * @Date: 2022/3/3 13:19
     */
    default String getErrorCode() {
        return StringUtils.join(getModuleName(), StrPool.UNDERLINE, getCode());
    }

}
