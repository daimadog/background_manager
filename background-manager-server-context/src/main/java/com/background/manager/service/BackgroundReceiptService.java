package com.background.manager.service;

import com.background.manager.model.BackgroundReceipt;
import com.background.manager.model.dto.request.receipt.*;
import com.background.manager.model.dto.response.receipt.ReceiptDTO;
import com.background.manager.model.dto.response.receipt.ReceiptDigestDTO;
import com.background.manager.model.dto.response.receipt.ReceiptRecordDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description:发票管理接口
 * @Author: 杜黎明
 * @Date: 2023/01/06 17:11:58
 * @Version: 1.0.0
 */
public interface BackgroundReceiptService extends IService<BackgroundReceipt> {
    /**
     * Description: 新增发票
     * @param request 请求
     * @return {@link String }
     * @author 杜黎明
     * @date 2023/01/09 08:52:50
     */
    void add(AddReceiptRequest request);

    /**
     * Description: 分页查询发票
     * @param request 请求
     * @return {@link IPage }<{@link ReceiptDigestDTO }>
     * @author 杜黎明
     * @date 2023/01/09 09:08:10
     */
    IPage<ReceiptDigestDTO> pageQuery(PageQueryReceiptRequest request);

    /**
     * Description: 编辑发票
     * @param request 请求
     * @author 杜黎明
     * @date 2023/01/09 09:24:01
     */
    void modify(ModifyReceiptRequest request);

    /**
     * Description: 删除发票
     * @param id id
     * @author 杜黎明
     * @date 2023/01/09 09:31:33
     */
    void delete(Long id);

    /**
     * Description: 查看发票详情
     * @param id id
     * @return {@link ReceiptDTO }
     * @author 杜黎明
     * @date 2023/01/09 09:41:38
     */
    ReceiptDTO getReceipt(Long id);

    /**
     * Description: 审核发票状态并添加备注
     * @param request 请求
     * @author 杜黎明
     * @date 2023/01/09 16:38:28
     */
    void audit(AuditReceiptRequest request);
}
