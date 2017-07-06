<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Avie Company</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/header.jsp" %>
       
            <c:if test="${not empty orders}">
            <c:forEach var="order" items="${orders}">
                <c:out value="${order.email}" />
            </c:forEach>
            </c:if>
       
            <a href="/Airline?command=profile">History</a>    

     <%@include file="/WEB-INF/jsp/footer.jsp" %>
    </body>
</html>
