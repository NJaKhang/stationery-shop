package com.group.sshop.models.dto;

import lombok.Data;

import java.util.Map;

@Data
public class FullCartForm {
    private Map<Long, Integer> quantities;
}
