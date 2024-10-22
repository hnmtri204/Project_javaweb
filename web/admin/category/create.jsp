<%@ include file="../inc/header.jsp" %>
<!-- Content wrapper -->
<div class="content-wrapper">
    <!-- Content -->
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="row">
            <div class="col-xl">
                <div class="card mb-4">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <h5 class="mb-0">Create Category</h5>
                        <small class="text-muted float-end">Default label</small>
                    </div>
                    <div class="card-body">
                        <span class="error">${error}</span>
                        <c:remove var="error" scope="session"/>
                        <form action="CreateCategoryServlet" method="post">
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Name:</label>
                                <input name="name" type="text" class="form-control" id="exampleInputEmail1"
                                       aria-describedby="emailHelp">
                                <div id="emailHelp" class="form-text">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="Thumbnail" class="form-label">Thumbnail:</label>
                                <input name="thumbnail" type="file" class="form-control" id="exampleInputEmail1"
                                       aria-describedby="emailHelp">
                                <div id="thumbnail" class="form-text">
                                </div>
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