    <h1>Transactions Management</h1>
    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Bill ID</th>
                    <th>Payment Method</th>
                    <th>Amount</th>
                    <th>Status</th>
                    <th>Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="transaction" items="${transactionList}">
                    <tr>
                        <td><c:out value="${transaction.transactionID}" /></td>
                        <td><c:out value="${transaction.billID}" /></td>
                        <td><c:out value="${transaction.paymentMethod}" /></td>
                        <td><fmt:formatNumber value="${transaction.amount}" type="currency" currencyCode="USD" /></td>
                        <td><c:out value="${transaction.status}" /></td>
                        <td><fmt:formatDate value="${transaction.date}" pattern="yyyy-MM-dd HH:mm" /></td>
                
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
