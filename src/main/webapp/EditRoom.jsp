<%@ page import="com.HotelManagementSystem.models.Room" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Room</title>
    <style>
        /* Add some styling */
    </style>
</head>
<body>
<%
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        if (session.getAttribute("adminusername") == null) {
            response.sendRedirect("index.jsp");
        }
    %>
    <h1>Edit Room</h1>

    <%
        Room room = (Room) request.getAttribute("room");
        if (room != null) {
    %>
    <form action="UpdateRoomServlet" method="post">
        <input type="hidden" name="roomId" value="<%= room.getRoomid() %>">
        
        <label for="capacity">Capacity:</label>
        <input type="text" id="capacity" name="capacity" value="<%= room.getCapacity() %>" required>
        
        <label for="fare">Fare Per Day:</label>
        <input type="text" id="fare" name="fare" value="<%= room.getFare() %>" required>
        
        <label for="status">Status:</label>
        <input type="text" id="status" name="status" value="<%= room.getStatus() %>" required>
        
        <input type="submit" value="Update Room">
    </form>
    <%
        } else {
    %>
    <p>Room not found</p>
    <%
        }
    %>
</body>
</html>
