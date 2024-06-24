package com.group.sshop.models.dto.datatable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
public class DataTableRequest {
    @NotNull
    @Min(0)
    private Integer draw;
    @NotNull
    @Min(0)
    private Integer start;
    @NotNull
    @Min(0)
    private Integer length;
    @NotBlank
    private List<Column> columns = List.of();
    private List<Order> order = List.of();
    private Search search;

    public Pageable getPageable() {
        List<Sort.Order> orders = this.order.stream().map(o -> {
                    Sort.Direction direction = Sort.Direction.fromString(o.dir);
                    String property = this.columns.get(o.column).name;
                    return new Sort.Order(direction, property);

                })
                .toList();
        Sort sort = Sort.by(orders);
        return PageRequest.of(start / length, length, sort);
    }


    @Data
    public static class Column {
        private String name;
        private String data;
        private boolean searchable;
        private boolean orderable;
    }

    @Data
    public static class Search {
        private String value;
        private boolean regex;
    }


    @Data
    public static class Order {
        @NotNull
        @Min(0L)
        private Integer column;
        @NotNull
        @Pattern(regexp = "(desc|asc)")
        private String dir;

    }


}
