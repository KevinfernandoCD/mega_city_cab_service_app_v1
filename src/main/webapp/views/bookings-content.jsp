    <h1>Bookings Management</h1>
    <div class="table-container">
        <a href="registerBooking.jsp" class="register-btn">Register New Booking</a>
        <table>
            <thead>
                <tr>
                    <th>Booking ID</th>
                    <th>Customer Name</th>
                    <th>Driver Name</th>
                    <th>Vehicle</th>
                    <th>Pickup Location</th>
                    <th>Drop-off Location</th>
                    <th>Booking Date</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${bookingList}">
                    <tr>
                        <td><c:out value="${booking.bookingID}" /></td>
                        <td><c:out value="${booking.customerName}" /></td>
                        <td><c:out value="${booking.driverName}" /></td>
                        <td><c:out value="${booking.vehicle}" /></td>
                        <td><c:out value="${booking.pickupLocation}" /></td>
                        <td><c:out value="${booking.dropoffLocation}" /></td>
                        <td><fmt:formatDate value="${booking.bookingDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td><c:out value="${booking.status}" /></td>
                        <td>
                            <a href="editBooking.jsp?id=${booking.bookingID}" class="edit-btn">
                                <i class="fas fa-edit"></i>
                            </a>
                            <a href="deleteBooking?id=${booking.bookingID}" class="delete-btn" onclick="return confirm('Are you sure?');">
                                <i class="fas fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
