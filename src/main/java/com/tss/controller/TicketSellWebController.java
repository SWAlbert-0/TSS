package com.tss.controller;

import com.tss.device.output.Screen;
import com.tss.model.Destination;
import com.tss.model.PaymentType;
import com.tss.model.SeatType;
import com.tss.model.TicketType;
import com.tss.service.DestinationService;
import com.tss.service.TicketSellSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 票务销售Web控制器
 * 处理Web界面显示
 */
@Controller
public class TicketSellWebController {
    private static final Logger logger = LoggerFactory.getLogger(TicketSellWebController.class);

    @Autowired
    private TicketSellSystem ticketSellSystem;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private Screen screen;

    /**
     * 首页
     * 
     * @param model 模型
     * @return 视图名
     */
    @GetMapping("/")
    public String index(Model model) {
        try {
            logger.info("访问售票终端首页");

            // 初始化系统
            ticketSellSystem.init();

            // 获取所有目的地
            List<Destination> destinations = destinationService.getAllDestinations();
            logger.info("加载了{}个目的地信息", destinations.size());

            // 添加数据到模型
            model.addAttribute("destinations", destinations);
            model.addAttribute("ticketTypes", TicketType.values());
            model.addAttribute("seatTypes", SeatType.values());
            model.addAttribute("paymentTypes", PaymentType.values());
            model.addAttribute("screenDisplay", screen.getCurrentDisplay());

            // 添加系统状态信息
            model.addAttribute("systemReady", true);
            model.addAttribute("systemVersion", "1.0.0");
            model.addAttribute("systemTime", new java.util.Date());

            return "index";
        } catch (Exception e) {
            logger.error("加载首页失败: {}", e.getMessage());
            model.addAttribute("error", "系统初始化失败: " + e.getMessage());
            model.addAttribute("systemReady", false);
            return "error";
        }
    }

    /**
     * 错误页面
     * 
     * @return 错误视图名
     */
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}