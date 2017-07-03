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
    <div class="logo">
    </div>
    <nav class="navbar">
        <ul>
            <li><a href = "/Airline?command=setLang&lang=en">EN</a> | <a href = "/Airline?command=setLang&lang=ua">UA</a></li>
            <li><a href=""><fmt:message key="sign" bundle="${bundle}" /></a></li>
            <li><a href=""><fmt:message key="registration" bundle="${bundle}" /></a></li>
        </ul>       
    </nav>
</header>
    </body>
