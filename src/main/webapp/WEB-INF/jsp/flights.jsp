<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Avie Company</title>
        <style>
            .table{
                color: #0086f6;
                position: absolute;
                left : 300px;

            }
        </style>
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/header.jsp" %>

        <TABLE class = "table">
            <TR>
                <TH>Рейс</TH>
                <TH>Аеропорт откуда</TH> 
                <TH>Аеропорт куда</TH>
                <th>Время вылета</th>
            </TR>
            <c:forEach var="flight" items="${flights}">
                <TR>
                    <TD><c:out value="${flight.id}"/></TD>
                    <TD><c:out value="${flight.departureAirport.name} (${flight.departureAirport.city.name})"/></TD>
                    <TD><c:out value="${flight.destinationAirport.name} (${flight.destinationAirport.city.name})"/><TD>
                    <td><c:out value="${flight.departureDateTime}"/></td>
                </TR>
            </c:forEach>
        </TABLE>

        <footer class="footer">
            <div class="about">Design by E&R</div>
            <div class="info">
                <label class="support">Support</label>
                <label class="email">r.kushmiruk@gmail.com</label>
                <label class="phone">+380633959425</label>
            </div>
        </footer>

        <div class="decoration1"></div>
    </body>
</html>
