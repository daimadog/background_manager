package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HtmlUtil;
import com.background.manager.convert.BackgroundActivityConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.dto.request.activity.AddActivityRequest;
import com.background.manager.model.dto.request.activity.ModifyActivityRequest;
import com.background.manager.model.dto.request.activity.PageQueryActivityRequest;
import com.background.manager.model.dto.response.activity.ActivityDTO;
import com.background.manager.model.dto.response.activity.ActivityDigestDTO;
import com.background.manager.handler.NonStaticResourceHttpRequestHandler;
import com.background.manager.model.BackgroundCmsActivity;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundActivityMapper;
import com.background.manager.service.BackgroundActivityService;
import com.background.manager.service.ResourcesActivityService;
import com.background.manager.service.TextActivityService;
import com.background.manager.service.VideoActivityService;
import com.background.manager.util.FileTypeCheckUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Description: 后台活动管理接口实现类
 * @Author: 杜黎明
 * @Date: 2022/10/26 19:45:19
 * @Version: 1.0.0
 */
@Service
public class BackgroundActivityServiceImpl extends ServiceImpl<BackgroundActivityMapper, BackgroundCmsActivity>implements BackgroundActivityService {

    @Resource
    private BackgroundActivityConvertor backgroundActivityConvertor;
    @Resource
    private ResourcesActivityService resourcesActivityService;
    @Resource
    private TextActivityService textActivityService;
    @Resource
    private VideoActivityService videoActivityService;
    @Resource
    private  NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    @Override
    public IPage<ActivityDigestDTO> pageQuery(PageQueryActivityRequest request) {
        IPage<BackgroundCmsActivity> page = this.lambdaQuery()
                .like(StringUtils.isNotBlank(request.getTitle()), BackgroundCmsActivity::getTitle, request.getTitle())
                .eq(ObjectUtils.isNotEmpty(request.getColumnId()), BackgroundCmsActivity::getColumnId, request.getColumnId())
                .eq(ObjectUtils.isNotEmpty(request.getType()),BackgroundCmsActivity::getType,request.getType())
                .orderByDesc(BackgroundCmsActivity::getCreateTime)
                .page(new Page<>(request.getPage(), request.getSize()));

        if (CollectionUtil.isEmpty(page.getRecords())) {
            return new Page<>();
        }
        Page<ActivityDigestDTO> pages = new Page<>(page.getCurrent(), page.getSize());
        pages.setTotal(page.getTotal());
        pages.setPages(page.getPages());
        pages.setRecords(backgroundActivityConvertor.toActivityDigestDTOS(page.getRecords()));
        return pages;
    }

