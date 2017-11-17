<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link href="<c:url value="/resources/home.css"/>" rel="stylesheet" type="text/css" media='all'>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>

<body>

<form:form method="POST" commandName="user" action="check-user" class="box login">

    <fieldset class="boxBody">

        <span style="float: right">
            <a href="?lang=en_US">en</a>
            <a href="?lang=ru_RU">ru</a>
        </span>

        <form:label path="name"><spring:message code="label.user"/>: </form:label>
        <form:input path="name" />
        <form:errors path="name" cssClass="error"/>

        <form:label path="password"><spring:message code="label.password"/>:</form:label>
        <form:password path="password"/>
        <form:errors path="password" cssClass="error"/>

    </fieldset>

    <footer>
        <form:checkbox path="admin"/>
        <form:label path="admin">Admin</form:label>
        <%--<label><input type="checkbox" tabindex="3">Admin</label>--%>
        <input type="submit" class="btnLogin" value="Login">
    </footer>

</form:form>


</body>
</html>