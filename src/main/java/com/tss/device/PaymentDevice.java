package com.tss.device;

import java.math.BigDecimal;

/**
 * 支付设备抽象类
 * 所有支付类设备的基础类
 */
public abstract class PaymentDevice extends InputDevice {
    /**
     * 处理支付
     * 
     * @param amount 支付金额
     * @return 支付是否成功
     */
    public abstract boolean processPayment(BigDecimal amount);

    /**
     * 获取支付金额
     * 
     * @return 支付金额
     */
    public abstract BigDecimal getAmount();
}