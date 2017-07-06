package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.util.CommandNames;
import com.kushmiruk.util.LoggerMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Retrieves command from request and takes appropriate command from Map
 */
public class CommandInvoker {
    private static final Logger LOGGER = Logger.getLogger(CommandInvoker.class);
    private Map<String, Command> commandMap = new HashMap<>();

    private CommandInvoker() {
        commandMap.put(CommandNames.INDEX_COMMAND, new DefaultCommand());
        commandMap.put(CommandNames.LANG_COMMAND, new LanguageCommand());
        commandMap.put(CommandNames.FIND_FLIGHTS_COMMAND, new FlightListCommand());
        commandMap.put(CommandNames.REGISTRATION_COMMAND, new RegistrationCommand());
        commandMap.put(CommandNames.REDIRECT_REGISTRATION_COMMAND, new RedirectCommand(CommandNames.REGISTRATION_COMMAND));
        commandMap.put(CommandNames.SIGN_IN_COMMAND, new SignInCommand());
        commandMap.put(CommandNames.REDIRECT_SIGN_IN_COMMAND, new RedirectCommand(CommandNames.SIGN_IN_COMMAND));
        commandMap.put(CommandNames.LOGOUT_COMMAND, new LogoutCommand());
        commandMap.put(CommandNames.REDIRECT_PROFILE_COMMAND, new RedirectCommand(CommandNames.PROFILE_COMMAND));
        commandMap.put(CommandNames.PROFILE_COMMAND, new ProfileCommand());
    }

    private static class CommandInvokerHolder {
        private static final CommandInvoker instance = new CommandInvoker();
    }

    public static CommandInvoker getInstance() {
        return CommandInvokerHolder.instance;
    }

    public String invoke(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
        String commandName = request.getParameter(CommandNames.PARAMETER_COMMAND);
        LOGGER.info(CommandNames.PARAMETER_COMMAND + commandName);
        Command command = commandMap.get(commandName);
        if (command == null) {
            command = new DefaultCommand();
        }
        try {
            return command.execute(request, response);
        } catch (AppException e) {
            return command.doOnError(request, e);
        }
    }

}
