package com.crossroads.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Data
public class Criteria {
    private int page;
    private int amount;
    private int offset;

    public Criteria() {
        this.page = 1;
    }

    public static Criteria create(int page, int amount) {
        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setAmount(amount);
        criteria.setOffset((page-1) * amount);
        return criteria;
    }

    public String getQueryString(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("page", this.page)
                .queryParam("amount", this.amount);
        return builder.toUriString();
    }
}

//    public com.crossroads.app.domain.dto.Criteria create(int page, int amount) {
//        this.page = page;
//        this.amount = amount;
//        this.offset = (page-1) * amount;
//        return this;
//    }
