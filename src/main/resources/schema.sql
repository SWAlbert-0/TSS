-- 创建目的地表
CREATE TABLE IF NOT EXISTS destinations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    base_price DECIMAL(10, 2) NOT NULL,
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