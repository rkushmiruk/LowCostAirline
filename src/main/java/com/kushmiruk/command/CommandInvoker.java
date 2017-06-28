package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Retrieves command from from request and takes appropriate command from Map
 */
public class CommandInvoker {
    private Map<String, Command> actionMap = new HashMap<>();

    private CommandInvoker() {
    }

    private static class CommandInvokerHolder {
        private static final CommandInvoker instance = new CommandInvoker();
    }

    public static CommandInvoker getInstance() {
        return CommandInvokerHolder.instance;
    }

    public String invoke(HttpServletRequest request, HttpServletResponse response) {
        String actionName = request.getParameter("command");
        Command command = actionMap.get(actionName);
        if (command == null) {
            command = new DefaultCommand();
        }
        try {
            return command.execute(request, response);
        } catch (AppException e) {

        }
        return null;
    }

}
