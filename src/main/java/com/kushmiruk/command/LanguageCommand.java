package com.kushmiruk.command;

import com.kushmiruk.util.ExceptionMessage;
import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.Messages;
import com.kushmiruk.util.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Command changes current locale to chosen language
 */
public class LanguageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocale(request);
        return request.getParameter(Parameters.PAGE);
    }

    private void setLocale(HttpServletRequest request) {
        String lang = request.getParameter(Parameters.LANGUAGE);
        LOGGER.info(LoggerMessage.LANGUAGE + lang);
        if (lang.equals(Parameters.EN)) {
            request.getSession().setAttribute(Parameters.LANGUAGE, Parameters.EN_US);
            Messages.setLocale(Messages.ENGLISH);
            ExceptionMessage.setLocale(ExceptionMessage.ENGLISH);
        } else {
            request.getSession().setAttribute(Parameters.LANGUAGE, Parameters.UK_UA);
            Messages.setLocale(Messages.UKRAINIAN);
            ExceptionMessage.setLocale(ExceptionMessage.UKRAINIAN);
        }
    }
}
