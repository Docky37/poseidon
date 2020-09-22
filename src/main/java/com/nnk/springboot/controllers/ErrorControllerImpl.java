package com.nnk.springboot.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorControllerImpl implements ErrorController {

    /**
     * Create a SLF4J/LOG4J LOGGER instance.
     */
    static final Logger LOGGER = LoggerFactory
            .getLogger(ErrorControllerImpl.class);

    /**
     * This ErrorControllerImpl is used to select the appropriated error page.
     *
     * @param request
     * @param response
     * @return a String, the name of the error page to display.
     */
    @RequestMapping("/error")
    public String handleError(final HttpServletRequest request,
            final HttpServletResponse response) {
        LOGGER.error("*** ERROR CONTROLLER ***");
        Object status = request
                .getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            LOGGER.error(" -> Status code = " + statusCode);
            LOGGER.error(request.getRemoteUser());
            if (statusCode == HttpStatus.SC_UNAUTHORIZED) {
                return "401";
            } else if (statusCode == HttpStatus.SC_FORBIDDEN) {
                return "403";
            } else if (statusCode == HttpStatus.SC_NOT_FOUND) {
                return "404";
            } else if (statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                return "500";
            }
        }
        return "error";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getErrorPath() {
        return null;
    }

}
