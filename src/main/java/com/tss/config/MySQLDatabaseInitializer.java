package com.tss.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * MySQL数据库初始化配置
 * 用于在应用启动时创建数据库（如果不存在）
 */
@Configuration
public class MySQLDatabaseInitializer {
    private static final Logger logger = LoggerFactory.getLogger(MySQLDatabaseInitializer.class);

    @Autowired
    private Environment env;

    /**
     * 创建数据库初始化器
     * 
     * @param dataSource 数据源
     * @return 命令行运行器
     */
    @Bean
    @Order(1)
    public CommandLineRunner createDatabaseIfNotExists(DataSource dataSource) {
        return args -> {
            // 由于数据库已经创建，这里不再尝试创建数据库
            // 只记录日志表明系统正在使用已存在的数据库
            logger.info("使用已存在的数据库: tss_db");

            // 可以在这里执行其他初始化操作，如检查表结构等
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            try {
                // 检查数据库连接
                jdbcTemplate.execute("SELECT 1");
                logger.info("数据库连接正常");
            } catch (Exception e) {
                logger.error("数据库连接测试失败: {}", e.getMessage());
            }
        };
    }
}