<%@ include file="../inc/header.jsp" %>
<!-- Content wrapper -->
<div class="content-wrapper">
    <!-- Content -->
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="row">
            <!-- Responsive Table -->
            <div class="card">
                <h5 class="card-header">Category List</h5>
                <div class="table-responsive text-nowrap">
                    <table class="table">
                        <thead>
                            <tr class="text-nowrap">
                                <th>#</th>
                                <th>Name</th>
                                <th>Thumbnail</th>   
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="i" value="0"/>
                            <c:forEach items="${categoryList}" var="category">
                                <c:set var="i" value="${i+1}"/>
                                <tr>
                                    <th scope="row">${i}</th>
                                    <td>${category.name}</td>
                                    <td>${category.thumbnail}</td>
                                    <td>
                                        <a href="EditCategoryServlet?categoryId=${category.id}">
                                            <i class="fas fa-edit"></i> <!-- Edit icon -->
                                        </a>
                                    </td>
                                    <td>
                                        <form action="DeleteCategoryServlet" method="POST">
                                            <input type="hidden" name="categoryId" value="${category.id}"/>
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
