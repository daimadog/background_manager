package com.background.manager.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.background.manager.constant.StatusConstant;
import com.background.manager.convert.BackgroundColumnConvertor;
import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.model.dto.request.column.AddColumnRequest;
import com.background.manager.model.dto.request.column.ListQueryColumnRequest;
import com.background.manager.model.dto.request.column.ModifyColumnRequest;
import com.background.manager.model.dto.response.column.ColumnDigestDTO;
import com.background.manager.model.BackgroundCmsColumn;
import com.background.manager.exception.BadRequestException;
import com.background.manager.mapper.BackgroundColumnMapper;
import com.background.manager.service.BackgroundColumnService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description: 栏目接口实现类
 * @Author: 杜黎明
 * @Date: 2022/10/26 10:38:28
 * @Version: 1.0.0
 */
@Service
public class BackgroundColumnServiceImpl extends ServiceImpl<BackgroundColumnMapper, BackgroundCmsColumn> implements BackgroundColumnService {

   @Resource
   private BackgroundColumnConvertor backgroundColumnConvertor;

    @Override
    public List<ColumnDigestDTO> listQuery(ListQueryColumnRequest request) {
        List<BackgroundCmsColumn> columnList = this.list(new LambdaQueryWrapper<BackgroundCmsColumn>()
                .eq(StringUtils.isNotBlank(request.getName()), BackgroundCmsColumn::getName, request.getName())
                .eq(ObjectUtils.isNotEmpty(request.getStatus()), BackgroundCmsColumn::getStatus, request.getStatus())
                .orderByAsc(BackgroundCmsColumn::getWeight)
        );
        if (CollectionUtil.isEmpty(columnList)){
            return new ArrayList<>();
        }
        return this.treeColumnDigestDTO(columnList);
    }

