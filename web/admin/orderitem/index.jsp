<%@ include file="../inc/header.jsp" %>
<!-- Content wrapper -->
<div class="content-wrapper">

    <!-- Content -->
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="row">

            <!-- Responsive Table -->
            <div class="card">
                <h5 class="card-header">Order Items for Order: ${order.code}</h5>
                <div class="table-responsive text-nowrap">
                    <table class="table">
                        <thead>
                            <tr class="text-nowrap">
                                <th>#</th>
                                <th>Product Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="i" value="0"/>
                            <c:forEach items="${orderItemList}" var="orderItem">
                                <c:set var="i" value="${i+1}"/>
                                <tr>
                                    <td scope="row">${i}</td>
                                    <td>${orderItem.product.name}</td>
                                    <td>${orderItem.quantity}</td>
                                    <td>${orderItem.price}</td>
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
