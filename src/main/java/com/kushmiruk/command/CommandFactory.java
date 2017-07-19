package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.service.factory.ServiceFactory;
import com.kushmiruk.util.CommandNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Retrieves command from request and takes appropriate command from Map
 */
public class CommandFactory {
    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class);
    private Map<String, Command> commandMap = new HashMap<>();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private CommandFactory() {
        commandMap.put(CommandNames.INDEX_COMMAND, new DefaultCommand());
        commandMap.put(CommandNames.LANG_COMMAND, new LanguageCommand());
        commandMap.put(CommandNames.FIND_FLIGHTS_COMMAND, new FlightListCommand(serviceFactory.createFlightService()));
        commandMap.put(CommandNames.REGISTRATION_COMMAND, new RegistrationCommand(serviceFactory.createUserService()));
        commandMap.put(CommandNames.REDIRECT_REGISTRATION_COMMAND, new RedirectCommand(CommandNames.REGISTRATION_COMMAND));
        commandMap.put(CommandNames.SIGN_IN_COMMAND, new SignInCommand(serviceFactory.createUserAuthenticationService(),serviceFactory.createUserService()));
        commandMap.put(CommandNames.REDIRECT_SIGN_IN_COMMAND, new RedirectCommand(CommandNames.SIGN_IN_COMMAND));
        commandMap.put(CommandNames.LOGOUT_COMMAND, new LogoutCommand());
        commandMap.put(CommandNames.REDIRECT_PROFILE_COMMAND, new RedirectCommand(CommandNames.PROFILE_COMMAND));
        commandMap.put(CommandNames.REDIRECT_PROFILE_ADMIN, new RedirectCommand(CommandNames.PROFILE_ADMIN));
        commandMap.put(CommandNames.PROFILE_COMMAND, new ProfileCommand(serviceFactory.createTicketOrderService()));
        commandMap.put(CommandNames.FIND_TICKET_COMMAND, new TicketCommand(serviceFactory.createTicketService(),serviceFactory.createFlightService()));
        commandMap.put(CommandNames.ORDER_TICKET_COMMAND, new OrderTicketCommand(serviceFactory.createTicketService()));
        commandMap.put(CommandNames.BUY_TICKET_COMMAND, new BuyTicketCommand(serviceFactory.createTicketService()));
        commandMap.put(CommandNames.HISTORY_DETAIL, new HistoryDetailCommand(serviceFactory.createTicketOrderService()));
        commandMap.put(CommandNames.EDIT_PROFILE, new EditProfileCommand(serviceFactory.createUserService()));
        commandMap.put(CommandNames.UPDATE_PROFILE, new UpdateProfileCommand());
        commandMap.put(CommandNames.PROFILE_ADMIN, new ProfileAdminCommand(serviceFactory.createTicketOrderService()));

    }

    private static class CommandFactoryHolder {
        private static final CommandFactory instance = new CommandFactory();
    }

    public static CommandFactory getInstance() {
        return CommandFactoryHolder.instance;
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
