package club.tilitili.schedule.controller;

import club.tilitili.schedule.entity.BaseModel;
import club.tilitili.schedule.exception.AssertException;
import club.tilitili.schedule.exception.UnLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(AssertException.class)
    @ResponseBody
    public BaseModel<?> handleAssertError(Exception ex) {
        return new BaseModel<>(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseModel<?> handleError(HttpServletRequest req, Exception ex) {
        log.error("Request: " + req.getRequestURL(), ex);
        return new BaseModel<>("error");
    }

    @ExceptionHandler(UnLoginException.class)
    @ResponseBody
    public ResponseEntity<String> parameterErrorHandler(HttpServletRequest req, UnLoginException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
