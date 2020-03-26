package com.example.demo.shorten.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QueryReq {
    String url;

    @Builder
    public QueryReq(String url) {
        this.url = url;
    }

    @Getter @Setter @ToString
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SearchShortenUrl implements BaseReq {
        @NotNull
        @Size(min = 1, message = "url 형식만 지원 합니다.")
        @URL( message = "url 형식만 지원 합니다." )
        String url;
    }

    public static QueryReq from(SearchShortenUrl searchShortenUrl){
        return QueryReq.builder().url(searchShortenUrl.getUrl()).build();
    }
}
