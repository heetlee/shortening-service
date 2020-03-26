package com.example.demo.shorten.service;

import com.example.demo.shorten.domain.QueryReq;
import com.example.demo.shorten.domain.vo.ShorteningUrl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ShorteningService {

  private static HashMap<String, String> shorteningUrlMap = new HashMap<>();
  private static HashMap<Integer, ShorteningUrl> originUrlMap = new HashMap<>();

  public Object shorteningUrl(final QueryReq request) {
    log.info("url = " + request.getUrl());

    String url = request.getUrl().substring(request.getUrl().lastIndexOf('/') + 1);
    return shorteningUrlMap.containsKey(url) ? redirectUrl(shorteningUrlMap.get(url)) : getShorteningUrl(request.getUrl());
  }

  private RedirectView redirectUrl(final String originUrl) {
    return new RedirectView(originUrl);
  }

  public ShorteningUrl getShorteningUrl(final String url){
    ShorteningUrl shorteningUrl;
    int hashCode = url.hashCode();
    if (originUrlMap.containsKey(hashCode)) {  // case 1. 중복 호출
      shorteningUrl = originUrlMap.get(hashCode);
      shorteningUrl.setCallCount(shorteningUrl.getCallCount() + 1);
    } else {  // case 2. 최초 호출
      String shortenUrl = Long.toString(System.currentTimeMillis(), 36);
      shorteningUrl = new ShorteningUrl("http://localhost:8080/" + shortenUrl, 1);
      originUrlMap.put(hashCode, shorteningUrl);
      shorteningUrlMap.put(shortenUrl, url);
    }
    return shorteningUrl;
  }
}
