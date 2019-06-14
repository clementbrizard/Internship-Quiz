<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Trainee</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/modal.css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/resources/css/common.css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
          integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/v/bs4/dt-1.10.18/af-2.3.3/b-1.5.6/b-colvis-1.5.6/b-flash-1.5.6/b-print-1.5.6/cr-1.5.0/r-2.2.2/rr-1.2.4/sc-2.0.0/sl-1.3.0/datatables.min.css"/>

</head>
<%@include file="../_header_trainee.jsp" %>
<body>
<div class="container col-md-12">
    <c:if test="${loggedUserName!= null}">
        <h2 class="text-center">Welcome ${loggedUserName}</h2>
    </c:if>
    <div class="col-md-12">
        <h2 class="text-center">Available quizzes</h2>
        <table id="formList" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th class="text-center">Title</th>
                <th class="text-center">Subjects</th>
                <th class="text-center">Active</th>
                <th class="text-center">Action</th>
            </tr>
            </thead>
            <c:forEach items="${formList}" var="item">
                <tr>
                    <td class="text-center">${item.title}</td>
                    <td class="text-center">
                        <c:forEach items="${item.subjects}" var="subject">
                            ${subject.title}
                        </c:forEach>
                    </td>
                    <td class="text-center">${item.isActive}</td>
                    <td class="text-center">
                        <form id="launchQuiz/${item.id}" method="GET" action="/forms/${item.id}">
                        </form>
                        <a onclick="document.forms['launchQuiz/${item.id}'].submit()" class="btn btn-info">
                            Start quiz
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h2 class="text-center">Done quizzes</h2>
        <table id="trackList" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th class="text-center">Title</th>
                <th class="text-center">Subjects</th>
                <th class="text-center">Score</th>
            </tr>
            </thead>
            <c:forEach items="${trackList}" var="item">
                <tr>
                    <td class="text-center">${item.form.title}</td>
                    <td class="text-center">
                        <c:forEach items="${item.form.subjects}" var="subject">
                            ${subject.title}
                        </c:forEach>
                    </td>
                    <td class="text-center">${item.score}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<!-- The Modal -->
<div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">&times;</span>
            <h2>Result</h2>
        </div>
        <div class="modal-body">
            <p id="score"></p>
        </div>
        <div class="modal-footer">
            <h3>Encore un quiz ?</h3>
        </div>
    </div>

</div>

<script type="text/javascript" charset="utf8"
        src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf8"
        src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript"
        src="https://cdn.datatables.net/v/bs4/dt-1.10.18/af-2.3.3/b-1.5.6/b-colvis-1.5.6/b-flash-1.5.6/b-print-1.5.6/cr-1.5.0/r-2.2.2/rr-1.2.4/sc-2.0.0/sl-1.3.0/datatables.min.js"></script>
<script> var score =<c:out value="${score}"/></script>
<script src="${contextPath}/resources/js/trainee.js"></script>
</body>
</html>

