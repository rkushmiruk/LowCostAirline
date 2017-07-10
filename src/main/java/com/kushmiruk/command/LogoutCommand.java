package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User logout and redirect to index page
 */
public class LogoutCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOGGER.info(LoggerMessage.USER_LOGOUT + request.getSession().getAttribute(Parameters.USER_AUTH));

        request.getSession().invalidate();
        return Pages.INDEX_PAGE;
    }

}
