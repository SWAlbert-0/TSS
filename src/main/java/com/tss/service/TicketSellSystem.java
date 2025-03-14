package com.tss.service;

import com.tss.device.input.ActionKeyboard;
import com.tss.device.input.DestinationKeyboard;
import com.tss.device.input.TicketKindKeyboard;
import com.tss.device.output.Printer;
import com.tss.device.output.Screen;
import com.tss.device.payment.CardDriver;
import com.tss.device.payment.CashSlot;
import com.tss.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 票务销售系统核心类
 * 协调整个购票流程
 */
@Service
public class TicketSellSystem {
    @Autowired
    private DestinationService destinationService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    // 设备组件
    @Autowired
    private Screen screen;

    @Autowired
    private Printer printer;

    @Autowired
    private DestinationKeyboard destinationKeyboard;

    @Autowired
    private TicketKindKeyboard ticketKindKeyboard;

    @Autowired
    private ActionKeyboard actionKeyboard;

    @Autowired
    private CardDriver cardDriver;

    @Autowired
    private CashSlot cashSlot;

    // 当前订单状态
    private TicketOrder currentOrder;
    private String currentDestination;
    private TicketType currentTicketType;
    private SeatType currentSeatType;

    /**
     * 初始化系统
     */
    public void init() {
        // 初始化所有设备
        screen.init();
        printer.init();
        destinationKeyboard.init();
        ticketKindKeyboard.init();
        actionKeyboard.init();
        cardDriver.init();
        cashSlot.init();

        // 执行设备自检
        boolean allDevicesReady = screen.doSelfTest() && printer.doSelfTest() &&
                destinationKeyboard.doSelfTest() && ticketKindKeyboard.doSelfTest() &&
                actionKeyboard.doSelfTest() && cardDriver.doSelfTest() && cashSlot.doSelfTest();

        if (allDevicesReady) {
            screen.display("系统就绪，请开始购票");
        } else {
            screen.display("系统初始化失败，请联系管理员");
        }
    }

    /**
     * 开始新的购票流程
     */
    public void startNewTicketSale() {
        resetCurrentState();
        screen.display("请输入目的地代码");
    }

    /**
     * 处理目的地输入
     * 
     * @param destinationCode 目的地代码
     */
    public void processDestinationInput(String destinationCode) {
        try {
            // 验证目的地代码
            if (!destinationService.isValidDestinationCode(destinationCode)) {
                screen.display("无效的目的地代码，请重新输入");
                return;
            }

            this.currentDestination = destinationCode;
            String destinationName = destinationService.getDestinationNameByCode(destinationCode);
            screen.display("目的地: " + destinationName + " (" + destinationCode + ")\n请选择票种和座位类型");
        } catch (Exception e) {
            screen.display("处理目的地输入时出错: " + e.getMessage());
        }
    }

    /**
     * 处理票种选择
     * 
     * @param ticketType 票种类型
     * @param seatType   座位类型
     */
    public void processTicketSelection(TicketType ticketType, SeatType seatType) {
        try {
            this.currentTicketType = ticketType;
            this.currentSeatType = seatType;

            BigDecimal price = ticketService.calculatePrice(currentDestination, ticketType, seatType);
            screen.display("票种: " + ticketType.getDescription() + ", 座位: " + seatType.getDescription() +
                    "\n票价: ¥" + price + "\n请选择支付方式");

            // 创建订单
            currentOrder = orderService.createOrder(currentDestination, ticketType, seatType, price);
        } catch (Exception e) {
            screen.display("处理票种选择时出错: " + e.getMessage());
        }
    }

    /**
     * 处理支付
     * 
     * @param paymentType 支付类型
     * @param amount      支付金额
     */
    public void processPayment(PaymentType paymentType, BigDecimal amount) {
        try {
            boolean paymentSuccess = false;

            if (paymentType == PaymentType.CARD) {
                paymentSuccess = paymentService.processCardPayment(cardDriver, currentOrder.getTotalAmount());
            } else if (paymentType == PaymentType.CASH) {
                paymentSuccess = paymentService.processCashPayment(cashSlot, amount, currentOrder.getTotalAmount());
            }

            if (paymentSuccess) {
                // 支付成功，完成订单
                orderService.payOrder(currentOrder.getId(), paymentType);

                // 创建票并打印
                Ticket ticket = ticketService.createTicket(currentDestination, currentTicketType, currentSeatType,
                        currentOrder.getId());
                printer.printTicket(ticket);

                screen.display("支付成功，票已打印\n按继续键继续购票，按取消键结束");
            } else {
                screen.display("支付失败，请重试或取消");
            }
        } catch (Exception e) {
            screen.display("处理支付时出错: " + e.getMessage());
        }
    }

    /**
     * 处理继续/取消操作
     * 
     * @param isContinue 是否继续
     */
    public void processAction(boolean isContinue) {
        if (isContinue) {
            startNewTicketSale();
        } else {
            resetCurrentState();
            screen.display("感谢使用，欢迎下次光临");
        }
    }

    /**
     * 重置当前状态
     */
    private void resetCurrentState() {
        currentOrder = null;
        currentDestination = null;
        currentTicketType = null;
        currentSeatType = null;

        // 重置所有设备
        destinationKeyboard.reset();
        ticketKindKeyboard.reset();
        actionKeyboard.reset();
        cardDriver.reset();
        cashSlot.reset();
    }
}