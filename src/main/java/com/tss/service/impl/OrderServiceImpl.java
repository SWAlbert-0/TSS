package com.tss.service.impl;

import com.tss.model.PaymentType;
import com.tss.model.SeatType;
import com.tss.model.TicketOrder;
import com.tss.model.TicketType;
import com.tss.repository.TicketOrderRepository;
import com.tss.service.DestinationService;
import com.tss.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单服务实现类
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TicketOrderRepository orderRepository;

    @Autowired
    private DestinationService destinationService;

    @Override
    public TicketOrder createOrder(String destinationCode, TicketType ticketType, SeatType seatType,
            BigDecimal amount) {
        // 验证目的地代码
        if (!destinationService.isValidDestinationCode(destinationCode)) {
            throw new RuntimeException("无效的目的地代码: " + destinationCode);
        }

        // 创建订单
        TicketOrder order = new TicketOrder();
        order.setDestinationCode(destinationCode);
        order.setTicketType(ticketType);
        order.setSeatType(seatType);
        order.setTotalAmount(amount);
        order.setPaid(false);
        order.setCreateTime(new Date());

        // 保存订单
        return orderRepository.save(order);
    }

    @Override
    public TicketOrder payOrder(Long orderId, PaymentType paymentType) {
        TicketOrder order = getOrderById(orderId);

        if (order.isPaid()) {
            throw new RuntimeException("订单已支付，不能重复支付");
        }

        order.setPaymentType(paymentType);
        order.setPaid(true);
        order.setPayTime(new Date());

        return orderRepository.save(order);
    }

    @Override
    public TicketOrder completeOrder(Long orderId) {
        TicketOrder order = getOrderById(orderId);

        if (!order.isPaid()) {
            throw new RuntimeException("订单未支付，不能完成");
        }

        return order;
    }

    @Override
    public TicketOrder getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("订单不存在: " + orderId));
    }

    @Override
    public TicketOrder getOrderByCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new RuntimeException("订单不存在: " + orderCode));
    }
}