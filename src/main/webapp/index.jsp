<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Access</title>
    <style>
        .admin-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 200px;
            gap: 15px;
            background: #f8f9fa;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            margin: 50px auto;
            text-align: center;
        }

        .admin-container button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease-in-out;
        }

        .admin-container button:hover {
            background-color: #0056b3;
        }

        .admin-container a {
            color: #007bff;
            text-decoration: none;
            font-size: 14px;
            transition: color 0.3s ease-in-out;
        }

        .admin-container a:hover {
            color: #0056b3;
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="admin-container">
        <h1>Megacity Cab Service</h1>
        <h3>Admin Portal</h3>
        <button id="createAdmin">Get Admin Credentials</button>
        <a href="views/login.jsp">Already created an admin account?</a>
    </div>

    <script>
    const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

    document.getElementById("createAdmin").addEventListener("click", async function(event) {
        event.preventDefault(); // Prevent default behavior

        const formData = new URLSearchParams();

        try {
            const response = await fetch(API_BASE_URL + "/auth", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
            });

            const result = await response.text();
            if (response.ok) {
                alert("Admin has been created successfully!, your email is admin@gmail.com and your password 123 ");
            } else {
                alert("Error: " + result.toString() || "Admin creation failed");
            }
        } catch (error) {
            alert("An error occurred: " + error.message);
        }
    });
</script>

</body>
</html>