    @Override
    public String add(AddActivityRequest request) {
        //校验图片格式
        if (StringUtils.isNotBlank(request.getImgUrl())){
            FileTypeCheckUtils.checkImageFormat(request.getImgUrl());
        }
        BackgroundCmsActivity backgroundCmsActivity = backgroundActivityConvertor.toBackgroundActivity(request);
        if (StringUtils.isNotBlank(request.getContent())) {
            String rteContent = HtmlUtil.escape(request.getContent());
            backgroundCmsActivity.setContent(rteContent);
        }
        backgroundCmsActivity.setCreateTime(LocalDateTime.now());
        backgroundCmsActivity.setUpdateTime(LocalDateTime.now());
        if (ObjectUtils.isNotEmpty(StpUtil.getLoginIdDefaultNull())) {
            String creator = StpUtil.getLoginIdAsString();
            backgroundCmsActivity.setCreator(creator);
            backgroundCmsActivity.setModifier(creator);
        }
        this.save(backgroundCmsActivity);
        return backgroundCmsActivity.getTitle();
//        //根据不同的Activity对象利用ActivityHandlerChain责任链模式进行参数的校验
//        Integer activityType = request.getColumnId().intValue();
//        Map<String, Object> context = request.getContext();
//        Activity activity = ActivityFactory.getInstance(activityType);
//        ActivityHandlerChain.handlerChain(activityType, request.getContext());
//        if (save&& StatusConstant.ORIGINAL.equals(request.getIsOriginal())) {
//            /*保存活动的具体内容*/
//            if (ActivityCategoryEnum.COMPUTER_RESOURCES == ActivityCategoryEnum.findEnum(activityType)) {
//                ActivityResources resourcesActivity = (ActivityResources) activity;
//                BeanUtil.fillBeanWithMap(context, resourcesActivity, true);
//                BigDecimal originalPrice = (BigDecimal) context.get("originalPrice");
//                BigDecimal preferentialPrice = (BigDecimal) context.get("preferentialPrice");
//                BigDecimal discount = (originalPrice.subtract(preferentialPrice)).divide(originalPrice, 2, BigDecimal.ROUND_HALF_DOWN);
//                resourcesActivity.setDiscount(discount);
//                resourcesActivity.setActivityId(backgroundCmsActivity.getId());
//                resourcesActivityService.add(resourcesActivity);
//            } else if (ActivityCategoryEnum.TEXT == ActivityCategoryEnum.findEnum(activityType)) {
//                ActivityText textActivity = (ActivityText) activity;
//                BeanUtil.fillBeanWithMap(request.getContext(), textActivity, true);
//                String content = (String) context.get("content");
//                if (StringUtils.isNotBlank(content)) {
//                    String filter = HtmlUtil.filter(content);
//                    String escape = HtmlUtil.escape(filter);
//                    textActivity.setContent(escape);
//                }
//                textActivity.setActivityId(backgroundCmsActivity.getId());
//                textActivityService.add(textActivity);
//            } else if (ActivityCategoryEnum.VIDEO == ActivityCategoryEnum.findEnum(activityType)) {
//                ActivityVideo videoActivity = (ActivityVideo) activity;
//                MultipartFile file = (MultipartFile) context.get("multipartFile");
//                //获取文件后缀，因此此后端代码可接收一切文件，上传格式前端限定
//                String filename = file.getOriginalFilename();
//                String fileExt = FileTypeUtils.getFileType(filename);
//                String pikId = UUID.randomUUID().toString().replaceAll("-", "");
//                //视频名字拼接：唯一标识符加上点，再加上上面的视频后缀也就是MP4之类的。就组成了现在的视频名字，比如这样：c7bbc1f9664947a287d35dd7cdc48a95.mp4
//                String newVideoName = pikId + "." + fileExt;
//                videoActivity.setVideoName(newVideoName);
//                videoActivity.setVideoUuid(pikId);
//                videoActivity.setActivityId(Long.parseLong(activityType.toString()));
//                //todo 保存视频url路径地址，下载视频文件到服务器文件夹/阿里oss；
//                //String savePath="";
//                //file.transferTo(new File(savePath,newVideoName));
//                videoActivityService.add(videoActivity);
//            }
//        }

    }

    @Override
    public void modify(ModifyActivityRequest request) {
        BackgroundCmsActivity repeatNameActivity = this.getOne(new LambdaQueryWrapper<BackgroundCmsActivity>()
                .eq(BackgroundCmsActivity::getTitle, request.getTitle())
                .ne(BackgroundCmsActivity::getId, request.getId()));
        if (ObjectUtils.isNotEmpty(repeatNameActivity)) {
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ACTIVITY_NAME_EXIST_ERROR);
        }
        //校验图片格式
        if (StringUtils.isNotBlank(request.getImgUrl())) {
            FileTypeCheckUtils.checkImageFormat(request.getImgUrl());
        }
        BackgroundCmsActivity backgroundCmsActivity = backgroundActivityConvertor.toBackgroundActivity(request);
        //处理富文本编辑器内容
        if (StringUtils.isNotBlank(request.getContent())){
            String rteContent = HtmlUtil.escape(request.getContent());
            backgroundCmsActivity.setContent(rteContent);
        }
        if(StpUtil.isLogin()){
            String modifier = StpUtil.getLoginIdAsString();
            backgroundCmsActivity.setModifier(modifier);
        }
        backgroundCmsActivity.setUpdateTime(LocalDateTime.now());
        this.updateById(backgroundCmsActivity);
        }

    @Override
    public void delete(Long id) {
        BackgroundCmsActivity backgroundCmsActivity = this.getOne(new LambdaQueryWrapper<BackgroundCmsActivity>()
                .eq(BackgroundCmsActivity::getId, id));
        if (ObjectUtils.isEmpty(backgroundCmsActivity)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ACTIVITY_NOT_EXIST_ERROR);
        }
        this.removeById(id);