    @Override
    public String add(AddColumnRequest request) {
        BackgroundCmsColumn repeatNameColumn = this.getOne(new LambdaQueryWrapper<BackgroundCmsColumn>()
                .eq(BackgroundCmsColumn::getName, request.getName()));
        if (ObjectUtil.isNotNull(repeatNameColumn)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.COLUMN_NAME_REPEAT_ERROR);
        }
        BackgroundCmsColumn repeatEnNameColumn = this.getOne(new LambdaQueryWrapper<BackgroundCmsColumn>()
                .eq(BackgroundCmsColumn::getEnName,request.getEnName()));
        if (ObjectUtil.isNotNull(repeatEnNameColumn)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.COLUMN_ENGLISH_NAME_REPEAT_ERROR);
        }
        BackgroundCmsColumn backgroundCmsColumn = backgroundColumnConvertor.toBackgroundCmsColumn(request);
        //todo 栏目onTap字段是否默认写死
        backgroundCmsColumn.setCreateTime(LocalDateTime.now());
        backgroundCmsColumn.setUpdateTime(LocalDateTime.now());
        if (ObjectUtils.isNotEmpty(StpUtil.getLoginIdDefaultNull())){
            String creator=StpUtil.getLoginIdAsString();
            backgroundCmsColumn.setCreator(creator);
            backgroundCmsColumn.setModifier(creator);
        }
        /**添加默认权重最大值*/
        backgroundCmsColumn.setWeight(backgroundCmsColumn.getId());
        this.save(backgroundCmsColumn);
        return backgroundCmsColumn.getName();
    }

    @Override
    public void modify(ModifyColumnRequest request) {
        BackgroundCmsColumn repeatNameColumn = this.getOne(new LambdaQueryWrapper<BackgroundCmsColumn>()
                .ne(BackgroundCmsColumn::getId,request.getId())
                .eq(BackgroundCmsColumn::getName, request.getName()));
        if (ObjectUtil.isNotNull(repeatNameColumn)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.COLUMN_NAME_REPEAT_ERROR);
        }
        BackgroundCmsColumn repeatEnNameColumn = this.getOne(new LambdaQueryWrapper<BackgroundCmsColumn>()
                .ne(BackgroundCmsColumn::getId,request.getId())
                .eq(BackgroundCmsColumn::getEnName,request.getEnName()));
        if (ObjectUtil.isNotNull(repeatEnNameColumn)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.COLUMN_ENGLISH_NAME_REPEAT_ERROR);
        }
        BackgroundCmsColumn backgroundCmsColumn = backgroundColumnConvertor.toBackgroundCmsColumn(request);
        backgroundCmsColumn.setUpdateTime(LocalDateTime.now());
        if (StpUtil.isLogin()){
            backgroundCmsColumn.setModifier(StpUtil.getLoginIdAsString());
        }
        this.updateById(backgroundCmsColumn);
    }

    @Override
    public void delete(Long id) {
        BackgroundCmsColumn backgroundCmsColumn = this.getOne(new LambdaQueryWrapper<BackgroundCmsColumn>()
                .eq(BackgroundCmsColumn::getId,id));
        if (ObjectUtil.isNull(backgroundCmsColumn)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.COLUMN_NOT_EXIST_ERROR);
        }
        if (StatusConstant.parent_Id.equals(backgroundCmsColumn.getParentId().intValue())){
            List<BackgroundCmsColumn> childrenColumns = this.list(new LambdaQueryWrapper<BackgroundCmsColumn>()
                    .eq(BackgroundCmsColumn::getParentId, id));
            this.removeBatchByIds(childrenColumns);
        }
        this.removeById(id);
    }

    @Override
    public void moveUpColumn(Long id) {
        BackgroundCmsColumn currentColumn= this.getOne(new LambdaQueryWrapper<BackgroundCmsColumn>()
                .eq(BackgroundCmsColumn::getId,id));
        if (ObjectUtil.isNull(currentColumn)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.COLUMN_NOT_EXIST_ERROR);
        }
        //拿到上一条栏目菜单记录
        BackgroundCmsColumn previousColumn = this.baseMapper.selectPreviousColumn(currentColumn.getWeight());
        if (ObjectUtil.isNull(previousColumn)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.COLUMN_NOT_EXIST_ERROR);
        }
        //交换当前栏目与上一条栏目的weight权重
        Long previousWeight = previousColumn.getWeight();
        previousColumn.setWeight(currentColumn.getWeight());
        this.updateById(previousColumn);
        currentColumn.setWeight(previousWeight);
        this.updateById(currentColumn);
    }

    @Override
    public void moveDownColumn(Long id) {
        BackgroundCmsColumn currentColumn= this.getOne(new LambdaQueryWrapper<BackgroundCmsColumn>()
                .eq(BackgroundCmsColumn::getId,id));
        if (ObjectUtil.isNull(currentColumn)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.COLUMN_NOT_EXIST_ERROR);
        }
        //拿到上一条栏目菜单记录
        BackgroundCmsColumn nextColumn = this.baseMapper.selectNextColumn(currentColumn.getWeight());
        if (ObjectUtil.isNull(nextColumn)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.COLUMN_NOT_EXIST_ERROR);
        }
        //交换当前栏目与上一条栏目的weight权重
        Long previousWeight = nextColumn.getWeight();
        nextColumn.setWeight(currentColumn.getWeight());
        this.updateById(nextColumn);
        currentColumn.setWeight(previousWeight);
        this.updateById(currentColumn);
    }

    /**
     * Description: 树形列表展示栏目
     * @param columnList 栏目对象集合
     * @return {@link List }<{@link ColumnDigestDTO }>
     * @author 杜黎明
     * @date 2022/10/26 13:14:29
     */
    private List<ColumnDigestDTO> treeColumnDigestDTO(List<BackgroundCmsColumn> columnList) {
        //栏目树形列表结构集合
        List<ColumnDigestDTO> columnTreeDigestDTOS=new ArrayList<>();
        if (CollectionUtil.isEmpty(columnList)){
            return columnTreeDigestDTOS;
        }
        List<ColumnDigestDTO> columnDigestDTOS=backgroundColumnConvertor.toColumnDigestDTOS(columnList);
        //一级栏目父节点
        List<ColumnDigestDTO> parentColumnList=new ArrayList<>();
        if (CollectionUtil.isNotEmpty(columnDigestDTOS)){
            for (ColumnDigestDTO columnDigestDTO:columnDigestDTOS){
                if (StatusConstant.parent_Id.equals(columnDigestDTO.getParentId().intValue())){
                    parentColumnList.add(columnDigestDTO);
                }
            }
        }
        //所有的栏目对象集合
        List<ColumnDigestDTO> allColumnList=this.findAllColumn();
        //封装树形结构
        if (CollectionUtil.isNotEmpty(parentColumnList)){
            for (ColumnDigestDTO amp:parentColumnList){
                ColumnDigestDTO columnTreeDigestDTO=new ColumnDigestDTO();
                columnTreeDigestDTO=amp;
                columnTreeDigestDTO.setChildren(getChildren(amp.getId(),allColumnList));
                columnTreeDigestDTOS.add(amp);
            }
        }
        if (CollectionUtil.isEmpty(columnTreeDigestDTOS)) {
            return columnDigestDTOS;
        }
        return columnTreeDigestDTOS;
    }


    /**
     * Description: 查找所有的栏目列表
     * @return {@link List }<{@link ColumnDigestDTO }>
     * @author 杜黎明
     * @date 2022/11/03 19:15:16
     */
    private List<ColumnDigestDTO> findAllColumn() {
        List<BackgroundCmsColumn> backgroundCmsColumnList = this.list(new LambdaQueryWrapper<BackgroundCmsColumn>());
        return backgroundColumnConvertor.toColumnDigestDTOS(backgroundCmsColumnList);
    }

    /**
     * Description: 获取子栏目集合
     * @param id 父节点ID
     * @param columnDigestDTOS 栏目集合对象
     * @return {@link List }<{@link ColumnDigestDTO }>
     * @author 杜黎明
     * @date 2022/10/26 13:32:52
     */
    private List<ColumnDigestDTO> getChildren(Long id, List<ColumnDigestDTO> columnDigestDTOS) {
        List<ColumnDigestDTO> childrenList=new ArrayList<>();
        for (ColumnDigestDTO columnTreeDigestDTO:columnDigestDTOS){
            if (id.equals(columnTreeDigestDTO.getParentId())){
                childrenList.add(columnTreeDigestDTO);
            }
        }
        //递归遍历
        if (CollectionUtil.isNotEmpty(childrenList)){
            for (ColumnDigestDTO columnTreeDigestDTO:childrenList){
                columnTreeDigestDTO.setChildren(getChildren(columnTreeDigestDTO.getId(),columnDigestDTOS));
            }
        }
        return childrenList;
    }

}
