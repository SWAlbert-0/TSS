package com.tss.service;

import com.tss.model.SeatType;
import com.tss.model.Ticket;
import com.tss.model.TicketType;

import java.math.BigDecimal;

/**
 * 票务服务接口
 */
public interface TicketService {
    /**
     * 创建车票
     * 
     * @param destinationCode 目的地代码
     * @param ticketType      票种类型
     * @param seatType        座位类型
     * @param orderId         订单ID
     * @return 创建的车票
     */
    Ticket createTicket(String destinationCode, TicketType ticketType, SeatType seatType, Long orderId);

    /**
     * 根据票号获取车票
     * 
     * @param ticketCode 票号
     * @return 车票对象
     */
    Ticket getTicketByCode(String ticketCode);

    /**
     * 计算票价
     * 
     * @param destinationCode 目的地代码
     * @param ticketType      票种类型
     * @param seatType        座位类型
     * @return 计算后的票价
     */
    BigDecimal calculatePrice(String destinationCode, TicketType ticketType, SeatType seatType);
}