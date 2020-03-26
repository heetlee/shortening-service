package com.example.demo.shorten.common;

import com.example.demo.shorten.domain.BaseRes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
@ResponseStatus(HttpStatus.OK)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BaseExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public BaseRes resolveBadRequest(Exception e, WebRequest wr) {
    return resolveException(e, wr, BaseResCode.BAD_REQUEST);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public BaseRes resolveResourceNotFound(Exception e, WebRequest wr) {
    return resolveException(e, wr, BaseResCode.NOT_FOUND);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public BaseRes resolveMethodNotAllowed(Exception e, WebRequest wr) {
    return resolveException(e, wr, BaseResCode.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(BaseException.class)
  public BaseRes resolveDomainException(Exception e, WebRequest wr) {
    return resolveException(e, wr, BaseResCode.STS_EXCEPTION);
  }

  @ExceptionHandler(Exception.class)
  public BaseRes resolveRemainderException(Exception e, WebRequest wr) {
    return resolveException(e, wr, BaseResCode.GENERAL_OTHER_EXCEPTION);
  }

  private BaseRes resolveException(Exception e, WebRequest wr, BaseResCode code) {

    String message = e.getMessage();

    if (BaseResCode.BAD_REQUEST == code) {
      MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException)e;
      message = methodArgumentNotValidException.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    } else if (e instanceof BaseException) {
      log.info("Sts Exception -> {}", message);
    } else {
      log.warn("", e);
    }

    BaseRes res = BaseRes.from(code, message);

    return res;
  }
}
