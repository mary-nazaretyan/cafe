package com.sflpro.cafe.model.jpa;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sflpro.cafe.model.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@javax.persistence.Table(name = "orders")
@NoArgsConstructor
public class CafeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<ProductInOrder> products;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;

}
