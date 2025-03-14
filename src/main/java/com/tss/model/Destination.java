package com.tss.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 目的地实体类
 */
@Entity
@Table(name = "destinations")
@Data
public class Destination {
    /**
     * 目的地ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 目的地代码
     */
    @Column(unique = true, nullable = false, length = 10)
    private String code;

    /**
     * 目的地名称
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * 基础票价
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    /**
     * 目的地描述
     */
    @Column(length = 255)
    private String description;
}