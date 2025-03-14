package com.tss.config;

import com.tss.model.Destination;
import com.tss.repository.DestinationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 数据初始化类
 * 用于初始化系统所需的基础数据
 */
@Component
public class DataInitializer implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        logger.info("开始初始化数据...");

        // 初始化目的地数据
        initDestinations();

        logger.info("数据初始化完成");
    }

    /**
     * 初始化目的地数据
     */
    private void initDestinations() {
        // 检查是否已有数据
        if (destinationRepository.count() > 0) {
            logger.info("目的地数据已存在，跳过初始化");
            return;
        }

        logger.info("开始初始化目的地数据");

        // 创建目的地数据
        createDestination("2U0", "总站", new BigDecimal("50.00"), "市中心总站");
        createDestination("A01", "北站", new BigDecimal("30.00"), "北部火车站");
        createDestination("B02", "南站", new BigDecimal("35.00"), "南部火车站");
        createDestination("C03", "东站", new BigDecimal("25.00"), "东部公交枢纽");
        createDestination("D04", "西站", new BigDecimal("28.00"), "西部公交枢纽");
        createDestination("E05", "机场", new BigDecimal("80.00"), "国际机场");
        createDestination("F06", "大学城", new BigDecimal("20.00"), "大学城站");
        createDestination("G07", "科技园", new BigDecimal("40.00"), "科技园区站");
        createDestination("H08", "商业区", new BigDecimal("45.00"), "中央商业区");
        createDestination("I09", "体育中心", new BigDecimal("38.00"), "体育中心站");

        logger.info("目的地数据初始化完成");
    }

    /**
     * 创建目的地
     * 
     * @param code        代码
     * @param name        名称
     * @param basePrice   基础票价
     * @param description 描述
     */
    private void createDestination(String code, String name, BigDecimal basePrice, String description) {
        try {
            Destination destination = new Destination();
            destination.setCode(code);
            destination.setName(name);
            destination.setBasePrice(basePrice);
            destination.setDescription(description);
            destinationRepository.save(destination);
            logger.debug("创建目的地: {} - {}", code, name);
        } catch (Exception e) {
            logger.error("创建目的地失败: {} - {}, 错误: {}", code, name, e.getMessage());
        }
    }
}