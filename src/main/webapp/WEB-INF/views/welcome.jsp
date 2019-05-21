<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

            <h2 class="text-center">Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
            </h2>
        <div class="container">
            <h2 class="text-center">All User</h2>
            <div class="row col-md-12 ">
                <div style="margin-bottom:20px; padding:10px; background-color:#336699; color:white;">
                    <p>Type some text to search the table </p>
                    <input class="form-control" id="inputFilter" type="text" placeholder="Search.." />
                </div>
                <table id="userTable" class="table table-bordered table-hover table-responsive">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>username</th>
                        <th>firstName</th>
                        <th>secondName</th>
                        <th>mail</th>
                        <th>admin</th>
                        <th>company</th>
                        <th>phone</th>
                        <th>creationDate</th>
                        <th>valid</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <c:forEach items="${userList}" var="item">
            <li>${item.id}</li>
            <li>${item.username}</li>
            <li>${item.firstName}</li>
            <li>${item.secondName}</li>
            <li>${item.mail}</li>
            <li>${item.admin}</li>
            <li>${item.company}</li>
            <li>${item.phone}</li>
            <li>${item.creationDate}</li>
            <li>${item.valid}</li>
        </c:forEach>
            <h2>$$ ${currentUser.admin} $$</h2>
            <c:if test="${currentUser.admin==true && currentUser.valid==true}">
                <h2> Your an ADMIN and your account is still valid, so you can see this</h2>
                <c:forEach items="${userList}" var="item">
                    <li>${item.username}</li>
                </c:forEach>
            </c:if>
            <c:if test="${currentUser.admin==false && currentUser.valid==true}">
                <h2> Your not an ADMIN so you can't see the list of the users</h2>
            </c:if>




    </c:if>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/jqueryScript.js"></script>
</body>
</html>
