<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="loggedUserName" value="${pageContext.request.userPrincipal.name}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Add Question</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/common.css"/>


</head>
<%@include file="_header_admin.jsp" %>
<body>
<div class="container col-md-12 text-center">

    <h1 class="text-center">Add Question</h1>
    <h2 class="text-center">Title</h2>
    <form:form method="POST" modelAttribute="question" class="form-signin">
        <spring:bind path="title">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="title" class="form-control" placeholder="Title"
                            autofocus="true"></form:input>
                <form:errors path="title"></form:errors>
            </div>
        </spring:bind>
        <h2 class="text-center">Subjects</h2>
        <spring:bind path="subjects">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select path="subjects" multiple="true" class="form-control">
                    <form:options items="${subjectList}" itemValue="id" itemLabel="title"/>
                </form:select>
            </div>
        </spring:bind>
        <h2 class="text-center">Active</h2>
        <spring:bind path="isActive">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:checkbox path="isActive"></form:checkbox>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>
</div>
</body>
</html>
