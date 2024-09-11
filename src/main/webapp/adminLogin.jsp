<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hotel Management - Admin Login</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
<style media="screen">
    *,
    *:before,
    *:after {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }
    body {
        background-color: #080710;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .background {
        width: 90%;
        height: 70%;
        position: absolute;
        top: 0;
        left: 0;
        z-index: -1;
    }
    .background .shape {
        height: 200px;
        width: 200px;
        position: absolute;
        border-radius: 50%;
    }
    .shape:first-child {
        background: linear-gradient(
            #1845ad,
            #23a2f6
        );
        left: -80px;
        top: -80px;
    }
    .shape:last-child {
        background: linear-gradient(
            to right,
            #ff512f,
            #f09819
        );
        right: -30px;
        bottom: -80px;
    }
    form {
        height: auto;
        width: 400px;
        background-color: rgba(255,255,255,0.13);
        border-radius: 10px;
        backdrop-filter: blur(10px);
        border: 2px solid rgba(255,255,255,0.1);
        box-shadow: 0 0 40px rgba(8,7,16,0.6);
        padding: 50px 35px;
        box-sizing: border-box;
    }
    form * {
        font-family: 'Poppins', sans-serif;
        color: #ffffff;
        letter-spacing: 0.5px;
        outline: none;
        border: none;
    }
    form h3 {
        font-size: 32px;
        font-weight: 500;
        line-height: 42px;
        text-align: center;
    }
    label {
        display: block;
        margin-top: 30px;
        font-size: 16px;
        font-weight: 500;
    }
    input {
        display: block;
        height: 50px;
        width: 100%;
        background-color: rgba(255,255,255,0.07);
        border-radius: 3px;
        padding: 0 10px;
        margin-top: 8px;
        font-size: 14px;
        font-weight: 300;
    }
    ::placeholder {
        color: #e5e5e5;
    }
    button {
        margin-top: 50px;
        width: 100%;
        background-color: #ffffff;
        color: #080710;
        padding: 15px 0;
        font-size: 18px;
        font-weight: 600;
        border-radius: 5px;
        cursor: pointer;
    }
    p {
        margin-top: 20px;
        text-align: center;
        color: #ffffff;
    }
    a {
        color: #ff512f;
        text-decoration: none;
    }
</style>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); %>
    <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
    </div>
    <form id="adminLoginForm" action="/HotelManagementSystem/AdminLogin" method="post">
        <h3>Admin Login</h3>
        <label for="adminUsername">Username</label>
        <input type="text" placeholder="Admin Email" id="adminUsername" name="adminusername">
        <label for="adminPassword">Password</label>
        <input type="password" placeholder="Admin Password" id="adminPassword" name="adminpassword">
        <button type="submit">Admin Log In</button>
        <p><a href="index.jsp">User Login</a></p>
    </form>
</body>
</html>
