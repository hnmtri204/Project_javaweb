<%@ include file="./inc/header.jsp" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha384-k6RqeWeci5ZR/Lv4MR0sA0FfDOMKZl5M4a6wF4F5xT1gK5h5F5k5T5wG5O4b5G5" crossorigin="anonymous">

<!-- Shopping Cart -->
<form class="bg0 p-t-75 p-b-85" style="margin-top: 60px;" id="cartForm" method="post" action="CartServlet">
    <div class="container">
        <div class="row">
            <div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
                <div class="wrap-table-shopping-cart">
                    <h2 class="mtext-109 cl2 p-b-30">Shopping Cart</h2>
                    <table class="table-shopping-cart">
                        <thead>
                            <tr class="table_head">
                                <th class="column-1">Product</th>
                                <th class="column-2">Name</th>
                                <th class="column-3">Price</th>
                                <th class="column-4">Quantity</th>
                                <th class="column-5">Total</th>
                                <th class="column-6">Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${cart}" var="orderItem">
                                <tr class="table_row">
                                    <td class="column-1">
                                        <div class="how-itemcart1">
                                            <img src="${orderItem.product.thumbnail}" alt="Product Image" class="img-fluid">
                                        </div>
                                    </td>
                                    <td class="column-2">${orderItem.product.name}</td>
                                    <td class="column-3">$${orderItem.price}</td>
                                    <td class="column-4">
                                        <form action="CartServlet" method="post" style="display: inline;">
                                            <input type="hidden" name="action" value="update"/>
                                            <input type="hidden" name="productId" value="${orderItem.productId}"/>
                                            <input onchange="this.form.submit()" name="quantity" type="number" value="${orderItem.quantity}" min="1" class="form-control" style="width: 60px; display: inline-block;">
                                        </form>
                                    </td>
                                    <td class="column-5">$${orderItem.price * orderItem.quantity}</td>
                                    <td class="column-6">
                                        <form action="CartServlet" method="post" style="display:inline;">
                                            <input type="hidden" name="action" value="delete"/>
                                            <input type="hidden" name="productId" value="${orderItem.productId}"/>
                                            <button type="submit" class="btn btn-danger" style="border: none; background: red; cursor: pointer;">
                                                <i class="fas fa-trash-alt"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
                <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                    <h4 class="mtext-109 cl2 p-b-30">Cart Totals</h4>
                    <div class="flex-w flex-t bor12 p-b-13">
                        <div class="size-208">
                            <span class="stext-110 cl2">Subtotal:</span>
                        </div>
                        <div class="size-209">
                            <span class="mtext-110 cl2">$${total}</span>
                        </div>
                    </div>
                    <div class="flex-w flex-t p-t-27 p-b-33">
                        <div class="size-208">
                            <span class="mtext-101 cl2">Total:</span>
                        </div>
                        <div class="size-209 p-t-1">
                            <span class="mtext-110 cl2">$${total}</span>
                        </div>
                    </div>

                    <h5 class="mtext-104 cl2 p-b-30">Payment QR Code:</h5>
                    <div class="p-t-20" id="qrCodeContainer">
                        <img src="data:image/png;base64,${qrCodeBase64}" alt="Payment QR Code" class="img-fluid mt-2" style="width: 300px; height: 300px;" />
                    </div>

                    <a href="CheckoutServlet" class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
                        Proceed to checkout
                    </a>
                </div>
            </div>
        </div>
    </div>
</form>

<%@ include file="./inc/footer.jsp" %>
