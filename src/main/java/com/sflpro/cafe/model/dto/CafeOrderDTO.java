package com.sflpro.cafe.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CafeOrderDTO {

    private String status;
    private long tableId;

}
