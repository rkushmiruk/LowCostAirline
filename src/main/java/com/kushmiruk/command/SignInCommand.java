package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.model.entity.user.UserRole;
import com.kushmiruk.service.UserAuthenticationService;
import com.kushmiruk.service.UserService;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * It checks user's credentials and lets him to sign in
 */
public class SignInCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SignInCommand.class);
    private UserAuthenticationService userAuthenticationService;
    private UserService userService;

    public SignInCommand(UserAuthenticationService userAuthenticationService, UserService userService) {
        this.userAuthenticationService = userAuthenticationService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        UserAuthentication userAuthentication = new UserAuthentication(login, password);
        request.getSession().setAttribute(Parameters.USER_AUTH, userAuthentication);
        userAuthenticationService.authentication(userAuthentication);
        LOGGER.info(userService.findUserByLogin(login));
        if (userService.findUserByLogin(login).getUserRole().toString().equals(UserRole.ADMIN.toString())) {
            request.getSession().setAttribute(Parameters.STATUS, UserRole.ADMIN.toString());
            request.getSession().setAttribute(Parameters.ADMIN_PARAM, true);
        } else{
            request.getSession().setAttribute(Parameters.STATUS, UserRole.USER.toString());
        }
        return Pages.INDEX_PAGE;
    }

    @Override
    public String doOnError(HttpServletRequest request, Exception e) throws AppException {
        LOGGER.error(e.getMessage());
        request.setAttribute(Parameters.EXCEPTION, e.getMessage());
        return Pages.SIGN_IN_PAGE;
    }

}
