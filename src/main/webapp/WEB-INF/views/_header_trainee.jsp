
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
                    <li><a href="#"><i class="fas fa-play"></i> Fill a form</a></li>
                    <li><a href="#"><i class="fas fa-table"></i> Show Results</a></li>
                    <li> <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                        <a onclick="document.forms['logoutForm'].submit()"><i
                                class="fas fa-sign-out-alt"></i> Logout</a></li>
                    <li>
                        <a class="btn btn-default btn-outline btn-circle"  data-toggle="collapse" href="#nav-collapse3" aria-expanded="false" aria-controls="nav-collapse3">${pageContext.request.userPrincipal.name}</a>
                    </li>
                </ul>


            </div>
        </div>
    </nav>
</div>

