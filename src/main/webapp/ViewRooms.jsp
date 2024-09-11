<%@ page import="java.util.*, com.HotelManagementSystem.models.Room" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View All Rooms - Hotel Management System</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            padding: 0;
        }
        h1 {
            color: #333;
            font-size: 2em;
            text-align: center;
            margin-top: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #333;
            color: white;
        }
        .btn {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .btn-container {
            margin-top: 20px;
        }
        /* Modal styling */
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
        }
        .modal-content {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            width: 400px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
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
    </style>
</head>
<body>
    <%
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        if (session.getAttribute("adminusername") == null) {
            response.sendRedirect("index.jsp");
        }
    %>

    <!-- Navbar -->
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
        <h1>View All Rooms</h1>

        <table>
            <thead>
                <tr>
                    <th>Room No</th>
                    <th>Capacity</th>
                    <th>Fare</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Room> rooms = (List<Room>) request.getAttribute("rooms");
                    if (rooms != null) {
                        for (Room room : rooms) {
                %>
                <tr>
                    <td><%= room.getRoomid() %></td>
                    <td><%= room.getCapacity() %></td>
                    <td><%= room.getFare() %></td>
                    <td><%= room.getStatus() %></td>
                    <td>
                        <form action="EditRoomServlet" method="get">
                            <input type="hidden" name="roomId" value="<%= room.getRoomid() %>">
                            <input type="submit" value="Edit" class="btn">
                        </form>
                    </td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5">No rooms found</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>

        <div class="btn-container">
            <button class="btn" onclick="openModal()">Add Room</button>
            <form action="admin.jsp" method="get" style="display:inline;">
                <input type="submit" value="Back" class="btn">
            </form>
            <form action="LogoutServlet" method="post" style="display:inline;">
                <input type="submit" value="Logout" class="btn">
            </form>
        </div>

        <!-- Add Room Modal -->
        <div id="addRoomModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal()">&times;</span>
                <h2>Add New Room</h2>
                <form action="AddRoomServlet" method="post">
                    <label for="roomid">Room No:</label>
                    <input type="text" id="roomid" name="roomid" required><br><br>
                    
                    <label for="capacity">Room Capacity:</label>
                    <input type="text" id="capacity" name="capacity" required><br><br>
                    
                    <label for="fare">Room Fare:</label>
                    <input type="number" id="fare" name="fare" step="0.01" required><br><br>
                    
                    <label for="status">Room Status:</label>
                    <input type="text" id="status" name="status" required><br><br>
                    
                    <input type="submit" value="Add Room" class="btn">
                </form>
            </div>
        </div>

        <script>
            function openModal() {
                document.getElementById("addRoomModal").style.display = "flex";
            }
            function closeModal() {
                document.getElementById("addRoomModal").style.display = "none";
            }
        </script>
    </div>
</body>
</html>
