<%@ include file="../inc/header.jsp" %>
<!-- Content wrapper -->
<div class="content-wrapper">
    <!-- Content -->
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="row">
            <div class="col-xl">
                <div class="card mb-4">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <h5 class="mb-0">Edit User</h5>
                        <small class="text-muted float-end">Update user details</small>
                    </div>
                    <div class="card-body">
                        <span class="error">${error}</span>
                        <c:remove var="error" scope="session"/>
                        <form action="EditUserServlet" method="post">
                            <input type="hidden" name="userId" value="${user.id}"/>
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Email address</label>
                                <input name="email" type="email" class="form-control" id="exampleInputEmail1"
                                       value="${user.email}" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputPassword1" class="form-label">New Password</label>
                                <input name="password" type="password" class="form-control" id="exampleInputPassword1">
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputRePassword1" class="form-label">Re-enter Password</label>
                                <input name="repassword" type="password" class="form-control" id="exampleInputRePassword1">
                            </div>

                            <div class="mb-3">
                                <label for="role" class="form-label">Role</label>
                                <input name="role" type="text" class="form-control" id="role" value="${user.role}">
                            </div>

                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../inc/footer.jsp" %>
