package com.musinsa.shorten.controller;

import com.musinsa.shorten.domain.QueryReq;
import com.musinsa.shorten.service.ShorteningService;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShorteningController {

  private ShorteningService shorteningService;

  @PostMapping("/v1/shortenurl")
  public Object shorteningUrl(
      @RequestBody @Valid QueryReq.SearchShortenUrl request ) {

    return shorteningService.shorteningUrl(QueryReq.from(request));
  }

  @GetMapping("/healthcheck/_check")
  public String healthcheck() {
    return "ok";
  }

}
