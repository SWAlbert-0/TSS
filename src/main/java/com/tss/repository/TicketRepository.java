package com.tss.repository;

import com.tss.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 车票数据访问接口
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    /**
     * 根据票号查找车票
     * 
     * @param ticketCode 票号
     * @return 车票对象
     */
    Optional<Ticket> findByTicketCode(String ticketCode);

    /**
     * 根据订单ID查找车票列表
     * 
     * @param orderId 订单ID
     * @return 车票列表
     */
    List<Ticket> findByOrderId(Long orderId);
}