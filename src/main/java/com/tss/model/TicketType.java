package com.tss.model;

import lombok.Getter;

/**
 * 票种类型枚举
 */
public enum TicketType {
    /**
     * 单程票
     */
    SINGLE_TRIP("单程票"),

    /**
     * 往返票
     */
    ROUND_TRIP("往返票"),

    /**
     * 多次票
     */
    MULTI_TRIP("多次票");

    /**
     * 票种描述
     */
    @Getter
    private final String description;

    TicketType(String description) {
        this.description = description;
    }
}