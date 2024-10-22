<%@ include file="../inc/header.jsp" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<!-- Content wrapper -->
<div class="content-wrapper">
    <!-- Content -->
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="row">
            <!-- Responsive Table -->
            <div class="card">
                <h5 class="card-header">Order List</h5>
                <div class="table-responsive text-nowrap">
                    <table class="table">
                        <thead>
                            <tr class="text-nowrap">
                                <th>#</th>
                                <th>Code</th>
                                <th>Status</th>
                                <th>User_id</th>  
                                <th>Edit</th>
                                <th>Delete</th>         
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="i" value="0"/>
                            <c:forEach items="${orderList}" var="order">
                                <c:set var="i" value="${i+1}"/>
                                <tr>
                                    <td scope="row">${i}</td>
                                    <td>
                                        <a href="IndexOrderItemServlet?orderId=${order.id}">${order.code}</a>
                                    </td>
                                    <td>${order.status}</td>
                                    <td>${order.user.email}</td>
                                    <td>
                                        <a href="EditOrderServlet?orderId=${order.id}">
                                            <i class="fas fa-edit"></i> <!-- Edit icon -->
                                        </a>
                                    </td>
                                    <td>
                                        <form action="DeleteOrderServlet" method="POST">
                                            <input type="hidden" name="orderId" value="${order.id}"/>
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
