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
                    <td><label for=""><fmt:message key="price" bundle="${bundle}" /></label>
                    </td>
                </tr>
                <c:forEach var="ticket" items="${orderTickets}">
                <tr>
                <form class="section" action = "Airline" method="POST">
                    <div><input name="command" value="findTicket" type="hidden"/></div>
                    <div><input name="id" value = "${ticket.id}" type="hidden"/></div>
                    <td><c:out value="${ticket.id}"/></td>
                    <td><c:out value="${currentFlight.departureAirport.name} (${currentFlight.departureAirport.city.name})"/></td>
                    <td><c:out value="${currentFlight.destinationAirport.name} (${currentFlight.destinationAirport.city.name})"/><td>
                    <td><c:out value="${currentFlight.time} ${currentFlight.departureDateTime}"/></td>
                    <td><c:out value="${ticket.price}$" /></td>
                    <td><button type="submit"><fmt:message key="search" bundle="${bundle}" /></td>
                    </tr>
                </form>
            </c:forEach>
        </tbody>
    </table>
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>