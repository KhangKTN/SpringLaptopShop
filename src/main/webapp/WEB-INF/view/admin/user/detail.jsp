<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create User</title>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <h1 class="text-info">User Detail</h1>
            <a href="/admin/user" class="btn btn-primary mt-5 mb-3 col-2">Return list user</a>
            <div>
                <div class="row border-bottom d-flex align-items-center py-2">
                    <div class="col-3">
                        <h6 class="text-end my-0">Name:</h6>
                    </div>
                    <span class="col-9">${user.fullName}</span>
                </div>
                <div class="row border-bottom d-flex align-items-center py-2">
                    <div class="col-3">
                        <h6 class="text-end my-0">Email:</h6>
                    </div>
                    <span class="col-9">${user.email}</span>
                </div>
                <div class="row border-bottom d-flex align-items-center py-2">
                    <div class="col-3">
                        <h6 class="text-end my-0">Phone number:</h6>
                    </div>
                    <span class="col-9">${user.phone}</span>
                </div>
                <div class="row border-bottom d-flex align-items-center py-2">
                    <div class="col-3">
                        <h6 class="text-end my-0">Address:</h6>
                    </div>
                    <span class="col-9">${user.address}</span>
                </div>
            </div>
        </div>
    </div>
</body>
</html>