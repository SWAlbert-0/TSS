package com.tss.service;

import com.tss.model.PaymentType;
import com.tss.model.SeatType;
import com.tss.model.TicketOrder;
import com.tss.model.TicketType;

import java.math.BigDecimal;

/**
 * 订单服务接口
 */
public interface OrderService {
    /**
     * 创建订单
     * 
     * @param destinationCode 目的地代码
     * @param ticketType      票种类型
     * @param seatType        座位类型
     * @param amount          订单金额
     * @return 创建的订单
     */
    TicketOrder createOrder(String destinationCode, TicketType ticketType, SeatType seatType, BigDecimal amount);

    /**
     * 支付订单
     * 
     * @param orderId     订单ID
     * @param paymentType 支付类型
     * @return 支付后的订单
     */
    TicketOrder payOrder(Long orderId, PaymentType paymentType);

    /**
     * 完成订单
     * 
     * @param orderId 订单ID
     * @return 完成的订单
     */
    TicketOrder completeOrder(Long orderId);

    /**
     * 根据ID获取订单
     * 
     * @param orderId 订单ID
     * @return 订单对象
     */
    TicketOrder getOrderById(Long orderId);

    /**
     * 根据订单号获取订单
     * 
     * @param orderCode 订单号
     * @return 订单对象
     */
    TicketOrder getOrderByCode(String orderCode);
}