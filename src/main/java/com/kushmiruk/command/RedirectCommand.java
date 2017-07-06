package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.util.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Redirect to jsp
 */
public class RedirectCommand implements Command {
    private String path;

    public RedirectCommand(String path) {
        this.path = Parameters.PATH_PREFIX + path + Parameters.PATH_SUFFIX;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        return path;
    }

}
