package com.musinsa.shorten.service;

import com.musinsa.shorten.domain.BaseRes;
import com.musinsa.shorten.domain.QueryReq;
import com.musinsa.shorten.domain.vo.ShorteningUrl;
import java.util.Date;
import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ShorteningService {

  private static HashMap<String, String> shorteningUrlMap = new HashMap<>();
  private static HashMap<Integer, ShorteningUrl> originUrlMap = new HashMap<>();

  public Object shorteningUrl(final QueryReq request) {
    log.info("url = " + request.getUrl());

    String url = request.getUrl().substring(
        request.getUrl().lastIndexOf('/') > -1 ? request.getUrl().lastIndexOf('/') + 1 : 0,
        request.getUrl().length());
    return shorteningUrlMap.containsKey(url) ? redirectUrl(shorteningUrlMap.get(url)) : getShorteningUrl(request);
  }

  private RedirectView redirectUrl(final String originUrl) {

    return new RedirectView(originUrl);
  }

  private BaseRes getShorteningUrl(final QueryReq request) {

    return BaseRes.success(getShorteningUrl(request.getUrl()), request);
  }

  public ShorteningUrl getShorteningUrl(final String url){
    ShorteningUrl shorteningUrl;
    int hashCode = url.hashCode();
    if (originUrlMap.containsKey(hashCode)) {  // case 1. 중복 호출
      shorteningUrl = originUrlMap.get(hashCode);
      shorteningUrl.setCallCount(shorteningUrl.getCallCount() + 1);
    } else {  // case 2. 최초 호출
      String shortenUrl = Long.toString(new Date().getTime(), 36);
      shorteningUrl = new ShorteningUrl("http://localhost:8080/" + shortenUrl, 1);
      originUrlMap.put(hashCode, shorteningUrl);
      shorteningUrlMap.put(shortenUrl, url);
    }
    return shorteningUrl;
  }
}
