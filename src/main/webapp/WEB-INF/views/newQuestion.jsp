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

    <title>New Question</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/common.css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
          integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/v/bs4/dt-1.10.18/af-2.3.3/b-1.5.6/b-colvis-1.5.6/b-flash-1.5.6/b-print-1.5.6/cr-1.5.0/r-2.2.2/rr-1.2.4/sc-2.0.0/sl-1.3.0/datatables.min.css"/>

</head>
<%@include file="_header_admin.jsp" %>
<body>
<div class="container col-md-12">
    <h1 class="text-center">User : ${loggedUserName}</h1>
    <h1 class="text-center">Form : ${form_name}</h1>
    <c:if test="${nbQuestions>0}">
        <h2 class="text-center">Current ${nbQuestions} question</h2>
    </c:if>
    <c:if test="${nbQuestions==0}">
        <h2 class="text-center">There is no questions in your form currently</h2>
    </c:if>
    <div class="col-md-12">
        <table id="questions" class="table table-striped table-bordered">
            <thead>
            <th class="text-center">Id</th>
            <th class="text-center">Position</th>
            <th class="text-center">Title</th>
            <th class="text-center">Active</th>
            <th class="text-center">Number of answers</th>
            <th class="text-center">Answers</th>
            <th class="text-center">Subjects</th>
            <th class="text-center">Action</th>

            </tr>
            </thead>
            <c:forEach items="${currentForm.formQuestion}" var="item">
                <tr>
                    <td class="text-center">${item.question.id}</td>
                    <td class="text-center">${item.position}</td>
                    <td class="text-center">${item.question.title}</td>
                    <td class="text-center">${item.question.isActive}</td>
                    <td class="text-center">${item.question.answerQuestion.size()}</td>
                    <td class="text-center">
                        <c:forEach items="${item.question.answerQuestion}" var="question">
                        <a class="text-center ${question.isValid ? 'is-valid' : 'is-false'}">
                                ${question.answer.title}
                            </c:forEach>
                        </a>
                    <td class="text-center">
                        <c:forEach items="${item.question.subjects}" var="subject">
                            ${subject.title}
                        </c:forEach>
                    </td>
                    <td class="text-center">
                        <form id="deleteForm/${item.id}" method="POST"
                              action="${contextPath}/questions/delete/${item.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                        <form id="editForm/${item.id}" method="POST"
                              action="${contextPath}/questions/editPosition/${item.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                        <form id="editQuestionForm/${item.id}" method="POST"
                              action="${contextPath}/questions/new/answers/${item.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                        <a onclick="document.forms['editForm/${item.id}'].submit()" class="btn btn-warning"><i class="far fa-edit"></i> Edit
                        </a>

                        <a onclick="document.forms['editQuestionForm/${item.id}'].submit()" class="btn btn-success">
                            <i class="fas fa-check-square"></i> Edit answer list</a>

                        <a onclick="document.forms['deleteForm/${item.id}'].submit()" class="btn btn-danger">
                            <i class="far fa-trash-alt"></i> Delete</a>
                            <%--                        <c:choose>
                                                        <c:when test="${item.question.isActive==true}">
                                                            <a onclick="document.forms['disableForm'].submit()" class="btn btn-info">
                                                                <i class="fas fa-times-circle"></i> Disable</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a onclick="document.forms['disableForm'].submit()" class="btn btn-info">
                                                                <i class="fas fa-check-circle"></i> Enable</a>
                                                        </c:otherwise>
                                                    </c:choose>--%>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="col-md-12 text-center">


        <h2>Add a new question</h2>
        <form:form method="POST" modelAttribute="formQuestionAttribute" class="form-signin formSubmit">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select path="question" multiple="false" class="form-control">
                    <form:options items="${questionList}" itemValue="id" itemLabel="title"/>
                </form:select>
            </div>
            <h2>Choose a position</h2>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="position" class="form-control" placeholder="Position"
                            autofocus="true"></form:input>
                <form:errors path="position"></form:errors>
            </div>

            <button class="btn btn-lg btn-primary" id="submit" type="submit">Submit</button>
        </form:form>

    </div>

</div>
<script type="text/javascript" charset="utf8"
        src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf8"
        src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript"
        src="https://cdn.datatables.net/v/bs4/dt-1.10.18/af-2.3.3/b-1.5.6/b-colvis-1.5.6/b-flash-1.5.6/b-print-1.5.6/cr-1.5.0/r-2.2.2/rr-1.2.4/sc-2.0.0/sl-1.3.0/datatables.min.js"></script>
<script src="${contextPath}/resources/js/question.js"></script>
<script src="${contextPath}/resources/js/formLoop.js"></script>
</body>
</html>