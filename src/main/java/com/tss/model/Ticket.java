package com.tss.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;

/**
 * 车票实体类
 */
@Entity
@Table(name = "tickets")
@Data
public class Ticket {
    /**
     * 车票ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 车票编号
     */
    @Column(unique = true, nullable = false, length = 50)
    private String ticketCode;

    /**
     * 目的地代码
     */
    @Column(nullable = false, length = 10)
    private String destinationCode;

    /**
     * 目的地名称
     */
    @Column(nullable = false, length = 50)
    private String destinationName;

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
     * 票价
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * 有效期
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date validDate;

    /**
     * 是否已使用
     */
    @Column(nullable = false)
    private boolean used = false;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;

    /**
     * 关联订单ID
     */
    @Column(nullable = false)
    private Long orderId;

    /**
     * 生成车票编号
     */
    @PrePersist
    public void prePersist() {
        if (ticketCode == null) {
            // 生成票号格式：T + 时间戳 + 4位随机数
            this.ticketCode = "T" + System.currentTimeMillis() +
                    String.format("%04d", (int) (Math.random() * 10000));
        }

        if (createTime == null) {
            this.createTime = new Date();
        }

        if (validDate == null) {
            // 默认有效期为创建时间后30天
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.createTime);
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            this.validDate = calendar.getTime();
        }
    }
}