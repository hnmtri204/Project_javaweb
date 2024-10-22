<%@ include file="./inc/header.jsp" %>

<div class="container">
    <h2 class="mtext-109 cl2 p-b-30">Status row</h2>
    <table class="table">
        <thead>
            <tr>
                <th>Order Code</th>
                <th>Status</th>
                <th>Total Amount</th>
                <th>Booking Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.code}</td>
                    <td>${order.status}</td>
                    <td>$${order.total}</td>
                    <td>${order.createdAt}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="./inc/footer.jsp" %>
