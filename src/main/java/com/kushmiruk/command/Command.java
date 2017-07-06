package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    /**
     * executes some command, returns address to JSP page which will be forwarded by servlet to show results
     *
     * @param request  HTTP request from servlet
     * @param response HTTP response from servlet
     * @return address to JSP page which will be forwarded by servlet
     * @throws AppException if some error occurs while performing some logic
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws AppException;

    /**
     * @param request HTTP request from servlet
     * @param e       exception occurred in method execute
     * @return address to JSP page with message about error
     * @throws AppException if some error occurs while preparing result page
     */
    default String doOnError(HttpServletRequest request, Exception e) throws AppException {
        throw new RuntimeException(e);
    }
}
