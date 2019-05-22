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

    <%-- <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">


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
            <h1 class="text-center">All User</h1>
            <table id="usersTable" class="display table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Second Name</th>
                    <th>Mail</th>
                    <th>Admin</th>
                    <th>Company</th>
                    <th>Phone</th>
                    <th>CreationDate</th>
                    <th>Valid</th>
                </tr>
                </thead>
                <!-- Footer Table -->
                <tfoot>
                <tr>
                    <th>Id</th>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Second Name</th>
                    <th>Mail</th>
                    <th>Admin</th>
                    <th>Company</th>
                    <th>Phone</th>
                    <th>CreationDate</th>
                    <th>Valid</th>
                </tr>
                </tfoot>
            </table>

                <%--
                            <div class="row col-md-12 ">
                                <div style="margin-bottom:20px; padding:10px; background-color:#336699; color:white;">
                                    <p>Type some text to search the table </p>
                                    <input class="form-control" id="inputFilter" type="text" placeholder="Search.." />
                                </div>
                                <table id="userTable" class="table table-bordered table-hover table-responsive">
                                    <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Username</th>
                                        <th>First Name</th>
                                        <th>Second Name</th>
                                        <th>Mail</th>
                                        <th>Admin</th>
                                        <th>Company</th>
                                        <th>Phone</th>
                                        <th>CreationDate</th>
                                        <th>Valid</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                --%>
        </div>
        <%--        <c:forEach items="${userList}" var="item">
                    <li>${item.id} | ${item.username} | ${item.firstName} | ${item.secondName} | ${item.mail} | ${item.admin} | ${item.company} | ${item.phone} | ${item.creationDate} | ${item.valid}</li>
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




            --%>
    </c:if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<%--<script src="${contextPath}/resources/js/jqueryScript.js"></script>--%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--<script src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="${contextPath}/resources/js/datatable.js"></script>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</body>
</html>
