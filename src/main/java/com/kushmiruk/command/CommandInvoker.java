package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import com.kushmiruk.util.StringConstants;
import org.apache.log4j.Logger;

/**
 * Retrieves command from from request and takes appropriate command from Map
 */
public class CommandInvoker {
    private static final Logger LOGGER = Logger.getLogger(CommandInvoker.class);
    private static final String PARAMETER_COMMAND = "command";
    private Map<String, Command> commandMap = new HashMap<>();

    private CommandInvoker() {
        commandMap.put("index", new DefaultCommand());
        commandMap.put("setLang", new LanguageCommand());
        commandMap.put("findFlights", new FlightListCommand());
    }

    private static class CommandInvokerHolder {
        private static final CommandInvoker instance = new CommandInvoker();
    }

    public static CommandInvoker getInstance() {
        return CommandInvokerHolder.instance;
    }

    public String invoke(HttpServletRequest request, HttpServletResponse response) {
        String commandName = request.getParameter(PARAMETER_COMMAND);
        LOGGER.info(LoggerMessage.COMMAND + commandName);
        Command command = commandMap.get(commandName);
        if (command == null) {
            command = new DefaultCommand();
        }
        try {
            return command.execute(request, response);
        } catch (AppException e) {
            request.setAttribute(StringConstants.FLIGHTS, e.getMessage());
        }
        return null;
    }

}
