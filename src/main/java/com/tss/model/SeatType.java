package com.tss.model;

import lombok.Getter;

/**
 * 座位类型枚举
 */
public enum SeatType {
    /**
     * 普通座
     */
    STANDARD("普通座"),

    /**
     * 一等座
     */
    FIRST_CLASS("一等座"),

    /**
     * 商务座
     */
    BUSINESS("商务座");

    /**
     * 座位类型描述
     */
    @Getter
    private final String description;

    SeatType(String description) {
        this.description = description;
    }
}