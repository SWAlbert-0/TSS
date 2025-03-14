package com.tss.service.impl;

import com.tss.device.payment.CardDriver;
import com.tss.device.payment.CashSlot;
import com.tss.model.PaymentType;
import com.tss.service.OrderService;
import com.tss.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 支付服务实现类
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private OrderService orderService;

    @Override
    public boolean processCardPayment(CardDriver cardDriver, BigDecimal amount) {
        // 验证卡是否插入
        if (cardDriver.getCardNumber() == null) {
            return false;
        }

        // 处理支付
        return cardDriver.processPayment(amount);
    }

    @Override
    public boolean processCashPayment(CashSlot cashSlot, BigDecimal insertedAmount, BigDecimal requiredAmount) {
        // 如果提供了投入金额，则添加到现金槽
        if (insertedAmount != null && insertedAmount.compareTo(BigDecimal.ZERO) > 0) {
            // 假设全部是纸币
            cashSlot.insertBill(insertedAmount);
        }

        // 处理支付
        return cashSlot.processPayment(requiredAmount);
    }

    @Override
    public boolean processOrderPayment(Long orderId, PaymentType paymentType, BigDecimal amount) {
        try {
            // 支付订单
            orderService.payOrder(orderId, paymentType);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}