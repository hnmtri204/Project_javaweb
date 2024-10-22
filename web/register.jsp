<%@include file="./inc/header.jsp"%>
<section class="vh-100">
    <div class="container py-5 h-100">
        <div class="spacer">
            <style>
                .spacer {
                    height: 20cm;
                }
            </style>
            <div class="row d-flex align-items-center justify-content-center h-100">
                <div class="col-md-8 col-lg-7 col-xl-6">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg" class="img-fluid" alt="Phone image">
                </div>
                <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
                    <form method="post" action="RegisterServlet">
                        <h1>Registration</h1>
                        <span class="error">${error}</span>
                        <c:remove var="error" scope="session"/>
                        
                        <!-- Email input -->
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="email" name="email" id="form1Example13" class="form-control form-control-lg" required />
                            <label class="form-label" for="form1Example13">Email address</label>
                        </div>

                        <!-- Password input -->
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="password" name="password" id="form1Example23" class="form-control form-control-lg" required />
                            <label class="form-label" for="form1Example23">Password</label>
                        </div>

                        <!-- Repeat Password input -->
                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="password" name="repeatPassword" id="form1Example24" class="form-control form-control-lg" required />
                            <label class="form-label" for="form1Example24">Repeat your password</label>
                        </div>

                        <div class="d-flex justify-content-around align-items-center mb-4">
                            <!-- Checkbox -->
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="form1Example3" checked />
                                <label class="form-check-label" for="form1Example3"> Remember me </label>
                            </div>
                            <a href="#!">Forgot password?</a>
                        </div>

                        <!-- Submit button -->
                        <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg btn-block">Sign Up</button>
                        <p class="mb-5 pb-lg-2" style="color: #393f81;">Don't have an account? <a href="LoginServlet" style="color: #393f81;">Login</a></p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="./inc/footer.jsp"%>
