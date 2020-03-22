package com.musinsa.shorten.common;

import com.musinsa.shorten.domain.BaseRes;

import javax.validation.ConstraintViolationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.WebUtils;

@Slf4j
@RestControllerAdvice
@ResponseStatus(HttpStatus.OK)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BaseExceptionHandler {

  @ExceptionHandler({BindException.class, ConstraintViolationException.class,
      HttpMessageNotReadableException.class,
      MethodArgumentNotValidException.class,
      MissingServletRequestParameterException.class,
      MissingServletRequestPartException.class,
      ServletRequestBindingException.class, TypeMismatchException.class})
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

  @ExceptionHandler({HttpMessageNotWritableException.class, IllegalArgumentException.class})
  public BaseRes resolveInternalServerError(Exception e, WebRequest wr) {
    return resolveException(e, wr, BaseResCode.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HttpMessageConversionException.class)
  public BaseRes resolveConversionInternalServerError(Exception e, WebRequest wr) {
    return resolveException(e, wr, BaseResCode.INTERNAL_SERVER_ERROR);
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
      log.debug("{}", message);
    } else if (e instanceof BaseException) {
      log.info("Sts Exception -> {}", message);
      if (e.getCause() != null) {
        log.warn("", e.getCause());
      }
    } else {
      log.warn("", e);
    }

    BaseRes res = BaseRes.from(code, message);

    if (BaseResCode.oneOfServerSideError(code)) {
      wr.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, WebRequest.SCOPE_REQUEST);
      res = BaseRes.from(code, message);
    }

    return res;
  }
}
