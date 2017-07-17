
package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.user.User;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.model.entity.user.UserRole;
import com.kushmiruk.service.UserService;
import com.kushmiruk.service.factory.ServiceFactory;
import com.kushmiruk.util.Messages;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class UpdateProfileCommand implements Command{
  private static final Logger LOGGER = Logger.getLogger(UpdateProfileCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.createUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        UserAuthentication userAuthentication = createUserAuthentication(request);
        updateUserFromRequest(request, userAuthentication);
        request.setAttribute(Parameters.SUCCESS_REGISTRATION, Messages.successMessage + userAuthentication.getLogin());
        return Pages.PROFILE_PAGE;
    }

    @Override
    public String doOnError(HttpServletRequest request, Exception e) throws AppException {
        LOGGER.error(e.getMessage());
        request.setAttribute(Parameters.EXCEPTION, e.getMessage());
        return Pages.EDIT_PAGE;
    }

    private UserAuthentication createUserAuthentication(HttpServletRequest request) {
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        UserAuthentication userAuthentication = new UserAuthentication(login, password);
        return userAuthentication;
    }

    private void updateUserFromRequest(HttpServletRequest request, UserAuthentication userAuthentication) {
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
        userService.update(user);
    }
}
