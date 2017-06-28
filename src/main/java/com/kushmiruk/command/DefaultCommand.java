package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.util.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        return Pages.getPage(Pages.INDEX_PAGE);
    }
}
