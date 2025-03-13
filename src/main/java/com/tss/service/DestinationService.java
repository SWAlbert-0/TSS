package com.tss.service;

import com.tss.model.Destination;

import java.math.BigDecimal;
import java.util.List;

/**
 * 目的地服务接口
 */
public interface DestinationService {
    /**
     * 获取所有目的地
     * 
     * @return 目的地列表
     */
    List<Destination> getAllDestinations();

    /**
     * 根据代码获取目的地
     * 
     * @param code 目的地代码
     * @return 目的地对象
     */
    Destination getDestinationByCode(String code);

    /**
     * 根据代码获取目的地名称
     * 
     * @param code 目的地代码
     * @return 目的地名称
     */
    String getDestinationNameByCode(String code);

    /**
     * 根据代码获取基础票价
     * 
     * @param code 目的地代码
     * @return 基础票价
     */
    BigDecimal getBasePriceByCode(String code);

    /**
     * 验证目的地代码是否有效
     * 
     * @param code 目的地代码
     * @return 是否有效
     */
    boolean isValidDestinationCode(String code);
}