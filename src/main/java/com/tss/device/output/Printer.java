package com.tss.device.output;

import com.tss.device.OutputDevice;
import com.tss.model.Ticket;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 打印机
 * 用于打印车票
 */
@Component
public class Printer extends OutputDevice {
    /**
     * 最后打印的内容
     */
    @Getter
    private String lastPrintedContent = "";

    @Override
    public boolean init() {
        this.deviceId = "PRINTER_001";
        this.deviceName = "打印机";
        this.initialized = true;
        return true;
    }

    @Override
    public boolean doSelfTest() {
        // 模拟打印机自检
        return this.initialized;
    }

    @Override
    public void reset() {
        this.lastPrintedContent = "";
    }

    @Override
    public boolean output(String content) {
        this.lastPrintedContent = content;
        return true;
    }

    /**
     * 打印车票
     * 
     * @param ticket 要打印的车票
     * @return 打印是否成功
     */
    public boolean printTicket(Ticket ticket) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder sb = new StringBuilder();
        sb.append("======================================\n");
        sb.append("            车票凭证                 \n");
        sb.append("======================================\n");
        sb.append("票号: ").append(ticket.getTicketCode()).append("\n");
        sb.append("目的地: ").append(ticket.getDestinationName()).append(" (").append(ticket.getDestinationCode())
                .append(")\n");
        sb.append("票种: ").append(ticket.getTicketType().getDescription()).append("\n");
        sb.append("座位类型: ").append(ticket.getSeatType().getDescription()).append("\n");
        sb.append("票价: ¥").append(ticket.getPrice()).append("\n");
        sb.append("有效期: ").append(sdf.format(ticket.getValidDate())).append("\n");
        sb.append("打印时间: ").append(sdf.format(new Date())).append("\n");
        sb.append("======================================\n");

        this.lastPrintedContent = sb.toString();
        return true;
    }
}