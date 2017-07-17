<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Avie Company</title> 
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/header.jsp" %>
        <label class="error" for=""><c:out value="${exception}"/></label>
        <table align="center">
            <thead>
                <tr>
                    <th><fmt:message key="id" bundle="${bundle}" /></th>
                    <th><fmt:message key="departureAirport" bundle="${bundle}" /></th>
                    <th><fmt:message key="destinationAirport" bundle="${bundle}" /></th>
                    <th><fmt:message key="time" bundle="${bundle}" /></th>
                    <th><fmt:message key="seatsNumber" bundle="${bundle}" /></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="flight" items="${flights}">
                    <tr>
                <form class="section" action = "Airline" method="POST">
                    <input name="command" value="findTicket" type="hidden"/>
                    <input name="id" value = "${flight.id}" type="hidden"/>
                    <td><c:out value="${flight.id}"/></td>
                    <td><c:out value="${flight.departureAirport.name} (${flight.departureAirport.city.name})"/></td>
                    <td><c:out value="${flight.destinationAirport.name} (${flight.destinationAirport.city.name})"/></td>
                    <td><c:out value="${flight.time} ${flight.departureDateTime}"/></td>
                    <td> <input type="text" id="count" name="count" value="1" required /></td>
                    <td><button type="submit"><fmt:message key="select" bundle="${bundle}" /></td>
                    </tr>
                </form>
            </c:forEach>
        </tbody>
    </table>
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
