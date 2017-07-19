package com.kushmiruk.test.command;

import com.kushmiruk.command.SignInCommand;
import com.kushmiruk.model.entity.user.User;
import com.kushmiruk.model.entity.user.UserRole;
import com.kushmiruk.service.UserAuthenticationService;
import com.kushmiruk.service.UserService;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SignInCommandTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession httpSession;
    private SignInCommand signInCommand;
    private UserAuthenticationService userAuthenticationService;
    private UserService userService;
    private User user;


    private void doInitialization() {
        userService = mock(UserService.class);
        userAuthenticationService = mock(UserAuthenticationService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        httpSession = mock(HttpSession.class);
        user = mock(User.class);
        signInCommand = new SignInCommand(userAuthenticationService, userService);
        
    }
    
    @Test
    public void testExecuteCommand() throws SQLException {
        doInitialization();
        when(userService.findUserByLogin(anyString())).thenReturn(user);
        when(user.getUserRole()).thenReturn(UserRole.USER);
        when(request.getParameter(Parameters.LOGIN)).thenReturn("Petr");
        when(request.getParameter(Parameters.PASSWORD)).thenReturn("555");
        when(request.getSession()).thenReturn(httpSession);
        String path = signInCommand.execute(request, response);
       
        assertNotNull(path);
        assertEquals(path, Pages.INDEX_PAGE);
        verify(request, atLeast(1)).getParameter(Parameters.LOGIN);
        verify(request, atLeast(1)).getParameter(Parameters.PASSWORD);
              
    
    }
}
