<link href="${contextPath}/resources/css/header.css" rel="stylesheet">
<div>
    <nav class="navbar-inverse">
        <div>

            <div class="navbar-header">
                <a class="navbar-brand" href="#">UT'Internship</a>
            </div>
            <div class="collapse navbar-collapse" id="navbar-collapse-3">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Manage Users</a></li>
                    <li><a href="#">Manage Forms</a></li>
                    <li><a href="#">Manage Subjects</a></li>
                    <li><a href="#">Manage Question</a></li>
                    <li><a href="#">Manage Answers</a></li>
                    <li> <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                        <a onclick="document.forms['logoutForm'].submit()">Logout</a></li>
                    <li>
                        <a class="btn btn-default btn-outline btn-circle"  data-toggle="collapse" href="#nav-collapse3" aria-expanded="false" aria-controls="nav-collapse3">${pageContext.request.userPrincipal.name}</a>
                    </li>
                </ul>

            </div>
        </div>
    </nav>
</div>

