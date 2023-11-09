package com.background.manager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Arrays;

/**
 * @Description:  security配置文件读取类
 * @Author: 杜黎明
 * @Date: 2022/11/15 10:08:31
 * @Version: 1.0.0
 */
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    private String[] excludes;
    private String[] unsafeExcludes;
    private String[] openApi;

    public SecurityProperties() {}

    public String[] getExcludes() {return excludes;}

    public void setExcludes(String[] excludes) {this.excludes = excludes;}

    public String[] getUnsafeExcludes() {return unsafeExcludes;}

    public void setUnsafeExcludes(String[] unsafeExcludes) {this.unsafeExcludes = unsafeExcludes;}

    public String[] getOpenApi() {return openApi;}

    public void setOpenApi(String[] openApi) {this.openApi = openApi;}

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SecurityProperties)) {
            return false;
        } else {
            SecurityProperties other = (SecurityProperties)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!Arrays.deepEquals(this.getExcludes(), other.getExcludes())) {
                return false;
            } else if (!Arrays.deepEquals(this.getUnsafeExcludes(), other.getUnsafeExcludes())) {
                return false;
            } else {
                return Arrays.deepEquals(this.getOpenApi(), other.getOpenApi());
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof SecurityProperties;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + Arrays.deepHashCode(this.getExcludes());
        result = result * 59 + Arrays.deepHashCode(this.getUnsafeExcludes());
        result = result * 59 + Arrays.deepHashCode(this.getOpenApi());
        return result;
    }

    public String toString() {
        String var10000 = Arrays.deepToString(this.getExcludes());
        return "SecurityProperties(excludes=" + var10000 + ", unsafeExcludes=" + Arrays.deepToString(this.getUnsafeExcludes()) + ", openApi=" + Arrays.deepToString(this.getOpenApi()) + ")";
    }


}
