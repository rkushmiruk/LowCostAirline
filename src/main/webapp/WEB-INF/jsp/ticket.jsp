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
                <c:forEach begin="1" end="${countTicket}" var = "i" varStatus="loop">
                <table class="table" border="1"> 
                    <tbody align="center">
                        <tr>
                            <td>
                                <label for=""><fmt:message key="firstName" bundle="${bundle}" /></label>
                                <input type="text" id="firstName" name="firstName${i}" required />
                            </td>
                            <td><label for=""><fmt:message key="lastName" bundle="${bundle}" /></label>
                                <input type="text" id="lastName" name="lastName${i}" required /></td>
                            <td><label for=""><fmt:message key="email" bundle="${bundle}" /></label>
                                <input type="text" id="email" name="email${i}" required /></td>
                        </tr>
                        <tr>
                            <td><label for=""><fmt:message key="hasBaggage" bundle="${bundle}" /></label>
                                <select name="hasBaggage${i}">
                                    <option value="false">No baggage</option>
                                    <option value="true">small Baggage</option>
                                    <option value="true">medium Baggage</option>
                                    <option value="true">Big Baggage</option>
                                </select></td>
                            <td><label for=""><fmt:message key="hasPriorityRegistration" bundle="${bundle}" />
                                    <input type="checkbox"  name="hasPriorityRegistration${i}" />
                            </td>
                            <td><label for=""><fmt:message key="seatNumber" bundle="${bundle}" /></label>
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
            <div class="input-data">
                <div class="input-block">
                    <label for="">
                        <button class="button" type="submit">
                            buy
                        </button>
                    </label>
                </div>
            </div>
        </form>
        <%@include file="/WEB-INF/jsp/footer.jsp" %>
    </body>
</html>
