package com.tss.model;

import lombok.Getter;

/**
 * 支付类型枚举
 */
public enum PaymentType {
    /**
     * 卡支付
     */
    CARD("卡支付"),

    /**
     * 现金支付
     */
    CASH("现金支付");

    /**
     * 支付类型描述
     */
    @Getter
    private final String description;

    PaymentType(String description) {
        this.description = description;
    }
}