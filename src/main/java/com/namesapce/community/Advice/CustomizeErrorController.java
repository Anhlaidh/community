package com.namesapce.community.Advice;

import com.namesapce.community.Exception.CustomizeErrorCode;
import com.namesapce.community.Exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020/3/8 0008 21:59
 */
@ControllerAdvice
public class CustomizeErrorController {
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, Throwable ex,Model model) {
        HttpStatus status = getStatus(request);
        if (ex instanceof CustomizeException){
            model.addAttribute("message", ex.getMessage());
        }
        else model.addAttribute("message","太难了，我实在是太难了");
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
