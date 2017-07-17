<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Avie Company</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/header.jsp" %>
        <label class="error" for=""><c:out value="${exception}"/></label>
        <c:if test="${not empty orderTickets}">
            <table align="center">
                <thead>
                    <tr>
                        <th><fmt:message key="id" bundle="${bundle}" /></th>
                        <th><fmt:message key="firstName" bundle="${bundle}" /></th>
                        <th><fmt:message key="lastName" bundle="${bundle}" /></th>
                        <th><fmt:message key="price" bundle="${bundle}" /></th>
                        <th><fmt:message key="seatNumber" bundle="${bundle}" /></th>
                        <th><fmt:message key="hasPriorityRegistration" bundle="${bundle}" /></th>
                        <th><fmt:message key="hasBaggage" bundle="${bundle}" /></th>
                        <th><fmt:message key="departureAirport" bundle="${bundle}" /></th>
                        <th><fmt:message key="destinationAirport" bundle="${bundle}" /></th>
                        <th><fmt:message key="time" bundle="${bundle}" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ticket" items="${orderTickets}">
                        <tr>
                    <form class="section" action = "Airline" method="POST">
                        <td><c:out value="${ticket.id}"/></td>
                        <td><c:out value="${ticket.passengerFirstName}"/></td>
                        <td><c:out value="${ticket.passengerLastName} "/></td>
                        <td><c:out value="${ticket.price}"/></td>
                        <td><c:out value="${ticket.seatNumber}"/></td>
                        <td><c:out value="${ticket.hasPriorityRegistration}"/></td>
                        <c:if test="${not empty baggege}">
                            <td><c:out value="${ticket.baggage}"/></td>
                        </c:if>
                        <c:if test="${empty baggege}">
                            <td><c:out value="No baggage"/></td>
                        </c:if>
                        <td><c:out value="${ticket.flight.departureAirport.name} (${ticket.flight.departureAirport.city.name})"/></td>
                        <td><c:out value="${ticket.flight.destinationAirport.name} (${ticket.flight.destinationAirport.city.name})"/></td>
                        <td><c:out value="${ticket.flight.time} ${ticket.flight.departureDateTime}"/></td>
                        </tr>
                    </form>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
        

    <div class="input-search">
        <div class="input-block">
            <label for="">
                <input type ="button" class="button" name ="Back->" value ="Back->" onClick ="history.back()">
            </label>
        </div>
    </div>

    <%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
