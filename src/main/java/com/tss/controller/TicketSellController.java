package com.tss.controller;

import com.tss.model.PaymentType;
import com.tss.model.SeatType;
import com.tss.model.TicketType;
import com.tss.service.TicketSellSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 票务销售控制器
 * 处理Web界面与系统的交互
 */
@RestController
@RequestMapping("/api/ticket")
public class TicketSellController {
    private static final Logger logger = LoggerFactory.getLogger(TicketSellController.class);

    @Autowired
    private TicketSellSystem ticketSellSystem;

    /**
     * 初始化系统
     * 
     * @return 初始化状态
     */
    @PostMapping("/init")
    public ResponseEntity<String> initSystem() {
        try {
            logger.info("初始化售票系统");
            ticketSellSystem.init();
            return ResponseEntity.ok("系统初始化完成");
        } catch (Exception e) {
            logger.error("系统初始化失败: {}", e.getMessage());
            return ResponseEntity.badRequest().body("系统初始化失败: " + e.getMessage());
        }
    }

    /**
     * 开始新的购票流程
     * 
     * @return 操作状态
     */
    @PostMapping("/start")
    public ResponseEntity<String> startTicketSale() {
        try {
            logger.info("开始新的购票流程");
            ticketSellSystem.startNewTicketSale();
            return ResponseEntity.ok("开始新的购票流程");
        } catch (Exception e) {
            logger.error("开始购票流程失败: {}", e.getMessage());
            return ResponseEntity.badRequest().body("开始购票流程失败: " + e.getMessage());
        }
    }

    /**
     * 处理目的地输入
     * 
     * @param destinationCode 目的地代码
     * @return 操作状态
     */
    @PostMapping("/destination")
    public ResponseEntity<String> inputDestination(@RequestParam String destinationCode) {
        try {
            logger.info("处理目的地输入: {}", destinationCode);
            ticketSellSystem.processDestinationInput(destinationCode);
            return ResponseEntity.ok("目的地输入成功: " + destinationCode);
        } catch (Exception e) {
            logger.error("处理目的地输入失败: {}", e.getMessage());
            return ResponseEntity.badRequest().body("处理目的地输入失败: " + e.getMessage());
        }
    }

    /**
     * 处理票种选择
     * 
     * @param ticketType 票种类型
     * @param seatType   座位类型
     * @return 操作状态
     */
    @PostMapping("/select-ticket")
    public ResponseEntity<String> selectTicket(@RequestParam TicketType ticketType,
            @RequestParam SeatType seatType) {
        try {
            logger.info("处理票种选择: 票种={}, 座位={}", ticketType, seatType);
            ticketSellSystem.processTicketSelection(ticketType, seatType);
            return ResponseEntity.ok("票种选择成功");
        } catch (Exception e) {
            logger.error("处理票种选择失败: {}", e.getMessage());
            return ResponseEntity.badRequest().body("处理票种选择失败: " + e.getMessage());
        }
    }

    /**
     * 处理支付
     * 
     * @param paymentType 支付类型
     * @param amount      支付金额
     * @return 操作状态
     */
    @PostMapping("/payment")
    public ResponseEntity<String> processPayment(@RequestParam PaymentType paymentType,
            @RequestParam(required = false) BigDecimal amount) {
        try {
            logger.info("处理支付: 类型={}, 金额={}", paymentType, amount);
            ticketSellSystem.processPayment(paymentType, amount);
            return ResponseEntity.ok("支付处理成功");
        } catch (Exception e) {
            logger.error("处理支付失败: {}", e.getMessage());
            return ResponseEntity.badRequest().body("处理支付失败: " + e.getMessage());
        }
    }

    /**
     * 处理继续/取消操作
     * 
     * @param isContinue 是否继续
     * @return 操作状态
     */
    @PostMapping("/action")
    public ResponseEntity<String> processAction(@RequestParam boolean isContinue) {
        try {
            logger.info("处理操作: isContinue={}", isContinue);
            ticketSellSystem.processAction(isContinue);
            return ResponseEntity.ok(isContinue ? "继续购票" : "结束购票");
        } catch (Exception e) {
            logger.error("处理操作失败: {}", e.getMessage());
            return ResponseEntity.badRequest().body("处理操作失败: " + e.getMessage());
        }
    }
}