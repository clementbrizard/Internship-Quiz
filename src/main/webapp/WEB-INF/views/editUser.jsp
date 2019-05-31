<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: clement
  Date: 27/05/19
  Time: 00:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<%@include file="_header_admin.jsp" %>
<body>

<div class="container">

    <form:form method="POST" modelAttribute="user" class="form-signin" action="${contextPath}/edit/${user.id}">
        <h2 class="form-signin-heading">Edit user</h2>

        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control" placeholder="${user.username}"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="mail">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="mail" class="form-control"
                            placeholder="${user.mail}"></form:input>
                <form:errors path="mail"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="firstName" class="form-control margin"
                            placeholder="${user.firstName}"></form:input>
                <form:errors path="firstName"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="secondName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="secondName" class="form-control margin"
                            placeholder="${user.secondName}"></form:input>
                <form:errors path="secondName"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="company">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="company" class="form-control margin"
                            placeholder="${user.company}"></form:input>
                <form:errors path="company"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="phone">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="phone" class="form-control margin"
                            placeholder="${user.phone}"></form:input>
                <form:errors path="phone"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
<!-- /container -->

</body>
</html>
