package com.background.manager.constants.database;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "基础分页查询DTO")
public class BasePageDTO {
    @ApiModelProperty("页码")
    private @NotNull(
            message = "分页页码不能为空"
    ) Integer page;
    @ApiModelProperty("大小")
    private @NotNull(
            message = "分页大小不能为空"
    ) Integer size;

    public BasePageDTO() {
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getSize() {
        return this.size;
    }

    public BasePageDTO setPage(Integer page) {
        this.page = page;
        return this;
    }

    public BasePageDTO setSize(Integer size) {
        this.size = size;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BasePageDTO)) {
            return false;
        } else {
            BasePageDTO other = (BasePageDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$page = this.getPage();
                Object other$page = other.getPage();
                if (this$page == null) {
                    if (other$page != null) {
                        return false;
                    }
                } else if (!this$page.equals(other$page)) {
                    return false;
                }

                Object this$size = this.getSize();
                Object other$size = other.getSize();
                if (this$size == null) {
                    if (other$size != null) {
                        return false;
                    }
                } else if (!this$size.equals(other$size)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BasePageDTO;
    }

    public int hashCode() {
        int result = 1;
        Object $page = this.getPage();
        result = result * 59 + ($page == null ? 43 : $page.hashCode());
        Object $size = this.getSize();
        result = result * 59 + ($size == null ? 43 : $size.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getPage();
        return "BasePageDTO(page=" + var10000 + ", size=" + this.getSize() + ")";
    }
}
