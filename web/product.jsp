<%@include file="./inc/header.jsp"%>
<!-- Title page -->
<section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('assets/images/bg-02.jpg');">
    <h2 class="ltext-105 cl0 txt-center">
        ${category.name}
    </h2>
    <!-- Search bar -->
    <form action="ShopServlet" method="get" class="search-form" style="display: flex; justify-content: center; margin-top: 15px;">
        <input type="text" name="search" class="form-control" placeholder="Search for products..." required style="width: 250px;">
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
</section>	

<!-- Product -->
<div class="bg0 m-t-23 p-b-140">
    <div class="container">
        <div class="flex-w flex-sb-m p-b-52">
            <section id="filter" class="section-p1">
                <style> form.filter {
                        display: flex;
                        align-items: center;
                        justify-content: flex-start;
                    }
                    #filter .form-control {
                        margin: 0 10px;
                    }
                </style>

                <form action="ShopServlet" method="get" class="filter">
                    <strong>Sort by:</strong>
                    <select name="property" class="form-control property">
                        <option value="name">Name</option>
                        <option value="price">Price</option>
                        <option value="createdAt">Time</option>
                    </select>
                    <strong>Order by:</strong>
                    <select name="order" class="form-control order">
                        <option value="asc">A-Z</option>
                        <option value="desc">Z-A</option>
                    </select>
                    <button type="submit" class="filter-submit btn btn-primary">Filter</button>
                </form>
            </section>
        </div>

        <div class="row isotope-grid">
            <c:forEach items="${productList}" var="product">
                <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women">
                    <!-- Block2 -->
                    <div class="block2">
                        <div class="block2-pic hov-img0">
                            <a href="ProductServlet?productId=${product.id}"><img src="${product.thumbnail}" alt="IMG-PRODUCT"></a>
                        </div>

                        <div class="block2-txt flex-w flex-t p-t-14">
                            <div class="block2-txt-child1 flex-col-l ">
                                <a href="product-detail.html" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                    ${product.name}
                                </a>

                                <span class="stext-105 cl3">
                                    $${product.price}
                                </span>
                            </div>

                            <div class="block2-txt-child2 flex-r p-t-3">
                                <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                                    <img class="icon-heart1 dis-block trans-04" src="assets/images/icons/icon-heart-01.png"
                                         alt="ICON">
                                    <img class="icon-heart2 dis-block trans-04 ab-t-l"
                                         src="assets/images/icons/icon-heart-02.png" alt="ICON">
                                </a>
                            </div>
                        </div> 
                    </div>
                </div>
            </c:forEach>
        </div>

        <section id="pagination" class="section-p1">
            <c:if test="${numberPage > 1}">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <c:forEach var="i" begin="1" end="${numberPage}">
                            <li class="page-item <c:if test='${i == page}'>active</c:if>'">
                                <a class="page-link" href="ShopServlet?page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>
        </section>

    </div>
</div>
<%@include file="./inc/footer.jsp"%>