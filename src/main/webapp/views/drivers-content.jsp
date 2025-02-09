    <h1>Driver Management</h1>
    <div class="table-container">
        <a href="register-driver.jsp" class="register-btn">Register New Driver</a>
        <table>
            <thead>
                <tr>
                    <th>Driver ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>License Number</th>
                    <th>Vehicle Assigned</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="driver" items="${driverList}">
                    <tr>
                        <td><c:out value="${driver.driverID}" /></td>
                        <td><c:out value="${driver.name}" /></td>
                        <td><c:out value="${driver.email}" /></td>
                        <td><c:out value="${driver.phone}" /></td>
                        <td><c:out value="${driver.licenseNumber}" /></td>
                        <td><c:out value="${driver.vehicleAssigned}" /></td>
                        <td>
                            <a href="editDriver.jsp?id=${driver.driverID}" class="edit-btn">
                                <i class="fas fa-edit"></i>
                            </a>
                            <a href="deleteDriver?id=${driver.driverID}" class="delete-btn" onclick="return confirm('Are you sure?');">
                                <i class="fas fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

