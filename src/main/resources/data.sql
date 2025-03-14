-- ========== 表结构定义 ==========
-- 创建目的地表
CREATE TABLE IF NOT EXISTS destinations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    basePrice DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255),
    INDEX idx_destination_code (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建订单表
CREATE TABLE IF NOT EXISTS ticket_orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_code VARCHAR(50) NOT NULL UNIQUE,
    destination_code VARCHAR(10) NOT NULL,
    ticket_type VARCHAR(20) NOT NULL,
    seat_type VARCHAR(20) NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    payment_type VARCHAR(20),
    paid BOOLEAN NOT NULL DEFAULT FALSE,
    create_time DATETIME NOT NULL,
    pay_time DATETIME,
    INDEX idx_order_code (order_code),
    INDEX idx_destination_code (destination_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建车票表
CREATE TABLE IF NOT EXISTS tickets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticket_code VARCHAR(50) NOT NULL UNIQUE,
    destination_code VARCHAR(10) NOT NULL,
    destination_name VARCHAR(50) NOT NULL,
    ticket_type VARCHAR(20) NOT NULL,
    seat_type VARCHAR(20) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    valid_date DATETIME NOT NULL,
    used BOOLEAN NOT NULL DEFAULT FALSE,
    create_time DATETIME NOT NULL,
    order_id BIGINT NOT NULL,
    INDEX idx_ticket_code (ticket_code),
    INDEX idx_order_id (order_id),
    INDEX idx_destination_code (destination_code),
    CONSTRAINT fk_ticket_order FOREIGN KEY (order_id) REFERENCES ticket_orders (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========== 初始化目的地数据 ==========
INSERT INTO destinations (code, name, basePrice, description) VALUES
('2U0', '总站', 50.00, '市中心总站'),
('A01', '北站', 30.00, '北部火车站'),
('B02', '南站', 35.00, '南部火车站'),
('C03', '东站', 25.00, '东部公交枢纽'),
('D04', '西站', 28.00, '西部公交枢纽'),
('E05', '机场', 80.00, '国际机场'),
('F06', '大学城', 20.00, '大学城站'),
('G07', '科技园', 40.00, '科技园区站'),
('H08', '商业区', 45.00, '中央商业区'),
('I09', '体育中心', 38.00, '体育中心站')
ON DUPLICATE KEY UPDATE
code = VALUES(code);

-- ========== 确保票价更新 ==========
-- 更新目的地表中的基础票价（如果有必要）
UPDATE destinations SET basePrice = 50.00 WHERE code = '2U0'; -- 总站
UPDATE destinations SET basePrice = 30.00 WHERE code = 'A01'; -- 北站
UPDATE destinations SET basePrice = 35.00 WHERE code = 'B02'; -- 南站
UPDATE destinations SET basePrice = 25.00 WHERE code = 'C03'; -- 东站
UPDATE destinations SET basePrice = 28.00 WHERE code = 'D04'; -- 西站
UPDATE destinations SET basePrice = 80.00 WHERE code = 'E05'; -- 机场
UPDATE destinations SET basePrice = 20.00 WHERE code = 'F06'; -- 大学城
UPDATE destinations SET basePrice = 40.00 WHERE code = 'G07'; -- 科技园
UPDATE destinations SET basePrice = 45.00 WHERE code = 'H08'; -- 商业区
UPDATE destinations SET basePrice = 38.00 WHERE code = 'I09'; -- 体育中心

-- 确保数据一致性（如果以上操作没有正确执行）
INSERT INTO destinations (code, name, basePrice, description)
VALUES 
('2U0', '总站', 50.00, '市中心总站'),
('A01', '北站', 30.00, '北部火车站'),
('B02', '南站', 35.00, '南部火车站'),
('C03', '东站', 25.00, '东部公交枢纽'),
('D04', '西站', 28.00, '西部公交枢纽'),
('E05', '机场', 80.00, '国际机场'),
('F06', '大学城', 20.00, '大学城站'),
('G07', '科技园', 40.00, '科技园区站'),
('H08', '商业区', 45.00, '中央商业区'),
('I09', '体育中心', 38.00, '体育中心站')
ON DUPLICATE KEY UPDATE 
    name = VALUES(name),
    basePrice = VALUES(basePrice),
    description = VALUES(description); 