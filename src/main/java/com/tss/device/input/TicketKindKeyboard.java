package com.tss.device.input;

import com.tss.device.InputDevice;
import com.tss.model.SeatType;
import com.tss.model.TicketType;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * 车票类型键盘
 * 用于选择车票种类和座位类型
 */
@Component
public class TicketKindKeyboard extends InputDevice {
    /**
     * 当前选择的票种
     */
    @Getter
    private TicketType selectedTicketType;

    /**
     * 当前选择的座位类型
     */
    @Getter
    private SeatType selectedSeatType;

    @Override
    public boolean init() {
        this.deviceId = "TICKET_KB_001";
        this.deviceName = "车票类型键盘";
        this.initialized = true;
        return true;
    }

    @Override
    public boolean doSelfTest() {
        // 模拟键盘自检
        return this.initialized;
    }

    @Override
    public void reset() {
        this.selectedTicketType = null;
        this.selectedSeatType = null;
    }

    @Override
    public String readInput() {
        if (selectedTicketType != null && selectedSeatType != null) {
            return selectedTicketType.name() + "," + selectedSeatType.name();
        }
        return "";
    }

    /**
     * 选择票种
     * 
     * @param ticketType 票种类型
     */
    public void selectTicketType(TicketType ticketType) {
        this.selectedTicketType = ticketType;
    }

    /**
     * 选择座位类型
     * 
     * @param seatType 座位类型
     */
    public void selectSeatType(SeatType seatType) {
        this.selectedSeatType = seatType;
    }
}