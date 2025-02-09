
            <h1>Register New Driver</h1>
            <form class="register-form" action="submit-driver.jsp" method="post">
                <div class="form-grid">
                    <div class="form-group">
                        <label for="name">Full Name</label>
                        <input type="text" id="name" name="name" required>
                    </div>

                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" required>
                    </div>

                    <div class="form-group">
                        <label for="phone">Phone Number</label>
                        <input type="tel" id="phone" name="phone" required>
                    </div>

                    <div class="form-group">
                        <label for="license">Driver's License Number</label>
                        <input type="text" id="license" name="license" required>
                    </div>

                    <div class="form-group">
                        <label for="vehicle">Vehicle Assigned</label>
                        <input type="text" id="vehicle" name="vehicle">
                    </div>
                </div>

                <div class="form-buttons">
                    <button type="button" class="back-btn" onclick="history.back()">Back</button>
                    <button type="submit" class="create-btn">Register</button>
                    
                </div>
            </form>

