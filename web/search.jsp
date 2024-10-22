<%@include file="./inc/header.jsp" %>

<section id="page-header">
    <h2>#${keyword}</h2>
    <p>Save more with coupons & up to 70% off!</p>
    <div class="wrap-form">
        <div class="search-form">
            <form action="SearchServlet" class="w-full" method="get">
                <div class="from-control flex items-center relative w-full">
                    <svg xmlns="http://www.w3.org/2000/svg" class="icon-search" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                    </svg>
                    <input name="keyword" placeholder="Search ..." type="text" class="search-input w-full bg-white border-[1px] rounded-[30px] h-[40px] border-[#b3b3b3] pl-[40px]">
                </div>
            </form>
        </div>
    </div>
</section>

<section id="pagination" class="section-p1">
    <c:if test="${not empty productList}">
        <c:forEach var="i" begin="1" end="${numberPage}">
            <a href="SearchServlet?keyword=${keyword}&page=${i}" <c:if test="${i == page}">class="active"</c:if>>${i}</a>
        </c:forEach>
    </c:if>
</section>

<!-- New Arrivals Section -->
<section class="bg0 p-t-23 p-b-140">
    <div class="container">
        <div class="p-b-10">
            <h3 class="ltext-103 cl5">New Arrivals</h3>
        </div>
        <div class="row isotope-grid">
            <c:forEach items="${newsProductList}" var="product">
                <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women">
                    <div class="block2">
                        <div class="block2-pic hov-img0">
                            <a href="ProductServlet?productId=${product.id}"><img src="${product.thumbnail}" alt="IMG-PRODUCT"></a>
                        </div>
                        <div class="block2-txt flex-w flex-t p-t-14">
                            <div class="block2-txt-child1 flex-col-l">
                                <a href="product-detail.html" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">${product.name}</a>
                                <span class="stext-105 cl3">$${product.price}</span>
                            </div>
                            <div class="block2-txt-child2 flex-r p-t-3">
                                <a href="ProductServlet?productId=${product.id}" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                                    <img class="icon-heart1 dis-block trans-04" src="assets/images/icons/icon-heart-01.png" alt="ICON">
                                    <img class="icon-heart2 dis-block trans-04 ab-t-l" src="assets/images/icons/icon-heart-02.png" alt="ICON">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<%@include file="./inc/footer.jsp" %>
