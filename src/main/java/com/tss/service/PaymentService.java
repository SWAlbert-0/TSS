package com.tss.service;

import com.tss.device.payment.CardDriver;
import com.tss.device.payment.CashSlot;
import com.tss.model.PaymentType;

import java.math.BigDecimal;

/**
 * 支付服务接口
 */
public interface PaymentService {
    /**
     * 处理卡支付
     * 
     * @param cardDriver 卡驱动器
     * @param amount     支付金额
     * @return 支付是否成功
     */
    boolean processCardPayment(CardDriver cardDriver, BigDecimal amount);

    /**
     * 处理现金支付
     * 
     * @param cashSlot       现金槽
     * @param insertedAmount 投入金额
     * @param requiredAmount 需要支付的金额
     * @return 支付是否成功
     */
    boolean processCashPayment(CashSlot cashSlot, BigDecimal insertedAmount, BigDecimal requiredAmount);

    /**
     * 处理订单支付
     * 
     * @param orderId     订单ID
     * @param paymentType 支付类型
     * @param amount      支付金额
     * @return 支付是否成功
     */
    boolean processOrderPayment(Long orderId, PaymentType paymentType, BigDecimal amount);
}