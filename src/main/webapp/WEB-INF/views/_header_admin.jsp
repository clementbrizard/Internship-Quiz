<link href="${contextPath}/resources/css/header.css" rel="stylesheet">
<div>
    <nav class="navbar-inverse">
        <div>

            <div class="navbar-header">
                <form id="welcomeForm" method="GET" action="${contextPath}/welcome">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>

                <a onclick="document.forms['welcomeForm'].submit()" class="navbar-brand">
                    UT'Internship
                </a>

            </div>
            <div class="collapse navbar-collapse" id="navbar-collapse-3">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <form id="usersForm" method="GET" action="${contextPath}/welcome">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>

                    <a onclick="document.forms['usersForm'].submit()"><i class="fas fa-users"></i></i>
                            Manage Users
                        </a></li>
                    <li>
                        <form id="managerForm" method="GET" action="${contextPath}/forms">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>

                        <a onclick="document.forms['managerForm'].submit()"><i class="fas fa-list-ol"></i>
                            Manage forms
                        </a></li>
                    <li><a href="#"><i class="fas fa-tags"></i>Manage Subjects</a></li>
                    <li><a href="#"><i class="fas fa-question"></i>Manage Question</a></li>
                    <li><a href="#"><i class="fas fa-check-square"></i>Manage Answers</a></li>
                    <li>
                        <form id="logoutForm" method="POST" action="${contextPath}/logout">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                        <a onclick="document.forms['logoutForm'].submit()"><i
                                class="fas fa-sign-out-alt"></i>Logout</a></li>
                    <li>
                        <a class="btn btn-default btn-outline btn-circle" data-toggle="collapse" href="#nav-collapse3"
                           aria-expanded="false"
                           aria-controls="nav-collapse3">${pageContext.request.userPrincipal.name}</a>
                    </li>
                </ul>

            </div>
        </div>
    </nav>
</div>

