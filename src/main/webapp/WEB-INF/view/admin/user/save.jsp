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
                        <div class="col-12 col-md-6 mx-auto">
                            <h1 class="text-secondary">Create User</h1>
                            <form:form class="mt-5" action="/admin/user/create" method="post" modelAttribute="user" enctype="multipart/form-data">
                                <div class="row mb-3">
                                    <div class="col">
                                        <label class="form-label">Email</label>
                                        <form:input class="form-control" value="" type="text" path="email" id=""/>
                                    </div>
                                    <div class="col">
                                        <label class="form-label">Password</label>
                                        <form:input class="form-control" type="password" path="password" id=""/>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col">
                                        <label class="form-label">Phone number</label>
                                        <form:input class="form-control" type="text" path="phone" id=""/>
                                    </div>
                                    <div class="col">
                                        <label class="form-label">Full name</label>
                                        <form:input class="form-control" type="text" path="fullName" id=""/>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Address</label>
                                    <form:input class="form-control" type="text" path="address" id=""/>
                                </div>
                                <div class="row mb-3">
                                    <div class="col">
                                        <label class="form-label">Role</label>
                                        <form:select class="form-select" type="text" path="role.name" id="">
                                            <form:option value="User">User</form:option>
                                            <form:option value="Admin">Admin</form:option>
                                        </form:select>
                                    </div>
                                    <div class="col">
                                        <label class="form-label">Avatar</label>
                                        <input class="form-control" type="file" path="" name="avatarFile" id="avatarFile" accept=".png, .jpg, .jpeg"/>
                                    </div>
                                </div>
                                <img style="max-height: 250px; display: none; margin: auto;" alt="avatar preview"
                                    id="avatarPreview" />
                                <div class="mt-5 d-flex justify-content-end border-top pt-5">
                                    <a href="/admin/user" type="button" class="btn btn-secondary me-4">< Go back</a>
                                    <button class="btn btn-dark px-4">Save</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </main>
            <jsp:include page="../layout/footer.jsp"/>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="/js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
    <script src="/js/datatables-simple-demo.js"></script>

    <script>
        $(document).ready(() => {
            const avatarFile = $("#avatarFile");
            avatarFile.change(function (e) {
                const imgURL = URL.createObjectURL(e.target.files[0]);
                $("#avatarPreview").attr("src", imgURL);
                $("#avatarPreview").css({ "display": "block" });
            });
        });
    </script>
</body>
</html>