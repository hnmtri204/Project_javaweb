<%@ include file="../inc/header.jsp" %>
<!-- Content wrapper -->
<div class="content-wrapper">
    <!-- Content -->
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="row">
            <!-- Responsive Table -->
            <div class="card">
                <h5 class="card-header">Product List</h5>
                <div class="table-responsive text-nowrap">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Thumbnail</th>
                                <th>Category</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${productList}" var="product" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td>${product.name}</td>
                                    <td>${product.description}</td>
                                    <td>${product.price}</td>
                                    <td>${product.quantity}</td>
                                    <td><img src="${product.thumbnail}" alt="Thumbnail" style="width: 50px;" /></td>
                                    <td>${product.category.name}</td>
                                    <td>
                                        <a href="EditProductServlet?productId=${product.id}">
                                            <i class="fas fa-edit"></i> <!-- Edit icon -->
                                        </a>
                                    </td>
                                    <td>
                                        <form action="DeleteProductServlet" method="POST">
                                            <input type="hidden" name="productId" value="${product.id}"/>
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
