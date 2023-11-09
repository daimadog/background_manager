package com.background.manager.model.activity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("活动—直播培训实体领域模型")
public class ActivityVideo extends Activity{

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频url地址
     */
    private String videoUrl;
    /**
     * 视频UUID
     */
    private String videoUuid;
    /**
     * 活动编号
     */
    private Long activityId;

}
