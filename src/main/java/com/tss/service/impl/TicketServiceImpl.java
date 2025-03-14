package com.tss.service.impl;

import com.tss.model.SeatType;
import com.tss.model.Ticket;
import com.tss.model.TicketType;
import com.tss.repository.TicketRepository;
import com.tss.service.DestinationService;
import com.tss.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 票务服务实现类
 */
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private DestinationService destinationService;

    @Override
    public Ticket createTicket(String destinationCode, TicketType ticketType, SeatType seatType, Long orderId) {
        // 验证目的地代码
        if (!destinationService.isValidDestinationCode(destinationCode)) {
            throw new RuntimeException("无效的目的地代码: " + destinationCode);
        }

        // 创建车票
        Ticket ticket = new Ticket();
        ticket.setDestinationCode(destinationCode);
        ticket.setDestinationName(destinationService.getDestinationNameByCode(destinationCode));
        ticket.setTicketType(ticketType);
        ticket.setSeatType(seatType);
        ticket.setPrice(calculatePrice(destinationCode, ticketType, seatType));
        ticket.setOrderId(orderId);
        ticket.setCreateTime(new Date());

        // 保存车票
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketByCode(String ticketCode) {
        return ticketRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new RuntimeException("车票不存在: " + ticketCode));
    }

    @Override
    public BigDecimal calculatePrice(String destinationCode, TicketType ticketType, SeatType seatType) {
        // 获取基础票价
        BigDecimal basePrice = destinationService.getBasePriceByCode(destinationCode);

        // 根据票种调整价格
        if (ticketType == TicketType.ROUND_TRIP) {
            basePrice = basePrice.multiply(new BigDecimal("1.9")); // 往返票优惠10%
        } else if (ticketType == TicketType.MULTI_TRIP) {
            basePrice = basePrice.multiply(new BigDecimal("0.8")); // 多次票优惠20%
        }

        // 根据座位类型调整价格
        if (seatType == SeatType.FIRST_CLASS) {
            basePrice = basePrice.multiply(new BigDecimal("1.5")); // 一等座加价50%
        } else if (seatType == SeatType.BUSINESS) {
            basePrice = basePrice.multiply(new BigDecimal("2.0")); // 商务座加价100%
        }

        return basePrice;
    }
}