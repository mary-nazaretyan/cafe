package com.sflpro.cafe.repository;

import java.util.List;

import com.sflpro.cafe.model.jpa.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TableRepository extends JpaRepository<Table, Long> {

    @Query("select t from Table t where t.waiter.id = :id")
    List<Table> findByWaiterId(long id);
}
