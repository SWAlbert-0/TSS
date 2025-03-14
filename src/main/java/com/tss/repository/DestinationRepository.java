package com.tss.repository;

import com.tss.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * 目的地数据访问接口
 */
@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    /**
     * 根据代码查找目的地
     * 
     * @param code 目的地代码
     * @return 目的地对象
     */
    Optional<Destination> findByCode(String code);

    /**
     * 根据代码查找目的地名称
     * 
     * @param code 目的地代码
     * @return 目的地名称
     */
    @Query("SELECT d.name FROM Destination d WHERE d.code = ?1")
    String findNameByCode(String code);

    /**
     * 根据代码查找基础票价
     * 
     * @param code 目的地代码
     * @return 基础票价
     */
    @Query("SELECT d.basePrice FROM Destination d WHERE d.code = ?1")
    BigDecimal findBasePriceByCode(String code);
}