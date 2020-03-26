package com.example.demo.shorten;

import com.example.demo.shorten.config.ServiceProperties;
import com.example.demo.shorten.domain.QueryReq;
import com.example.demo.shorten.service.ShorteningService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = ShorteningApplicationTest.InnerConfiguration.class, initializers = ConfigFileApplicationContextInitializer.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShorteningApplicationTest {

  private ShorteningService shorteningService;
  @BeforeEach
  void createAutoCompletionService() {
    shorteningService = new ShorteningService();
  }

  @Test
  @Order(0)
  @DisplayName("랜덤 url 입력 / shorteningUrl 생성  / 회신이 정상인지 체크 / 동일 요청시 카운트 체크")
  public void shorteningUrlTest() {
    String randomUrl = redomQuery();
    Assertions.assertNotNull(shorteningService.getShorteningUrl(randomUrl).getShorteningUrl(), "shorteningUrl 이 null 아님");
    Assertions.assertEquals(shorteningService.getShorteningUrl(randomUrl).getCallCount(), 2);
  }

  @Test
  @Order(1)
  @DisplayName("shorteningUrl 요청시 리다이렉트 체크")
  public void redirectTest() {
    QueryReq.SearchShortenUrl searchShortenUrl = new QueryReq.SearchShortenUrl();
    searchShortenUrl.setUrl(shorteningService.getShorteningUrl(redomQuery()).getShorteningUrl());
    Assertions.assertEquals(shorteningService.shorteningUrl(QueryReq.from(searchShortenUrl)).getClass(), RedirectView.class);
  }

  private <T> T randomFrom(final List<T> source) {
    return source.get(ThreadLocalRandom.current().nextInt(0, source.size()));
  }

  private String redomQuery(){

      return randomFrom(ImmutableList.of("https://en.wikipedia.org/wiki/URL_shortening", "https://github.com/heetlee/shortening-service", "https://www.python.org/"));
  }

  @Configuration
  @EnableConfigurationProperties({ServiceProperties.class}) static class InnerConfiguration { }

}
