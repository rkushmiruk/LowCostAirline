package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.user.User;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.service.UserService;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command take user information and redirect to edit page
 */
public class EditProfileCommand implements Command {
    private UserService userService;

    public EditProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        UserAuthentication userAuth = (UserAuthentication) request.getSession().getAttribute(Parameters.USER_AUTH);
        User user = userService.findUserByLogin(userAuth.getLogin());
        request.setAttribute(Parameters.USER, user);
        return Pages.EDIT_PAGE;
    }

}
