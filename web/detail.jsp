<%@include file="./inc/header.jsp"%>	
<!-- Product -->
<div class="bg0 m-t-23 p-b-50"></div>
<div class="container mt-4">
    <div class="row">
        <div class="col-md-6 col-lg-5 mb-4">
            <div class="card border-0 shadow-sm">
                <div class="card-body p-0">
                    <div class="position-relative">
                        <img src="${product.thumbnail}" alt="IMG-PRODUCT" class="img-fluid rounded" style="border-radius: 15px;">
                        <a href="images/product-detail-01.jpg" class="position-absolute top-0 end-0 m-3 btn btn-light btn-sm rounded-circle">
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-6 col-lg-5 p-b-30">
            <form action="CartServlet" method="post">
                <input type="hidden" name="action" value="create"/>
                <input type="hidden" name="productId" value="${product.id}"/>
                <input type="hidden" name="price" value="${product.price}"/>
                <div class="p-r-50 p-t-5 p-lr-0-lg">
                    <h4 class="mtext-105 cl2 js-name-detail p-b-14">
                        ${product.name}
                    </h4>

                    <span class="mtext-106 cl2">
                        $${product.price}
                    </span>

                    <p class="stext-102 cl3 p-t-23">    
                        ${product.description}
                    </p>

                    <div class="p-t-20">
                        <h5 class="mtext-104 cl2">QR Code Product</h5>
                        <img src="ProductServlet?action=getQRCode&productId=${product.id}" alt="QR Code" class="img-fluid mt-2"/>
                    </div>

                    <!-- Variants Section -->
                    <div class="p-t-20">
                        <h5 class="mtext-104 cl2">Variations</h5>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="me-4">
                                <span class="stext-102 cl3">Size:</span>
                                <select name="size" class="form-select mt-2">
                                    <option value="S">S</option>
                                    <option value="M">M</option>
                                    <option value="L">L</option>
                                    <option value="XL">XL</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="p-t-33">
                        <div class="d-flex align-items-center">
                            <div class="wrap-num-product flex-w m-r-20">
                                <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                    <i class="fs-16 zmdi zmdi-minus"></i>
                                </div>

                                <input class="mtext-104 cl3 txt-center num-product" type="number"
                                       name="quantity" value="1" min="1">

                                <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                    <i class="fs-16 zmdi zmdi-plus"></i>
                                </div>
                            </div>

                            <button type="submit" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
                                Add to cart
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <section class="bg0 p-t-23 p-b-140">
        <div class="container">
            <div class="p-b-10">
                <h3 class="ltext-103 cl5">
                    Related Products
                </h3>
            </div>
            <div class="row isotope-grid">
                <c:forEach var="relatedProduct" items="${relatedProducts}" varStatus="status">
                    <c:if test="${status.index < 4}">
                        <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item">
                            <!-- Block2 -->
                            <div class="block2">
                                <div class="block2-pic hov-img0">
                                    <a href="ProductServlet?productId=${relatedProduct.id}">
                                        <img src="${relatedProduct.thumbnail}" alt="${relatedProduct.name}">
                                    </a>
                                </div>

                                <div class="block2-txt flex-w flex-t p-t-14">
                                    <div class="block2-txt-child1 flex-col-l">
                                        <a href="ProductServlet?productId=${relatedProduct.id}" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                            ${relatedProduct.name}
                                        </a>

                                        <span class="stext-105 cl3">
                                            $${relatedProduct.price}
                                        </span>
                                    </div>

                                    <div class="block2-txt-child2 flex-r p-t-3">
                                        <a href="ProductServlet?productId=${relatedProduct.id}" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                                            <img class="icon-heart1 dis-block trans-04" src="assets/images/icons/icon-heart-01.png" alt="ICON">
                                            <img class="icon-heart2 dis-block trans-04 ab-t-l" src="assets/images/icons/icon-heart-02.png" alt="ICON">
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>   
        </div>
    </section>
</div>
<%@include file="./inc/footer.jsp"%>
