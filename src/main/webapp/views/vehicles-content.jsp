    <h1>Vehicle Management</h1>
    <div class="table-container">
        <a href="registerVehicle.jsp" class="register-btn">Register New Vehicle</a>
        <table>
            <thead>
                <tr>
                    <th>Vehicle ID</th>
                    <th>License Plate</th>
                    <th>Model</th>
                    <th>Brand</th>
                    <th>Year</th>
                    <th>Capacity</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="vehicle" items="${vehicleList}">
                    <tr>
                        <td><c:out value="${vehicle.vehicleID}" /></td>
                        <td><c:out value="${vehicle.licensePlate}" /></td>
                        <td><c:out value="${vehicle.model}" /></td>
                        <td><c:out value="${vehicle.brand}" /></td>
                        <td><c:out value="${vehicle.year}" /></td>
                        <td><c:out value="${vehicle.capacity}" /></td>
                        <td><c:out value="${vehicle.status}" /></td>
                        <td>
                            <a href="editVehicle.jsp?id=${vehicle.vehicleID}" class="edit-btn">
                                <i class="fas fa-edit"></i>
                            </a>
                            <a href="deleteVehicle?id=${vehicle.vehicleID}" class="delete-btn" onclick="return confirm('Are you sure?');">
                                <i class="fas fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
