package com.sflpro.cafe.controller;

import java.util.List;

import com.sflpro.cafe.model.dto.TableDTO;
import com.sflpro.cafe.model.jpa.Table;
import com.sflpro.cafe.service.TableService;
import com.sflpro.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;
    private final UserService userService;

    @GetMapping("/add-table")
    public ModelAndView addTable() {

        ModelAndView modelAndView = new ModelAndView("addTable");
        modelAndView.addObject("table", new TableDTO());
        modelAndView.addObject("waiters", userService.getAllWaiters());

        return modelAndView;
    }

    @PostMapping("/add-table")
    public ModelAndView addTable(@ModelAttribute TableDTO table) {

        ModelAndView modelAndView = new ModelAndView("redirect:/home");

        tableService.save(table);

        return modelAndView;
    }

    @GetMapping("/table-list")
    public ModelAndView listTable() {

        ModelAndView modelAndView = new ModelAndView("managerHome");

        List<Table> tableList = tableService.getAllTables();
        modelAndView.addObject("tables", tableList);

        return modelAndView;
    }
}
