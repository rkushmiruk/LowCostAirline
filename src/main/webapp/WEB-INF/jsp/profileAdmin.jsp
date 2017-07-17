<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Avie Company</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/header.jsp" %>
        <nav class="navbar" id="new">
            <ul>
                <li><a href="/Airline?command=profileAdmin">All orders</a></li>  
            </ul>
        </nav>
        <label class="error" for=""><c:out value="${exception}"/></label>
        <c:if test="${not empty orders}">
            <table align="center">
                <thead>
                    <tr>
                        <th><fmt:message key="id" bundle="${bundle}" /></th>
                        <th><fmt:message key="date" bundle="${bundle}" /></th>
                        <th><fmt:message key="paymentMethod" bundle="${bundle}" /></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                    <form class="section" action = "Airline" method="POST">
                        <input name="command" value="historyDetail" type="hidden"/>
                        <input name="orderId" value = "${order.id}" type="hidden"/>
                        <td><c:out value="${order.id}"/></td>
                        <td><c:out value="${order.dateTime}"/></td>
                        <td><c:out value="${order.paymentMethod} "/></td>
                        <td><button type="submit"><fmt:message key="detail" bundle="${bundle}" /></td>
                        </tr>
                    </form>
                </c:forEach>
            </c:if>
            <tr>
                <c:forEach begin="1" end="${numberOfPages}" var="i">
                    <td><a href="/Airline?command=profileAdmin&page=${i}">${i}</a></td>
                    </c:forEach>
            </tr>
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
