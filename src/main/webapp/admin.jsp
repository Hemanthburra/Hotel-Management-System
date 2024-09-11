<%@ page import="java.util.*, com.HotelManagementSystem.models.Room" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel - Hotel Management System</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <style>
        /* General Styling */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            padding: 0;
        }
        h1, h2 {
            color: #333;
            text-align: center;
        }
        h1 {
            font-size: 3em;
            margin: 20px 0;
        }
        h2 {
            font-size: 2em;
            margin: 10px 0;
        }
        .container {
            max-width: 1200px;
            margin: auto;
            padding: 20px;
        }
        /* Header Styling */
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background-color: #fff;
            border-bottom: 1px solid #ddd;
        }
        .header .logo {
            font-size: 24px;
            font-weight: bold;
            color: #005eb8;
        }
        .header .actions {
            display: flex;
            align-items: center;
            gap: 20px;
        }
        .header .actions i {
            font-size: 18px;
            cursor: pointer;
        }
        .header .actions .user {
            font-size: 14px;
            color: #333;
            display: flex;
            align-items: center;
            gap: 5px;
        }
        .header .actions .logout {
            font-size: 14px;
            color: #333;
            text-decoration: none;
        }
        .header .actions .logout:hover {
            text-decoration: underline;
        }
        /* Card Styling */
        .card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin: 20px 0;
            text-align: center;
        }
        .card h2 {
            font-size: 1.5em;
            margin-bottom: 15px;
        }
        .card .btn {
            padding: 12px 30px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s;
        }
        .card .btn:hover {
            background-color: #0056b3;
        }
        /* Message Styling */
        .message {
            text-align: center;
            margin: 20px 0;
            padding: 10px;
            border-radius: 5px;
            color: white;
            font-size: 1.2em;
            font-weight: bold;
            width: 80%;
            margin: auto;
        }
        .message.success {
            background-color: #28a745;
        }
        .message.error {
            background-color: #dc3545;
        }
        /* Responsive Design */
        @media (max-width: 768px) {
            .card {
                margin: 10px;
            }
        }
    </style>
</head>
<body>
    <%
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        if (session.getAttribute("adminusername") == null) {
            response.sendRedirect("index.jsp");
        }
    %>

    <div class="header">
        <div class="logo">ADMIN PANEL</div>
        
        <div class="actions">
            <i class="fas fa-globe"></i>
            <div class="user">
                <i class="fas fa-user"></i>
                <span><%= session.getAttribute("adminname") %></span>
            </div>
            <a class="logout" href="LogoutServlet">Logout</a>
        </div>
    </div>

    <div class="container">
        <!-- Admin Panel Heading -->
        <h1>Admin Panel</h1>
        <h2>Welcome, <%= session.getAttribute("adminname") %>!</h2>

        <!-- Message Display Section -->
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
                String messageClass = message.startsWith("Successfully") ? "success" : "error";
        %>
        <div class="message <%= messageClass %>">
            <%= message %>
        </div>
        <% } %>

        <!-- Card for View All Rooms -->
        <div class="card">
            <h2>View All Rooms</h2>
            <form action="ViewRoomsServlet" method="post">
                <input type="submit" value="View All Rooms" class="btn">
            </form>
        </div>

        <!-- Card for View All Bookings -->
        <div class="card">
            <h2>View All Bookings</h2>
            <form action="BookedRoomServlet" method="post">
                <input type="submit" value="View All Bookings" class="btn">
            </form>
        </div>

        <!-- Logout Button -->
       
    </div>

    <script>
        // You can add any additional JavaScript logic here if necessary
    </script>

</body>
</html>
