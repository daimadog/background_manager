package com.background.manager.Base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel("基础数据传输对象")
public class BaseDTO {

    @ApiModelProperty("数据ID")
    private String id;
    @ApiModelProperty("创建者ID")
    private String creatorId;
    @ApiModelProperty("修改者ID")
    private String modifierId;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("修改时间")
    private LocalDateTime modifyTime;

    public BaseDTO() {
    }

    public String getId() {
        return this.id;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public String getModifierId() {
        return this.modifierId;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public LocalDateTime getModifyTime() {
        return this.modifyTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseDTO)) {
            return false;
        } else {
            BaseDTO other = (BaseDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label71;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label71;
                    }

                    return false;
                }

                Object this$creatorId = this.getCreatorId();
                Object other$creatorId = other.getCreatorId();
                if (this$creatorId == null) {
                    if (other$creatorId != null) {
                        return false;
                    }
                } else if (!this$creatorId.equals(other$creatorId)) {
                    return false;
                }

                label57: {
                    Object this$modifierId = this.getModifierId();
                    Object other$modifierId = other.getModifierId();
                    if (this$modifierId == null) {
                        if (other$modifierId == null) {
                            break label57;
                        }
                    } else if (this$modifierId.equals(other$modifierId)) {
                        break label57;
                    }

                    return false;
                }

                Object this$createTime = this.getCreateTime();
                Object other$createTime = other.getCreateTime();
                if (this$createTime == null) {
                    if (other$createTime != null) {
                        return false;
                    }
                } else if (!this$createTime.equals(other$createTime)) {
                    return false;
                }

                Object this$modifyTime = this.getModifyTime();
                Object other$modifyTime = other.getModifyTime();
                if (this$modifyTime == null) {
                    if (other$modifyTime == null) {
                        return true;
                    }
                } else if (this$modifyTime.equals(other$modifyTime)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseDTO;
    }

    public int hashCode() {
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $creatorId = this.getCreatorId();
        result = result * 59 + ($creatorId == null ? 43 : $creatorId.hashCode());
        Object $modifierId = this.getModifierId();
        result = result * 59 + ($modifierId == null ? 43 : $modifierId.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $modifyTime = this.getModifyTime();
        result = result * 59 + ($modifyTime == null ? 43 : $modifyTime.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getId();
        return "BaseDTO(id=" + var10000 + ", creatorId=" + this.getCreatorId() + ", modifierId=" + this.getModifierId() + ", createTime=" + this.getCreateTime() + ", modifyTime=" + this.getModifyTime() + ")";
    }

}
