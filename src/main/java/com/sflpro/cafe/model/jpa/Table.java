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

import com.sflpro.cafe.model.enums.TableStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@javax.persistence.Table(name = "cafe_table")
@NoArgsConstructor
public class Table {

    public Table(String name, User waiter) {
        this.name = name;
        this.waiter = waiter;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private User waiter;

    @OneToMany(mappedBy = "table")
    private List<CafeOrder> order;

    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

}
