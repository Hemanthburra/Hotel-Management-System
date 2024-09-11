<%@ page import="java.util.List, com.HotelManagementSystem.models.Booking" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Booked Rooms</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #333;
            font-size: 2.5em;
        }
        table {
        
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #333;
            color: white;
            font-size: 1.2em;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .btn-back, .btn-logout, .btn-cancel {
            padding: 10px 20px;
            font-size: 1em;
            margin: 10px 5px;
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            text-decoration: none;
        }
         .btn-cancel {
         	background-color: red;
         	
         }
        .btn-back:hover, .btn-logout:hover, .btn-cancel:hover {
            background-color: #45a049;
        }
        .message {
            margin: 20px 0;
            padding: 10px;
            border-radius: 5px;
            color: white;
            font-weight: bold;
        }
        .message.error {
            background-color: #f44336;
        }
        .message.success {
            background-color: #4CAF50;
        }
    </style>
</head>
<body>
    <%
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        if ((session.getAttribute("user") == null) && (session.getAttribute("adminusername") == null)) {
            response.sendRedirect("index.jsp");
        }
        
    %>
    
    <h1>Your Booked Rooms</h1>
    
    <%
    	
        List<Booking> userBookings = (List<Booking>) request.getAttribute("BookedRoomsByUser");
        String cancelMessage = (String) request.getAttribute("cancelMessage");
        
        if (cancelMessage != null) {
    %>
    <p class="message success"><%= cancelMessage %></p>
    <%
        }
        
        if (userBookings != null && !userBookings.isEmpty()) {
    %>
    <table>
        <tr>
            <th>Booking ID</th>
            <th>Room ID</th>
            <th>From Date</th>
            <th>To Date</th>
            <th>Total Fare (Rs.)</th>
            <th>Booking Date</th>
            <th>Action</th>
        </tr>
        <% 
            for (Booking booking : userBookings) { 
        %>
        <tr>
            <td><%= booking.getBookingId() %></td>
            <td><%= booking.getRoomId() %></td>
            <td><%= booking.getFromDate() %></td>
            <td><%= booking.getToDate() %></td>
            <td>Rs.<%= booking.getTotalFare() %>/-</td>
            <td><%= booking.getBookingDate() %></td>
            <td>
                <!-- Cancel Booking Button -->
                <form action="CancelBookingServlet" method="post">
                    <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>">
                    <input type="submit" value="Cancel Booking" class="btn-cancel">
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
    <p class="message error">You have no bookings.</p>
    <%
        }
    %>
	<%
	if (session.getAttribute("user")!=null){
	%>
    <!-- Back and Logout buttons -->
    <a href="UserHome.jsp" class="btn-back">Back to Home</a>
    <%}else{
    %>
    <a href="admin.jsp" class="btn-back">Back to Home</a>
    <%} %>
    <a href="LogoutServlet" class="btn-logout">Logout</a>

</body>
</html>
