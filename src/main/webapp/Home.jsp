<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Our Hotel</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            color: #333;
            background-color: #f9f9f9;
	        }
	    .header {
	    background-color: #F5DEB3; /* Light grey (milk grey) color */
	    color: #333;
	    padding: 20px;
        font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
	    text-align: center;
	    position: relative;
	    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Adding a soft shadow */
		}
		.header a {
		    color: 	black; /* Adjusting link color to match the new header color */
		    text-decoration: none;
		    margin: 0 15px;
		    font-size: 16px;

		}
		.header .login-buttons {
		    position: absolute;
		    top: 50%;
		    right: 20px;
		    transform: translateY(-50%);
		}
		.login-buttons a {
		    padding: 10px 15px;
		    background-color: 		#E97451;
		    border-radius: 5px;
		    color: #fff;
		
		        }
		        .login-buttons a:hover{
		    padding: 10px 15px;
		    background-color: #988558;
		    border-radius: 5px;
		    color: #fff;
		
		        }
		.hero {
		        background-image: url('<%= request.getContextPath() %>/images/1.jpg');
		        background-size: cover;
		        background-position: center;
		        height: 100vh;
		        display: flex;
		        justify-content: center;
		        align-items: center;
		        color: #fff;
		    }        
    .hero h1 {
            font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
            font-size: 48px;
            color:black;
        }
        .features {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px; /* Adds spacing between cards */
}

.feature {
    width: 30%; /* Each card takes up approximately 30% of the row, making room for 3 per row */
    margin: 10px;
    padding: 15px;
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.feature img {
    width: 100%;
    height: 120px; /* Reduced image height to fit the smaller card size */
    object-fit: cover;
    border-radius: 10px 10px 0 0;
}

.feature h2 {
    font-size: 20px; /* Slightly smaller font size */
    margin-bottom: 10px;
}

.feature p {
    font-size: 14px; /* Smaller text for card description */
    margin-bottom: 10px;
}

        .footer {
            background-color: #F5DEB3;
            color: black;
            font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
            padding: 20px;
            text-align: center;
            clear: both;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Our Hotel</h1>
        <p>Welcome to our luxurious hotel, where comfort and relaxation await</p>
        <div class="login-buttons">
            <!-- Use JSP to generate dynamic URLs or process forms -->
            <a href="<%= request.getContextPath() %>/index.jsp">Customer Login</a>
            <a href="<%= request.getContextPath() %>/adminLogin.jsp">Admin Login</a>
        </div>
    </div>
    <div class="hero">
        <h1>Experience the best of hospitality</h1>
    </div>
    <div class="features">
        <div class="feature">
            <img src="<%= request.getContextPath() %>/images/2.jpg" alt="Luxurious Rooms"> <!-- Add your luxurious rooms image here (e.g. 'rooms.jpg') -->
            <h2>Luxurious Rooms</h2>
            <p>Our rooms are designed to provide the ultimate comfort and relaxation, with plush beds, modern amenities, and stunning views.</p>
        </div>
        <div class="feature">
            <img src="<%= request.getContextPath() %>/images/3.jpg" alt="Fine Dining"> <!-- Add your fine dining image here (e.g. 'dining.jpg') -->
            <h2>Fine Dining</h2>
            <p>Indulge in our exquisite cuisine, crafted by world-class chefs using only the freshest ingredients and finest techniques.</p>
        </div>
        <div class="feature">
            <img src="<%= request.getContextPath() %>/images/4.png" alt="Recreational Activities"> <!-- Add your recreational activities image here (e.g. 'activities.jpg') -->
            <h2>Recreational Activities</h2>
            <p>Enjoy a range of activities, from fitness and wellness to entertainment and leisure, designed to keep you engaged and entertained.</p>
        </div>
        <div class="feature">
            <img src="<%= request.getContextPath() %>/images/5.png" alt="Online Booking"> <!-- Add your online booking image here (e.g. 'online-booking.jpg') -->
            <h2>Online Booking</h2>
            <p>Book your room online with ease, and get instant confirmation. No more waiting!</p>
        </div>
        <div class="feature">
            <img src="<%= request.getContextPath() %>/images/6.png" alt="Immediate Booking"> <!-- Add your immediate booking image here (e.g. 'immediate-booking.jpg') -->
            <h2>Immediate Booking</h2>
            <p>Get immediate booking confirmation, and plan your stay with confidence.</p>
        </div>
        <div class="feature">
            <img src="<%= request.getContextPath() %>/images/7.jpg" alt="Cancellation Policy"> <!-- Add your cancellation policy image here (e.g. 'cancellation-policy.jpg') -->
            <h2>Flexible Cancellation Policy</h2>
            <p>Cancel or modify your booking online, with no cancellation charges. We understand that plans can change!</p>
        </div>
    </div>
    <div class="footer">
        <p>&copy; 2023 Our Hotel. All rights reserved.</p>
    </div>
</body>
</html>
