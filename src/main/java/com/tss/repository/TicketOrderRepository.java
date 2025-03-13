package com.tss.repository;

import com.tss.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 订单数据访问接口
 */
@Repository
public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {
    /**
     * 根据订单号查找订单
     * 
     * @param orderCode 订单号
     * @return 订单对象
     */
    Optional<TicketOrder> findByOrderCode(String orderCode);
}