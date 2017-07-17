<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Avie Company</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/header.jsp" %>
        <label class="error" for=""><c:out value="${exception}"/></label>
        <form class="section" action = "Airline" method="POST">
            <div><input name="command" value="orderTicket" type="hidden"/></div>
            <div><input name="countTicket" value="${countTicket}" type="hidden"/></div>
            <div><input name="currentFlight" value="${currentFlight}" type =hidden /><div>
                    <c:forEach begin="1" end="${countTicket}" var = "i" varStatus="loop">
                        <table align="center"> 
                            <tbody>
                                <tr>
                                    <th>
                                        <label for=""><fmt:message key="firstName" bundle="${bundle}" />
                                            <input type="text" id="firstName" name="firstName${i}" required />
                                    </th>
                                    <th><label for=""><fmt:message key="lastName" bundle="${bundle}" />
                                            <input type="text" id="lastName" name="lastName${i}" required />
                                    </th>
                                    <th><label for=""><fmt:message key="email" bundle="${bundle}" />
                                            <input type="text" id="email" name="email${i}" required />
                                    </th>
                                </tr>
                                <tr>
                                    <td><label for=""><fmt:message key="hasBaggage" bundle="${bundle}" />
                                            <select name="hasBaggage${i}">
                                                <option value="1">No baggage</option>
                                                <option value="2">Small Baggage</option>
                                                <option value="3">Medium Baggage</option>
                                                <option value="4">Big Baggage</option>
                                            </select></td>
                                    <td><label for=""><fmt:message key="hasPriorityRegistration" bundle="${bundle}" />
                                            <input type="checkbox"  name="hasPriorityRegistration${i}" />
                                    </td>
                                    <td><label for=""><fmt:message key="seatNumber" bundle="${bundle}" />
                                            <select name="seatNumber${i}" >  
                                                <c:forEach var="ticket" items="${freeTickets}">
                                                    <option>
                                                        <c:out value="${ticket}" />
                                                    </option>
                                                </c:forEach>
                                            </select></td>
                                </tr>
                            </tbody>
                        </table>
                    </c:forEach>
                    <div class="input-search">
                        <div class="input-block">
                            <label for="">
                                <button class="button" type="submit">
                                    <fmt:message key="order" bundle="${bundle}" />
                                </button>
                            </label>
                        </div>
                    </div>
                    </form>
                    <%@include file="/WEB-INF/jsp/footer.jsp" %>
                    </body>
                    </html>
