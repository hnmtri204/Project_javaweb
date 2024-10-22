<%@ include file="../inc/header.jsp" %>
<!-- Content wrapper -->
<div class="content-wrapper">
    <!-- Content -->
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="row">
            <!-- Responsive Table -->
            <div class="card">
                <h5 class="card-header">Responsive Table</h5>
                <div class="table-responsive text-nowrap">
                    <table class="table">
                        <thead>
                            <tr class="text-nowrap">
                                <th>#</th>
                                <th>Email</th>
                                <th>Password</th>
                                <th>Role</th>  
                                <th>#</th>
                                <th>#</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:set var="i" value="0"/>
                        <c:forEach items="${userList}" var="user">
                            <c:set var="i" value="${i+1}"/>
                            <tr>
                                <th scope="row">${i}</th>
                                <td>${user.email}</td>
                                <td>${user.password}</td>
                                <td>${user.role}</td>
                                <td>
                                    <a href="EditUserServlet?userId=${user.id}">
                                        <i class="fas fa-edit"></i> <!-- Edit icon -->
                                    </a>
                                </td>
                                <td>
                                    <form action="DeleteUserServlet" method="POST">
                                        <input type="hidden" name="userId" value="${user.id}"/>
                                        <button class="btn btn-danger" type="submit">
                                            <i class="fas fa-trash-alt"></i> <!-- Delete icon -->
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!--/ Responsive Table -->
        </div>
    </div>
</div>
<%@ include file="../inc/footer.jsp" %>
