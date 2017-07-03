<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Avie Company</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<form class="section" action = "Airline" method="POST">
    <div><input name="command" value="findFlights" type="hidden"/></div>
    <div class="input-container">
        <div class="input-block input-from">
            <label for=""><fmt:message key="from" bundle="${bundle}" /></label>
            <input type="text" id="cityFrom" name="cityFrom" required />
        </div>
        <div class="arrow"></div>
        <div class="input-block input-to">
            <label for=""><c:out value="${message}"/></label>
            <label for=""><fmt:message key="to" bundle="${bundle}" /></label>
            <input type="text" id="cityTo" name="cityTo" required />
        </div>
    </div>
    <div class="input-data">
        <div class="input-block">
            <label for=""><fmt:message key="date" bundle="${bundle}" /></label>
            <input type="text" id="date" name="date" required />
        </div>
    </div>
    <div class="input-search">
        <div class="input-block">
            <button type="submit">
            <label for=""><fmt:message key="search" bundle="${bundle}" /></label>
            </button>
        </div>
    </div>
</form>
        
<footer class="footer">
    <div class="about">Design by E&R</div>
    <div class="info">
        <label class="support">Support</label>
        <label class="email">r.kushmiruk@gmail.com</label>
        <label class="phone">+380633959425</label>
    </div>
</footer>

<div class="decoration1"></div>
<div class="decoration2"></div>
<div class="decoration3"></div>
<div class="decoration4"></div>

</body>
</html>
x