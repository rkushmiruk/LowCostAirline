package com.kushmiruk.filter;

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
 * Filter for unauthorized user. Can't visit not allowed pages
 */
public class SignInFilter implements Filter {
    private List<String> notAllowedActions = new ArrayList<>();

    @Override
    public void init(FilterConfig fc) throws ServletException {
        notAllowedActions.add(CommandNames.PROFILE_COMMAND);
        notAllowedActions.add(CommandNames.REDIRECT_PROFILE_COMMAND);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        boolean notAllowedRequest = isNotAllowedRequest(request);
        if (notAllowedRequest && (session == null || session.getAttribute(Parameters.STATUS) == null)) {
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
