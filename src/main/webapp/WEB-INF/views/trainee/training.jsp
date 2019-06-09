<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="loggedUserName" value="${pageContext.request.userPrincipal.name}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Training</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/resources/css/common.css"/>
</head>
<body>
<div class="container col-md-12">
    <h1 class="text-center">${form.title}</h1>
    <h2 class="text-center">${formQuestion.question.title}</h2>
    <form:form method="post" action="/forms/${formId}/questions/${formQuestion.id}">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>
</div>
<script type="text/javascript" charset="utf8"
        src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
</body>
</html>
