<%@ include file="../inc/header.jsp" %>
<!-- Content wrapper -->
<div class="content-wrapper">
    <!-- Content -->
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="row">
            <div class="col-xl">
                <div class="card mb-4">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <h5 class="mb-0">Edit Product</h5>
                        <small class="text-muted float-end">Default label</small>
                    </div>
                    <div class="card-body">
                        <span class="error">${error}</span>
                        <c:remove var="error" scope="session"/>
                        <form action="EditProductServlet" method="post">
                            <input type="hidden" name="productId" value="${product.id}"/>
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Name:</label>
                                <input name="name" value="${product.name}" type="text" class="form-control" id="exampleInputEmail1"
                                       aria-describedby="emailHelp">
                                <div id="emailHelp" class="form-text">We'll never share your email with anyone else.
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Description</label>
                                <textarea name="description" type="text" class="form-control" >${product.description}</textarea>
                                <div id="emailHelp" class="form-text">We'll never share your email with anyone else.
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Thumbnail:</label>
                                <input name="thumbnail" value="${product.thumbnail}" type="text" class="form-control" id="exampleInputEmail1"
                                       aria-describedby="emailHelp">
                                <div id="emailHelp" class="form-text">We'll never share your email with anyone else.
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Price</label>
                                <input name="price" value="${product.price}" type="text" class="form-control" id="exampleInputEmail1"
                                       aria-describedby="emailHelp">
                                <div id="emailHelp" class="form-text">We'll never share your email with anyone else.
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Quantity</label>
                                <input name="quantity" value="${product.quantity}" type="text" class="form-control" id="exampleInputEmail1"
                                       aria-describedby="emailHelp">
                                <div id="emailHelp" class="form-text">We'll never share your email with anyone else.
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputPassword1" class="form-label">Category</label>
                                <select name="categoryId" class="form-control">
                                    <c:forEach items="${categoryList}" var="category">
                                        <option <c:if test="${category.id == product.categoryId}">selected</c:if> value="${category.id}">${category.name}</option>
                                    </c:forEach>
                                </select>
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