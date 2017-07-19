package com.kushmiruk.filter;

import com.kushmiruk.model.entity.user.UserRole;
import com.kushmiruk.util.CommandNames;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Filter for signed in admin. Can't visit not allowed pages
 */
public class AdminFilter implements Filter {

    private List<String> notAllowedActions = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        notAllowedActions.add(CommandNames.SIGN_IN_COMMAND);
        notAllowedActions.add(CommandNames.REDIRECT_SIGN_IN_COMMAND);
        notAllowedActions.add(CommandNames.REGISTRATION_COMMAND);
        notAllowedActions.add(CommandNames.REDIRECT_REGISTRATION_COMMAND);
        notAllowedActions.add(CommandNames.PROFILE_COMMAND);
        notAllowedActions.add(CommandNames.REDIRECT_PROFILE_COMMAND);
        notAllowedActions.add(CommandNames.UPDATE_PROFILE);
    }

  @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        String status = null;
        if (session != null) {
            status = (String) session.getAttribute(Parameters.STATUS);
        }
        boolean notAllowedRequest = isNotAllowedRequest(request);

        if (notAllowedRequest && ((status != null && status.equals(UserRole.ADMIN.toString())))) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(Pages.INDEX_PAGE);
            dispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isNotAllowedRequest(ServletRequest request) {
        String command = request.getParameter(CommandNames.PARAMETER_COMMAND);
        return command != null && notAllowedActions.contains(command);
    }

    @Override
    public void destroy() {

    }
}
