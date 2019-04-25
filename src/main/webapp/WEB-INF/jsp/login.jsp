<%@include file="header.jsp" %>

        <div class="jumbotron" style="background-color:black">
            <div class="container" style="padding-top: 25px;text-align:center;color:white;">
                <div class="col-md-6 offset-md-3">
                <h1 style="font-size:72px;">${appName}</h1><br>
                                
                <h3>Login</h3>
                <br>
                <% if(request.getParameter("error")!=null){ %>
                    <div class="alert alert-danger alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>Error!</strong> Could not login with these credentials!
                    </div>
                <%  } %>
                <br>
                <form name="f" method="POST" action="login">
                    <div class="form-group">
                      <label for="exampleInputEmail1">Username</label>
                      <input type="text" name="uname" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Username">
                      <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">Password</label>
                      <input type="password" name="pwd" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>
                    <div class="form-check">
                      <input type="checkbox" class="form-check-input" id="exampleCheck1">
                      <label class="form-check-label" for="exampleCheck1">Remember me</label>
                    </div><br>
                    <button type="submit" class="btn btn-primary">Login</button>
                  </form>
            </div>
            </div>
        </div>
            
<%@include file="footer.jsp" %>
