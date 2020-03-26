package com.example.demo.shorten.controller;

import com.example.demo.shorten.domain.BaseRes;
import com.example.demo.shorten.domain.QueryReq;
import com.example.demo.shorten.service.ShorteningService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShorteningRestController {

  private ShorteningService shorteningService;

  @PostMapping("/v1/shortenurl")
  public BaseRes shorteningUrl(
      @RequestBody @Valid QueryReq.SearchShortenUrl request ) {

    return BaseRes.success(shorteningService.shorteningUrl(QueryReq.from(request)), request);
  }

  @GetMapping("/healthcheck/_check")
  public String healthcheck() {
    return "ok";
  }

}
