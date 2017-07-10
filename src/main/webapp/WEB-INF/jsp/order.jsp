<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Avie Company</title> 
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/header.jsp" %>
        <label class="error" for=""><c:out value="${exception}"/></label>
        <table class="table" border="1"> 
            <tbody align="center">
                <tr>
                    <td><label for=""><fmt:message key="id" bundle="${bundle}" /></label>
                    </td>
                    <td><label for=""><fmt:message key="departureAirport" bundle="${bundle}" /></label>
                    </td>
                    <td><label for=""><fmt:message key="destinationAirport" bundle="${bundle}" /></label>
                    </td>
                    <td><label for=""></label>
                    </td>
                    <td><label for=""><fmt:message key="time" bundle="${bundle}" /></label>
                    </td>
                    <td><label for=""><fmt:message key="seatsNumber" bundle="${bundle}" /></label>
                    </td>
                </tr>
                <c:forEach var="ticket" items="${orderTickets}">
                <tr>
                <form class="section" action = "Airline" method="POST">
                    <div><input name="command" value="findTicket" type="hidden"/></div>
                    <div><input name="id" value = "${ticket.id}" type="hidden"/></div>
                    <td><c:out value="${ticket.id}"/></td>
                    <td><c:out value="${flight.departureAirport.name} (${flight.departureAirport.city.name})"/></td>
                    <td><c:out value="${flight.destinationAirport.name} (${flight.destinationAirport.city.name})"/><td>
                    <td><c:out value="${flight.time} ${flight.departureDateTime}"/></td>
                    <td> <input type="text" id="count" name="count" value="1" required /></td>
                    <td><button type="submit"><fmt:message key="search" bundle="${bundle}" /></td>
                    </tr>
                </form>
            </c:forEach>
        </tbody>
    </table>
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>