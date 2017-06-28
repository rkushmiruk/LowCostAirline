package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    /**
     * executes some action, returns address to JSP page which will be forwarded by servlet to show results
     *
     * @param request  HTTP request from servlet
     * @param response HTTP response from servlet
     * @return address to JSP page which will be forwarded by servlet
     * @throws AppException if some error occurs while performing some logic
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws AppException;
}
