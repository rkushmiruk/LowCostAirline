<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Avie Company</title>
</head>
<body> 
<%@include file="/WEB-INF/jsp/header.jsp" %>

<label class="registration" for=""><c:out value="${successRegistration}"/></label>
<label class="registration" for=""><c:out value="Order success: ${clientAddress}"/></label>
<label class="error" for=""><c:out value="${exception}"/></label>
<form class="section" action = "Airline" method="POST">
    <div><input name="command" value="findFlights" type="hidden"/></div>
    <div class="input-container">
        <div class="input-block input-from">
            <label for=""><fmt:message key="from" bundle="${bundle}" /></label>
            <input type="text" id="cityFrom" name="cityFrom" value="${cityFrom}" required />
        </div>
        <div class="input-block input-to">
            <label for=""><fmt:message key="to" bundle="${bundle}" /></label>
            <input type="text" id="cityTo" name="cityTo" value="${cityTo}" required />
        </div>
    </div>
    <div class="input-data">
        <div class="input-block">
            <label for=""><fmt:message key="date" bundle="${bundle}" /></label>
            <input type="text" id="date" name="date" value="${date}" required />
        </div>
    </div>
    <div class="input-search">
        <div class="input-block">
            <label for="">
            <button class="button" type="submit">
            <fmt:message key="search" bundle="${bundle}" />
            </button>
            </label>
        </div>
    </div>
</form>
        
<%@include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