//        if (remove){
//            /*删除对应的具体内容*/
//            Integer activityType = backgroundCmsActivity.getColumnId().intValue();
//            if (ActivityCategoryEnum.COMPUTER_RESOURCES == ActivityCategoryEnum.findEnum(activityType)) {
//                ActivityResources resourcesActivity = resourcesActivityService.getResourcesActivity(id);
//                resourcesActivityService.removeById(resourcesActivity);
//            } else if (ActivityCategoryEnum.TEXT == ActivityCategoryEnum.findEnum(activityType)) {
//                ActivityText activityText = textActivityService.getTextActivity(id);
//                textActivityService.removeById(activityText);
//            } else if (ActivityCategoryEnum.VIDEO == ActivityCategoryEnum.findEnum(activityType)) {
//                ActivityVideo activityVideo = videoActivityService.getVideoActivity(id);
//                //todo 删除本地保存的视频文件资源
//                videoActivityService.removeById(activityVideo);
//            }
//        }
    }

    @Override
    public ActivityDTO getActivity(Long id) {
        BackgroundCmsActivity backgroundCmsActivity = this.getOne(new LambdaQueryWrapper<BackgroundCmsActivity>()
                .eq(BackgroundCmsActivity::getId, id));
        if (ObjectUtils.isEmpty(backgroundCmsActivity)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.ACTIVITY_NOT_EXIST_ERROR);
        }
        ActivityDTO activityDTO = backgroundActivityConvertor.toActivityDTO(backgroundCmsActivity);
        if (StringUtils.isNotBlank(backgroundCmsActivity.getContent())){
            String content = HtmlUtil.unescape(backgroundCmsActivity.getContent());
            activityDTO.setContent(content);
        }
        return activityDTO;
//        Integer activityType = backgroundCmsActivity.getColumnId().intValue();
//        Map<String,Object> resultMap=new HashMap<>();
//        if (ActivityCategoryEnum.COMPUTER_RESOURCES == ActivityCategoryEnum.findEnum(activityType)) {
//            ActivityResources resourcesActivity = resourcesActivityService.getResourcesActivity(id);
//            BeanUtil.beanToMap(resourcesActivity,resultMap,true,true);
//        } else if (ActivityCategoryEnum.TEXT == ActivityCategoryEnum.findEnum(activityType)) {
//            ActivityText activityText = textActivityService.getTextActivity(id);
//            BeanUtil.beanToMap(activityText,resultMap,true,true);
//            String unescapeContent = HtmlUtil.unescape(activityText.getContent());
//            resultMap.put("content",unescapeContent);
//        } else if (ActivityCategoryEnum.VIDEO == ActivityCategoryEnum.findEnum(activityType)) {
//            ActivityVideo activityVideo = videoActivityService.getVideoActivity(id);
//            BeanUtil.beanToMap(activityVideo,resultMap,true,true);
//            //通过视频流的形式在前端播放视频
//            HttpServletResponse response = WebUtils.getResponse();
//            String videoUrl = activityVideo.getVideoUrl();
//            Path filePath  = Paths.get(videoUrl);
//            if (Files.exists(filePath)){
//                try {
//                    String contentType = Files.probeContentType(filePath);
//                    if (StringUtils.isNotBlank(contentType)){
//                        response.setContentType(contentType);
//                    }
//                    HttpServletRequest request= WebUtils.getRequest().get();
//                    request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
//                    nonStaticResourceHttpRequestHandler.handleRequest(request,response);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                } catch (ServletException e) {
//                    throw new RuntimeException(e);
//                }
//            }else {
//                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
//            }
//        }
//        activityDTO.setContext(resultMap);
    }

}
