<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Portal</title>
    <link rel="stylesheet" href="../styles/login.css">
</head>
<body>
    <div class="login-wrapper">
        <div class="login-image">
            <img src="../assets/logo.jpg" alt="Admin Portal">
        </div>
        <div class="login-form">
            <h1>Megacity Cab Service</h1>
            <h3>Admin Portal</h3>
            <form action="admin-dashboard.jsp" method="post">
                <input type="email" placeholder="Enter email adderess" id="email" name="email" required>
                
                <input type="password" placeholder="Enter password" id="password" name="password" required>
                
                <button type="submit">Login</button>
            </form>
        </div>
    </div>
</body>
</html>
