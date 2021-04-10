package com.sflpro.cafe.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TableDTO {

    private String name;
    private Long waiterId;
}
