package com.kushmiruk.command;

import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.Messages;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.StringConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class LanguageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocale(request);
        return Pages.INDEX_PAGE;
    }

    protected void setLocale(HttpServletRequest request) {
        String lang = request.getParameter(StringConstants.LANGUAGE);
        LOGGER.info(LoggerMessage.LANGUAGE+lang);
        if (lang.equals(StringConstants.EN)) {
            request.getSession().setAttribute(StringConstants.LANGUAGE, StringConstants.EN_US);
            Messages.setLocale(Messages.ENGLISH);
        } else {
            request.getSession().setAttribute(StringConstants.LANGUAGE, StringConstants.UK_UA);
            Messages.setLocale(Messages.UKRAINIAN);
        }
    }
}
