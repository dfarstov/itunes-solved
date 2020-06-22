package org.astelit.itunes.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class SearchRequest {
    @NotNull
    @PositiveOrZero(message = "больше или равно нулю")
    private Integer page = 0;

    @NotNull
    @PositiveOrZero(message = "больше или равно нулю")
    private Integer size = 10;

    @NotEmpty
    private String query;

    public Pageable pageable() {
        return PageRequest.of(page, size);
    }

    public String getLikeString() {
        return '%' + query.toUpperCase() + '%';
    }
}
