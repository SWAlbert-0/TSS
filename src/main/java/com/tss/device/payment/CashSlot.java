package com.tss.device.payment;

import com.tss.device.PaymentDevice;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 现金槽
 * 用于处理硬币和纸币支付
 */
@Component
public class CashSlot extends PaymentDevice {
    /**
     * 硬币金额
     */
    @Getter
    private BigDecimal coinAmount = BigDecimal.ZERO;

    /**
     * 纸币金额
     */
    @Getter
    private BigDecimal billAmount = BigDecimal.ZERO;

    /**
     * 总金额
     */
    @Getter
    private BigDecimal totalAmount = BigDecimal.ZERO;

    /**
     * 支付状态
     */
    @Getter
    private boolean paymentSuccess = false;

    @Override
    public boolean init() {
        this.deviceId = "CASH_SLOT_001";
        this.deviceName = "现金槽";
        this.initialized = true;
        return true;
    }

    @Override
    public boolean doSelfTest() {
        // 模拟现金槽自检
        return this.initialized;
    }

    @Override
    public void reset() {
        this.coinAmount = BigDecimal.ZERO;
        this.billAmount = BigDecimal.ZERO;
        this.totalAmount = BigDecimal.ZERO;
        this.paymentSuccess = false;
    }

    @Override
    public String readInput() {
        return totalAmount.toString();
    }

    @Override
    public BigDecimal getAmount() {
        return totalAmount;
    }

    @Override
    public boolean processPayment(BigDecimal requiredAmount) {
        // 检查总金额是否足够支付
        if (totalAmount.compareTo(requiredAmount) >= 0) {
            this.paymentSuccess = true;
            return true;
        }
        return false;
    }

    /**
     * 投入硬币
     * 
     * @param amount 硬币金额
     */
    public void insertCoin(BigDecimal amount) {
        this.coinAmount = this.coinAmount.add(amount);
        updateTotalAmount();
    }

    /**
     * 投入纸币
     * 
     * @param amount 纸币金额
     */
    public void insertBill(BigDecimal amount) {
        this.billAmount = this.billAmount.add(amount);
        updateTotalAmount();
    }

    /**
     * 更新总金额
     */
    private void updateTotalAmount() {
        this.totalAmount = this.coinAmount.add(this.billAmount);
    }
}