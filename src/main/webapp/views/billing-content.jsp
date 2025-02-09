    <h1>Bills Management</h1>
    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>Bill ID</th>
                    <th>Booking ID</th>
                    <th>Customer Name</th>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="bill" items="${billList}">
                    <tr>
                        <td><c:out value="${bill.billID}" /></td>
                        <td><c:out value="${bill.bookingID}" /></td>
                        <td><c:out value="${bill.customerName}" /></td>
                        <td><fmt:formatNumber value="${bill.amount}" type="currency" currencyCode="USD" /></td>
                        <td><fmt:formatDate value="${bill.date}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>
                            <a href="deleteBill?id=${bill.billID}" class="delete-btn" onclick="return confirm('Are you sure?');">
                                <i class="fas fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
