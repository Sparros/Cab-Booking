<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book a Cab</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2 class="text-center">Book a Cab</h2>
        
        <form action="/cab-booking/book-cab" method="post">
            <div class="mb-3">
                <label for="source" class="form-label">Source</label>
                <input type="text" class="form-control" id="source" name="source" value="${cabBooking.source}" required>
            </div>
            <div class="mb-3">
                <label for="destination" class="form-label">Destination</label>
                <input type="text" class="form-control" id="destination" name="destination" value="${cabBooking.destination}" required>
            </div>
            <button type="submit" class="btn btn-primary">Book Cab</button>
        </form>

        <!-- Display success message or booking result -->
        <c:if test="${not empty message}">
            <div class="alert alert-success mt-4">
                <strong>Success!</strong> ${message}
            </div>
        </c:if>

        <!-- Display the booking ID and fare after a successful booking -->
        <c:if test="${not empty cabBooking}">
            <div class="mt-4">
                <h4>Booking ID: ${cabBooking.id}</h4>
                <h5>Source: ${cabBooking.source}</h5>
                <h5>Destination: ${cabBooking.destination}</h5>
                <h5>Fare: £${cabBooking.fare}</h5>
            </div>
        </c:if>
    </div>
</body>
</html>
