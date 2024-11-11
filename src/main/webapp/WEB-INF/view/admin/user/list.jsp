<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp"/>
    
    <div id="layoutSidenav">
        <jsp:include page="../layout/sidebar.jsp"/>
        <div id="layoutSidenav_content">
            <main>
                <div class="container mt-5">
                    <div class="row">
                        <h1 class="text-secondary">Table Users</h1>
                        <div class="border-top mt-4 mb-3 py-3">
                            <a href="/admin/user/create" class="btn btn-dark col-2">Add new User</a>
                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Full Name</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Phone</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${list}">
                                    <tr>
                                        <th scope="row">${user.id}</th>
                                        <td>${user.fullName}</td>
                                        <td>${user.email}</td>
                                        <td>${user.phone}</td>
                                        <td>
                                            <a href="/admin/user/${user.id}" class="btn btn-secondary">Detail</a>
                                            <a href="/admin/user/update/${user.id}" class="btn btn-warning mx-3">Edit</a>
                                            <a href="/admin/user/delete/${user.id}" onclick="return confirm('Are you sure delete?')" class="btn btn-danger">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </main>
            <footer class="py-4 bg-light mt-auto">
                <div class="container-fluid px-4">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div class="text-muted">Copyright &copy; KhangKTN 2024</div>
                        <div>
                            <a href="https://hoidanit.vn/" target="_blank">Website</a>
                            &middot;
                            <a href="https://www.youtube.com/@hoidanit" target="_blank">Youtube channel</a>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="/js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
    <script src="/js/datatables-simple-demo.js"></script>
</body>
</html>