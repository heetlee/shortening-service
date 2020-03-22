package com.musinsa.shorten.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

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
        @NotNull @Size(min = 1, message = "url must not be null" )
        String url;
    }

    public static QueryReq from(SearchShortenUrl searchShortenUrl){
        return QueryReq.builder().url(searchShortenUrl.getUrl()).build();
    }
}
