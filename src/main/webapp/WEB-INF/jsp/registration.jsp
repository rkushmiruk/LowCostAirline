<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Avie Company</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<label class="error" for=""><c:out value="${exception}"/></label>
<form class="section" action = "Airline" method="POST">
    <div><input name="command" value="registration" type="hidden"/></div>
     <div class="input-container">
        <div class="input-block input-from">
            <label for=""><fmt:message key="firstName" bundle="${bundle}" /></label>
            <input type="text" id="firstName" name="firstName" value="${user.firstName}" required />
        </div>
        <div class="input-block input-to">
            <label for=""><fmt:message key="lastName" bundle="${bundle}" /></label>
            <input type="text" id="lastName" name="lastName" value="${user.lastName}" required />
        </div>
    </div>
    <div class="input-data">
        <div class="input-block">
            <label for=""><fmt:message key="email" bundle="${bundle}" /></label>
            <input type="text" id="email" name="email" value="${user.email}" required />
        </div>
    </div>
    <div class="input-data">
        <div class="input-block">
            <label for=""><fmt:message key="login" bundle="${bundle}" /></label>
            <input type="text" id="login" name="login" value="${userAuth.login}" required />
        </div>
    </div>
    <div class="input-data">
        <div class="input-block">
            <label for=""><fmt:message key="password" bundle="${bundle}" /></label>
            <input type="password" id="password" name="password" required />
        </div>
    </div>
    <div class="input-search">
        <div class="input-block">
            <label for="">
            <button class="button" type="submit">
            <fmt:message key="registration" bundle="${bundle}" />
            </button>
            </label>
        </div>
    </div>
    
</form>
    
<%@include file="/WEB-INF/jsp/footer.jsp" %>

</body>
</html>
