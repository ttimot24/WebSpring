<%@include file="header.jsp" %>

        <div class="jumbotron" style="background-color:black;padding-top:10px;">
            <div class="float-right">
            <a href="${pageContext.request.contextPath}/register" style="text-decoration:none;color:white;"><b>Register</b></a> |
            <security:authorize access="!isAuthenticated()">
                <a href="${pageContext.request.contextPath}/login" style="text-decoration:none;color:white;"><b>Login</b></a>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
                <a href="${pageContext.request.contextPath}/logout" style="text-decoration:none;color:white;"><b>Logout</b></a>
            </security:authorize>
            </div>
            <div class="container" style="padding-top: 79px;text-align:center;color:white;">
                <h1 style="font-size:72px;">${appName}</h1><br>
                <a href="#" class="btn btn-warning btn-lg">Application</a>
                <a href="${pageContext.request.contextPath}/WelcomeWS" class="btn btn-primary btn-lg">WebService</a>
            </div>
        </div>
                
<%@include file="footer.jsp" %>
