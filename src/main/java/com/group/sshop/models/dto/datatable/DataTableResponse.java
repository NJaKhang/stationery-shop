package com.group.sshop.models.dto.datatable;

import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class DataTableResponse<T>{
    private int draw;
    private long recordsTotal = 0L;
    private long recordsFiltered = 0L;
    private List<T> data = Collections.emptyList();
    private String error;
}
