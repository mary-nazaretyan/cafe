package com.sflpro.cafe.service;

import java.util.List;

import com.sflpro.cafe.model.dto.TableDTO;
import com.sflpro.cafe.model.enums.TableStatus;
import com.sflpro.cafe.model.jpa.Table;
import com.sflpro.cafe.repository.TableRepository;
import com.sflpro.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;
    private final UserRepository userRepository;

    public void save(TableDTO tableDTO) {

        Table table = new Table();

        table.setTableStatus(TableStatus.AVAILABLE);
        table.setName(tableDTO.getName());
        table.setWaiter(userRepository.findById(tableDTO.getWaiterId()).get());


        tableRepository.save(table);
    }

    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    public List<Table> getTablesByWaiterId(Long waiterId) {
        return tableRepository.findByWaiterId(waiterId);
    }
}
