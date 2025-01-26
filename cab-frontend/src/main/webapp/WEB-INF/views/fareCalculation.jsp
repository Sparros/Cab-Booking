<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculate Fare</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2 class="text-center">Calculate Fare</h2>
        
        <!-- Form for fare calculation -->
        <form action="/fare-calculation/calculate-fare" method="post">
		    <div class="mb-3">
		        <label for="source" class="form-label">Source</label>
		        <input type="text" class="form-control" id="source" name="source" value="${cab.source}" required>
		    </div>
		    <div class="mb-3">
		        <label for="destination" class="form-label">Destination</label>
		        <input type="text" class="form-control" id="destination" name="destination" value="${cab.destination}" required>
		    </div>
		    <button type="submit" class="btn btn-primary">Calculate Fare</button>
		</form>
		
		<c:if test="${not empty cab.fare}">
		    <div class="alert alert-info mt-4">
		        <strong>Calculated Fare:</strong> £${cab.fare}
		    </div>
		</c:if>
    </div>
</body>
</html>
