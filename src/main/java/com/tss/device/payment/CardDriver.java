package com.tss.device.payment;

import com.tss.device.PaymentDevice;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 卡驱动器
 * 用于处理MCard支付
 */
@Component
public class CardDriver extends PaymentDevice {
    /**
     * 当前卡号
     */
    @Getter
    private String cardNumber;

    /**
     * 支付金额
     */
    @Getter
    private BigDecimal amount = BigDecimal.ZERO;

    /**
     * 支付状态
     */
    @Getter
    private boolean paymentSuccess = false;

    @Override
    public boolean init() {
        this.deviceId = "CARD_DRIVER_001";
        this.deviceName = "卡驱动器";
        this.initialized = true;
        return true;
    }

    @Override
    public boolean doSelfTest() {
        // 模拟卡驱动器自检
        return this.initialized;
    }

    @Override
    public void reset() {
        this.cardNumber = null;
        this.amount = BigDecimal.ZERO;
        this.paymentSuccess = false;
    }

    @Override
    public String readInput() {
        return cardNumber;
    }

    @Override
    public boolean processPayment(BigDecimal amount) {
        // 模拟卡支付处理
        if (this.cardNumber != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            this.amount = amount;
            this.paymentSuccess = true;
            return true;
        }
        return false;
    }

    /**
     * 插入卡
     * 
     * @param cardNumber 卡号
     */
    public void insertCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * 移除卡
     */
    public void removeCard() {
        this.cardNumber = null;
    }
}