<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <link href='<c:url value="../resources/css/jquery-ui.min.css"/>' rel="stylesheet" type="text/css">
    <link href='<c:url value="../resources/css/style.css"/>' rel="stylesheet" type="text/css">
    </head>
    <body>
        
        <%@include file="/WEB-INF/jsp/i18n.jsp" %>
        <header class="header">
            <a href="Airline?command=index"><div class="logo"></div></a>
    <nav class="navbar">
        <ul>
            <li> <form action="Airline" method="POST">
                <div><input name="command" value="setLang" type="hidden"</div>
                <div><input name="page" value="${page}" type="hidden"</div>
                <input type="radio" id="lang" name="lang" value="en" checked="checked"/>EN
                <input type="radio" id="lang" name="lang" value="ua" />UA
                <button class="change-button" type="submit">
                     <fmt:message key="changeLang" bundle="${bundle}" />
            </button>
            </form>
            </li>
            <c:if test="${empty status}">
            <li><a href="/Airline?command=redirectSignIn"><fmt:message key="sign" bundle="${bundle}" /></a></li>
            <li><a href="/Airline?command=redirectRegistration"><fmt:message key="registration" bundle="${bundle}" /></a></li>
            </c:if>
            <c:if test="${not empty status}">
            <li><a href="/Airline?command=redirectProfile"><fmt:message key="profile" bundle="${bundle}" /></a></li>
            <li><a href="/Airline?command=logout"><fmt:message key="logout" bundle="${bundle}" /></a></li>
            </c:if>
        </form>
       
        
</header>
    </body>
