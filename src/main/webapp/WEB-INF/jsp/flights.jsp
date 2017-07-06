<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Avie Company</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/header.jsp" %>
        <table class = "table">
            <tr>
                <th>Рейс</th>
                <th>Аеропорт откуда</th> 
                <th>Аеропорт куда</th>
                <th>Время вылета</th>
            </tr>
            <c:forEach var="flight" items="${flights}">
                <tr>
                    <td><c:out value="${flight.id}"/></td>
                    <td><c:out value="${flight.departureAirport.name} (${flight.departureAirport.city.name})"/></td>
                    <td><c:out value="${flight.destinationAirport.name} (${flight.destinationAirport.city.name})"/><td>
                    <td><c:out value="${flight.departureDateTime}"/></td>
                </tr>
            </c:forEach>
        </table>

     <%@include file="/WEB-INF/jsp/footer.jsp" %>
    </body>
</html>
