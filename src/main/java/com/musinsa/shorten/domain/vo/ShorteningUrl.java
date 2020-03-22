package com.musinsa.shorten.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShorteningUrl {

  @NonNull
  @JsonProperty("shortening_url")
  String shorteningUrl;

  @NonNull
  @JsonProperty("call_count")
  long callCount;
}
