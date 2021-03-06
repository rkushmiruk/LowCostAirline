package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.user.User;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.model.entity.user.UserRole;
import com.kushmiruk.service.UserService;
import com.kushmiruk.util.Messages;
import com.kushmiruk.util.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kushmiruk.util.Parameters;
import org.apache.log4j.Logger;

/**
 * Registration for new users
 */
public class RegistrationCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        UserAuthentication userAuthentication = createUserAuthentication(request);
        saveUserFromRequest(request, userAuthentication);
        LOGGER.info(Messages.successMessage + userAuthentication.getLogin());
        request.setAttribute(Parameters.SUCCESS_REGISTRATION, Messages.successMessage + userAuthentication.getLogin());
        return Pages.SIGN_IN_PAGE;
    }

    @Override
    public String doOnError(HttpServletRequest request, Exception e) throws AppException {
        LOGGER.error(e.getMessage());
        request.setAttribute(Parameters.EXCEPTION, e.getMessage());
        return Pages.REGISTRATION_PAGE;
    }

    private UserAuthentication createUserAuthentication(HttpServletRequest request) {
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        UserAuthentication userAuthentication = new UserAuthentication(login, password);
        request.getSession().setAttribute(Parameters.USER_AUTH, userAuthentication);
        return userAuthentication;
    }

    private void saveUserFromRequest(HttpServletRequest request, UserAuthentication userAuthentication) {
        String firstName = request.getParameter(Parameters.FIRST_NAME);
        String lastName = request.getParameter(Parameters.LAST_NAME);
        String email = request.getParameter(Parameters.EMAIL);
        User user = new User.Builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .userAuthentication(userAuthentication)
                .userRole(UserRole.USER)
                .build();
        request.getSession().setAttribute(Parameters.USER, user);
        userService.save(user);
    }

}
