package com.sflpro.cafe.repository;

import java.util.List;

import com.sflpro.cafe.model.jpa.CafeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<CafeOrder, Long> {
    @Query("select o from CafeOrder o where o.table.waiter.id = :waiterId")
    List<CafeOrder> findOrderByWaiterId(long waiterId);
}
