package com.kushmiruk.test.command;

import com.kushmiruk.command.HistoryDetailCommand;
import com.kushmiruk.service.TicketOrderService;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HistoryCommandTest {
    private TicketOrderService ticketOrderService;
    private HistoryDetailCommand historyDetailCommand;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession httpSession;

    private void doInitialization() {
        ticketOrderService = mock(TicketOrderService.class);
        historyDetailCommand = new HistoryDetailCommand(ticketOrderService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        httpSession = mock(HttpSession.class);
    }

    @Test
    public void testExecuteCommand() throws SQLException {
        doInitialization();
        when(request.getParameter(Parameters.ORDER_ID)).thenReturn("1");
        when(request.getSession()).thenReturn(httpSession);
        String path = historyDetailCommand.execute(request, response);
        assertNotNull(path);
        assertEquals(path, Pages.HISTORY_PAGE);
        verify(request, atLeast(1)).getParameter(Parameters.ORDER_ID);
    }
}
