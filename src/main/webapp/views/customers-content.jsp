    <h1>Customer Management</h1>
    <div class="table-container">
        <a href="register-customer.jsp" class="register-btn">Register New Customer</a>
        <table>
            <thead>
                <tr>
                    <th>Customer ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${customerList}">
                    <tr>
                        <td><c:out value="${customer.customerID}" /></td>
                        <td><c:out value="${customer.name}" /></td>
                        <td><c:out value="${customer.email}" /></td>
                        <td><c:out value="${customer.phone}" /></td>
                        <td><c:out value="${customer.address}" /></td>
                        <td>
                            <a href="editCustomer.jsp?id=${customer.customerID}" class="edit-btn">
                                <i class="fas fa-edit"></i>
                            </a>
                            <a href="deleteCustomer?id=${customer.customerID}" class="delete-btn" onclick="return confirm('Are you sure?');">
                                <i class="fas fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
