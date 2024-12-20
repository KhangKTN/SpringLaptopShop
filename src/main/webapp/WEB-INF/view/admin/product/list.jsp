<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="KhangKTN - Dự án laptopshop" />
    <meta name="author" content="KhangKTN" />
    <title>Dashboard - KhangKTN</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
        <jsp:include page="../layout/sidebar.jsp" />
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Manage Products</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                        <li class="breadcrumb-item active">Products</li>
                    </ol>
                    <!-- Section render data list -->
                    <div class="mt-5">
                        <div class="row">
                            <div class="col-12 mx-auto">
                                <div class="d-flex justify-content-between">
                                    <h3>Table products</h3>
                                    <a href="/admin/product/create" class="btn btn-primary">Create a product</a>
                                </div>

                                <hr />
                                <table class=" table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Image</th>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Price</th>
                                            <th>Factory</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="product" items="${productList}">
                                            <tr>
                                                <th class="text-center">
                                                    <img style="width: 100%; height: 80px;" src="/images/product/${product.image}" alt="">
                                                </th>
                                                <td>${product.id}</td>
                                                <td>${product.name}</td>
                                                <td>${product.price}</td>
                                                <td>${product.factory}</td>
                                                <td>
                                                    <a href="/admin/product/${product.id}" class="btn btn-secondary">Detail</a>
                                                    <a href="/admin/product/update/${product.id}" class="btn btn-warning mx-3">Update</a>
                                                    <a href="/admin/product/delete/${product.id}" onclick="return confirm('Are you sure delete?')" class="btn btn-danger">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- Pagination -->
                    <nav class="mx-auto" style="width: fit-content;" aria-label="Page navigation example">
                        <ul class="pagination mx-auto">
                            <li class="page-item">
                                <a class="page-link ${currentPage == 1 ? 'disabled' : ''}" href="/admin/product?page=${currentPage - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach var="page" begin="1" end="${totalPage}">
                                <c:choose>
                                    <c:when test="${currentPage == page}" >
                                        <li class="page-item"><a class="page-link active">${page}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link" href="/admin/product?page=${page}">${page}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <li class="page-item">
                                <a class="page-link ${currentPage == totalPage ? 'disabled' : ''}" href="/admin/product?page=${currentPage + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </main>
            <jsp:include page="../layout/footer.jsp" />
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="/js/scripts.js"></script>
</body>
</html>