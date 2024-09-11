<%@ page import="java.util.*, com.HotelManagementSystem.models.Room,java.time.LocalDate, java.time.format.DateTimeFormatter" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>World of HOTEL - Select Dates and View Available Rooms</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
        }
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
        .header .nav {
            display: flex;
            gap: 20px;
        }
        .header .nav a {
            text-decoration: none;
            color: #333;
            font-size: 14px;
        }
        .header .nav a:hover {
            text-decoration: underline;
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
        .search-bar {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
            background-color: #f8f8f8;
            position: relative;
            z-index: 2;
        }
        .search-bar input[type="date"] {
            width: 300px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-right: 10px;
        }
        .search-bar button {
            padding: 10px 20px;
            background-color: #ffcc00;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        .search-bar button:hover {
            background-color: #e6b800;
        }
        .hero {
            position: relative;
            text-align: right;
            color: white;
            z-index: 1;
        }
        .hero img {
            width: 100%;
            height: auto;
        }
        .hero .location {
            position: absolute;
            top: 20px;
            right: 20px;
            background-color: rgba(0, 0, 0, 0.5);
            padding: 10px 20px;
            border-radius: 4px;
            font-size: 14px;
        }
        .table-container {
            position: absolute;
            top: 200px; /* Adjust based on header height */
            left: 0;
            width: 100%;
            padding: 20px;
            box-sizing: border-box;
            z-index: 2;
            background: rgba(255, 255, 255, 0.8); /* Semi-transparent background */
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        table {
       
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #ffcc00;
            color: black;
            font-size: 1.2em;
        }
        tr:hover {
            background-color: #f1f1f1;
            cursor: pointer;
        }
        .btn-book {
            background-color: #ffcc00;
            color: black;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            border: none;
        }
        .btn-book:hover {
            background-color: #45a049;
        }
        .footer {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 10px;
            background-color: #f8f8f8;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <div class="header">
        <div class="logo">WORLD OF HOTEL</div>
        
        <div class="actions">
            <i class="fas fa-globe"></i>
            <div class="user">
                <i class="fas fa-user"></i>
                <span><%= session.getAttribute("user") %></span>
            </div>
            <a class="logout" href="LogoutServlet">Logout</a>
        </div>
    </div>

    <!-- Search Bar -->
    <div class="search-bar">
        <form action="CheckAvailabilityServlet" method="post" style="display: inline;">
            <input type="date" id="fromDate" name="fromDate" required>
            <input type="date" id="toDate" name="toDate" required>
            <button type="submit">FIND HOTELS</button>
        </form>
        <form action="BookedRoomServlet" method="post" style="display: inline;margin-left:20px;">
            <button type="submit">ALL BOOKINGS</button>
        </form>
    </div>

    <!-- Hero Section -->
    <div class="hero">
        <img src="<%= request.getContextPath() %>/images/8.jpg" alt="Hotel with palm trees and a pool" />
        <div class="location">Park Hyatt Marrakech, Morocco</div>
    </div>

    <!-- Main Content -->
    <%
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        if (session.getAttribute("user") == null) {
            out.print("user is null");
            response.sendRedirect("index.jsp");
        }
    %>
    
    <h2>Available Rooms for Selected Dates</h2>

    <div class="table-container">
        <% 
            String message = (String) request.getAttribute("message");
            if (message != null) { 
                String messageClass = message.startsWith("Successfully") ? "success" : "error";
        %>
        <div class="message <%= messageClass %>">
            <%= message %>
        </div>
        <% 
            }

            List<Room> availableRooms = (List<Room>) request.getAttribute("availableRooms");
            String fromDateStr = request.getParameter("fromDate");
            String toDateStr = request.getParameter("toDate");

            LocalDate fromDate = fromDateStr != null && !fromDateStr.isEmpty() ? LocalDate.parse(fromDateStr) : null;
            LocalDate toDate = toDateStr != null && !toDateStr.isEmpty() ? LocalDate.parse(toDateStr) : null;

            if (fromDate != null && toDate != null && !fromDate.isAfter(toDate)) {
                if (availableRooms != null && !availableRooms.isEmpty()) {
                    long numberOfDays = java.time.temporal.ChronoUnit.DAYS.between(fromDate, toDate);
        %>
        <p>Selected date from: <%= fromDate %> to: <%= toDate %></p>
        <table>
            <tr>
                <th>Room No.</th>
                <th>Capacity</th>
                <th>Fare Per Day</th>
                <th>Total Fare</th>
                <th>Action</th>
            </tr>
            <%
                for (Room room : availableRooms) {
                    double totalFare = room.getFare() * numberOfDays;
            %>
            <tr>
                <td><%= room.getRoomid() %></td>
                <td><%= room.getCapacity() %></td>
                <td>Rs.<%= room.getFare() %>/-</td>
                <td>Rs.<%= totalFare %>/-</td>
                <td>
                    <form action="BookRoom" method="post">
                        <input type="hidden" name="userid" value="<%= session.getAttribute("user") %>">
                        <input type="hidden" name="roomid" value="<%= room.getRoomid() %>">
                        <input type="hidden" name="fromDate" value="<%= fromDate %>">
                        <input type="hidden" name="toDate" value="<%= toDate %>">
                        <input type="hidden" name="totalFare" value="<%= totalFare %>">
                        <button type="submit" class="btn-book">Book Room</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <% 
                } else {
        %>
        <p>Sorry! No rooms are available for the selected dates.</p>
        <% 
                }
            } else {
        %>
        <p>Invalid date range! Please select valid 'from' and 'to' dates.</p>
        <% 
            }
        %>
    </div>

    <!-- Footer -->
    <div class="footer">
        <p>© 2024 World of HOTEL. All rights reserved.</p>
    </div>
</body>
</html>
