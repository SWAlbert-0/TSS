package com.tss.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 车票订单实体类
 */
@Entity
@Table(name = "ticket_orders")
@Data
public class TicketOrder {
    /**
     * 订单ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单编号
     */
    @Column(unique = true, nullable = false, length = 50)
    private String orderCode;

    /**
     * 目的地代码
     */
    @Column(nullable = false, length = 10)
    private String destinationCode;

    /**
     * 票种类型
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TicketType ticketType;

    /**
     * 座位类型
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SeatType seatType;

    /**
     * 订单总金额
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    /**
     * 支付类型
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PaymentType paymentType;

    /**
     * 支付状态
     */
    @Column(nullable = false)
    private boolean paid = false;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;

    /**
     * 支付时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date payTime;

    /**
     * 生成订单编号
     */
    @PrePersist
    public void prePersist() {
        if (orderCode == null) {
            // 生成订单号格式：O + 时间戳 + 4位随机数
            this.orderCode = "O" + System.currentTimeMillis() +
                    String.format("%04d", (int) (Math.random() * 10000));
        }

        if (createTime == null) {
            this.createTime = new Date();
        }
    }
}