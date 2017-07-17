<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Avie Company</title> 
        <script>
            history.back(-1);
            history.forward();
        </script>
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/header.jsp" %>
        <label class="error" for=""><c:out value="${exception}"/></label>
        <table align="center"> 
            <tbody>
                <tr>
                    <th><label for=""><fmt:message key="departureAirport" bundle="${bundle}" /></label>
                    </th>
                    <th><label for=""><fmt:message key="destinationAirport" bundle="${bundle}" /></label>
                    </th>
                    <th><label for=""><fmt:message key="time" bundle="${bundle}" /></label>
                    </th>
                    <th><label for=""><fmt:message key="price" bundle="${bundle}" /></label>
                    </th>
                </tr>
                <c:forEach var="ticket" items="${orderTickets}">
                    <tr>
                        <td><c:out value="${currentFlight.departureAirport.name} (${currentFlight.departureAirport.city.name})"/></td>
                        <td><c:out value="${currentFlight.destinationAirport.name} (${currentFlight.destinationAirport.city.name})"/></td>
                        <td><c:out value="${currentFlight.time} ${currentFlight.departureDateTime}"/></td>
                        <td><c:out value="${ticket.price}$" /></td>
                    </tr>
                </c:forEach> 
            <form class="section" action = "Airline" method="POST">
                <th><fmt:message key="address" bundle="${bundle}" /></th>
                <td><input type="text" id="address" name="address"  required /></td>
                <th>Total Price:</th>
                <td><label><c:out value="${totalPrice}" /></label></td>
        </tbody>
    </table>
        <div><input name="command" value="buyTicket" type="hidden"/></div>
        <div class="input-search">
            <div class="input-block">
                <label for="">
                    <button class="button" type="submit">
                        <fmt:message key="buy" bundle="${bundle}" />
                    </button>
                </label>
            </div>
        </div>
    </form>

    <%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>