package com.background.manager.cache.constant;

/**
 * ClassName: com.zero.distance.mall.common.cache.constant
 * Description:
 * 选品评分计算缓存名称（信息以单个选品为维度）增量变更数据需维护redis，如redis数据清空，需启动时执行@PostConstruct对应方法
 *
 * @author: lizhoukai
 * @since: 2022/7/6 15:32
 */
public interface GoodsEvaluateNames {

    /**
     * 是否需要初始化
     */
    String INIT = "init";

    /**
     * 更新的选品信息
     */
    String GOODS = "goods";

    /**
     * 更新的工厂信息
     */
    String FACTORIES = "factories";

    /**
     * 三十日成交总量
     */
    String MONTHMAKEAMOUNT = "monthMakeAmount";

    /**
     * 成交总量
     */
    String MAKEAMOUNT = "makeAmount";

    /**
     * 工厂订单数（增量变更）
     */
    String ORDERS = "orders";

    /**
     * 工厂信息完整度（增量变更）
     */
    String FACTORYINFOINTEGRITY = "factoryInfoIntegrity";

    /**
     * 工厂评价（增量变更）
     */
    String FACTORYEVALUATE = "factoryEvaluate";

    /**
     * 选品不良数（增量变更）
     */
    String BADNESSGOODS = "badNessGoods";

    /**
     * 选品实际生产数（增量变更）
     */
    String REALITYGOODS = "realityGoods";

    /**
     * 订单完成总数（结单和验证状态）
     */
    String COMPLETEORDER = "completeOrder";

    /**
     * 延期订单总数
     */
    String POSTPONEORDER = "postponeOrder";

    /**
     * 选品资质数量（增量变更）
     */
    String PRODUCTQUALIFICATION = "productQualification";

    /**
     * 新选品附加分
     */
    String NEWGOODSGRADE = "newGoodsGrade";



}
