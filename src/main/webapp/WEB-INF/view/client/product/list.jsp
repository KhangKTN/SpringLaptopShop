<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Sản Phẩm - Laptopshop</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
        rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
        rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


    <!-- Customized Bootstrap Stylesheet -->
    <link href="/client/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="/client/css/style.css" rel="stylesheet">
    <style>
        .page-link.disabled {
            color: var(--bs-pagination-disabled-color);
            pointer-events: none;
            background-color: var(--bs-pagination-disabled-bg);
        }

        #btnClear:hover{
            color: red !important;
        }

        .productName {
            margin-bottom: 12px;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;

            @supports (-webkit-line-clamp: 2) {
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: initial;
                display: -webkit-box;
                -webkit-line-clamp: 2;
                -webkit-box-orient: vertical;
            }
        }
    </style>
</head>

<body>

    <!-- Spinner Start -->
    <div id="spinner"
        class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
        <div class="spinner-grow text-primary" role="status"></div>
    </div>
    <!-- Spinner End -->

    <jsp:include page="../layout/header.jsp" />

    <!-- Single Product Start -->
    <div class="container-fluid py-5 mt-5">
        <div class="container py-5">
            <div class="row g-4 mb-5">
                <div>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="/">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Danh Sách Sản Phẩm
                            </li>
                        </ol>
                    </nav>
                </div>

                <div class="row g-4 fruite">
                    <!-- Filter -->
                    <div class="col-12 col-md-3">
                        <div class="row g-4">
                            <!-- Factory filter -->
                            <div class="col-12" id="factoryFilter">
                                <div class="mb-2"><b>Hãng sản xuất</b></div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input default-check" type="checkbox" id="factory"
                                        value="">
                                    <label class="form-check-label user-select-none" for="factory">Tất cả</label>
                                </div>
                                <c:forEach varStatus="loop" var="factory" items="${factoryList}">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="factory${loop.index}"
                                            value="${factory.key}">
                                        <label class="form-check-label user-select-none" for="factory${loop.index}">${factory.value}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- Target filter -->
                            <div class="col-12" id="targetFilter">
                                <div class="mb-2"><b>Mục đích sử dụng</b></div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input default-check" type="checkbox" id="target-0"
                                        value="">
                                    <label class="form-check-label" for="target-0">Tất cả</label>
                                </div>
                                <c:forEach varStatus="loop" var="target" items="${targetList}">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="target${loop.index}"
                                            value="${target.key}">
                                        <label class="form-check-label" for="target${loop.index}">${target.value}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- Price filter -->
                            <div class="col-12" id="priceFilter">
                                <div class="mb-2"><b>Mức giá</b></div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input default-check" type="checkbox" id="price-0"
                                        value="">
                                    <label class="form-check-label" for="price-0">Tất cả</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="price-2"
                                        value="duoi-10-trieu">
                                    <label class="form-check-label" for="price-2">Dưới 10 triệu</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="price-3"
                                        value="10-15-trieu">
                                    <label class="form-check-label" for="price-3">Từ 10 - 15
                                        triệu</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="price-4"
                                        value="15-20-trieu">
                                    <label class="form-check-label" for="price-4">Từ 15 - 20
                                        triệu</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="price-5"
                                        value="tren-20-trieu">
                                    <label class="form-check-label" for="price-5">Trên 20 triệu</label>
                                </div>
                            </div>
                            <!-- CPU filter -->
                            <div class="col-12" id="cpuFilter">
                                <div class="mb-2"><b>Công nghệ CPU</b></div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input default-check" type="checkbox" id="cpu"
                                        value="">
                                    <label class="form-check-label" for="cpu">Tất cả</label>
                                </div>
                                <c:forEach varStatus="loop" var="cpu" items="${cpuList}">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="cpu${loop.index}"
                                            value="${cpu.key}">
                                        <label class="form-check-label" for="cpu${loop.index}">${cpu.value}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- Card filter -->
                            <div class="col-12" id="vgaFilter">
                                <div class="mb-2"><b>Card đồ hoạ</b></div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input default-check" type="checkbox" id="vga"
                                        value="">
                                    <label class="form-check-label" for="vga">Tất cả</label>
                                </div>
                                <c:forEach varStatus="loop" var="vga" items="${vgaList}">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="vga${loop.index}"
                                            value="${vga.key}">
                                        <label class="form-check-label" for="vga${loop.index}">${vga.value}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- Ram filter -->
                            <div class="col-12" id="ramFilter">
                                <div class="mb-2"><b>RAM</b></div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input default-check" type="checkbox" id="ram"
                                        value="">
                                    <label class="form-check-label" for="ram">Tất cả</label>
                                </div>
                                <c:forEach varStatus="loop" var="ram" items="${ramList}">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="ram${loop.index}"
                                            value="${ram.key}">
                                        <label class="form-check-label" for="ram${loop.index}">${ram.value}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- Storage filter -->
                            <div class="col-12" id="storageFilter">
                                <div class="mb-2"><b>Ổ cứng</b></div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input default-check" type="checkbox" id="storage"
                                        value="">
                                    <label class="form-check-label" for="storage">Tất cả</label>
                                </div>
                                <c:forEach varStatus="loop" var="storage" items="${storageList}">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="storage${loop.index}"
                                            value="${storage.key}">
                                        <label class="form-check-label" for="storage${loop.index}">${storage.value}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- Screen size filter -->
                            <div class="col-12" id="screenFilter">
                                <div class="mb-2"><b>Kích thước màn hình</b></div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input default-check" type="checkbox" id="screen"
                                        value="">
                                    <label class="form-check-label" for="screen">Tất cả</label>
                                </div>
                                <c:forEach varStatus="loop" var="screen" items="${screenSizeList}">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="screen${loop.index}"
                                            value="${screen.key}">
                                        <label class="form-check-label" for="screen${loop.index}">${screen.value}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- Sort -->
                            <div class="col-12" id="sortBy">
                                <div class="mb-2"><b>Sắp xếp</b></div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" id="sort-1"
                                        value="gia-tang-dan" name="radio-sort">
                                    <label class="form-check-label" for="sort-1">Giá tăng dần</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" id="sort-2"
                                        value="gia-giam-dan" name="radio-sort">
                                    <label class="form-check-label" for="sort-2">Giá giảm dần</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" id="sort-3" checked
                                        value="" name="radio-sort">
                                    <label class="form-check-label" for="sort-3">Không sắp xếp</label>
                                </div>

                            </div>
                            <div class="col-12 row gap-3 mt-3">
                                <button class="btn border-secondary fs-6 rounded-pill px-2 py-2 text-primary text-uppercase col" id="btnClear">
                                    Xoá tất cả
                                </button>
                                <button
                                    class="btn border-secondary rounded-pill px-2 py-2 text-primary text-uppercase col"
                                    id="btnFilter">
                                    Lọc Sản Phẩm
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- Show list product -->
                    <div class="col-12 col-md-9 text-center">
                        <div class="row g-4">
                            <c:if test="${totalPages ==  0}">
                                <div>Không tìm thấy sản phẩm</div>
                            </c:if>
                            <!-- Show product list -->
                            <c:forEach var="product" items="${productList}">
                                <div class="col-md-6 col-lg-3">
                                    <div class="rounded position-relative fruite-item">
                                        <div class="fruite-img">
                                            <img src="/images/product/${product.image}"
                                                class="img-fluid w-100 rounded-top" alt="">
                                        </div>
                                        <div class="text-white text-capitalize bg-secondary px-3 py-1 rounded position-absolute"
                                            style="top: 10px; left: 10px;">${product.factory}
                                        </div>
                                        <div
                                            class="p-2 pb-3 rounded-bottom product-name">
                                            <a class="productName" href="/product/${product.slug}">
                                                ${product.name}
                                            </a>
                                            <div
                                                class="d-flex  flex-lg-wrap justify-content-center flex-column">
                                                <p style="font-size: 15px; text-align: center; width: 100%;"
                                                    class="text-dark  fw-bold mb-3">
                                                    <fmt:formatNumber type="number"
                                                        value="${product.price}" />
                                                    đ
                                                </p>
                                                <form action="/add-product-to-cart/${product.id}"
                                                    method="post">
                                                    <input type="hidden" name="${_csrf.parameterName}"
                                                        value="${_csrf.token}" />

                                                    <button
                                                        class="mx-auto btn border border-secondary rounded-pill px-3 text-primary"><i
                                                            class="fa fa-shopping-bag me-2 text-primary"></i>
                                                        Add to cart
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <!-- Pagination -->
                            <c:if test="${totalPages > 0}">
                                <div class="pagination d-flex justify-content-center mt-5">
                                    <li class="page-item">
                                        <a class="${1 eq currentPage ? 'disabled page-link' : 'page-link'}"
                                            href="/products?page=${currentPage - 1}${queryString}"
                                            aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
                                        <li class="page-item">
                                            <a class="${(loop.index + 1) eq currentPage ? 'active page-link' : 'page-link'}"
                                                href="/products?page=${loop.index + 1}${queryString}">
                                                ${loop.index + 1}
                                            </a>
                                        </li>
                                    </c:forEach>
                                    <li class="page-item">
                                        <a class="${totalPages eq currentPage ? 'disabled page-link' : 'page-link'}"
                                            href="/products?page=${currentPage + 1}${queryString}"
                                            aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>

                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Single Product End -->

    <jsp:include page="../layout/footer.jsp" />


    <!-- Back to Top -->
    <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
            class="fa fa-arrow-up"></i></a>


    <!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/client/lib/easing/easing.min.js"></script>
    <script src="/client/lib/waypoints/waypoints.min.js"></script>
    <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
    <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="/client/js/main.js"></script>
    <script>
        $('input.default-check').change(function () {
            const isChecked = $(this).prop('checked')
            if (isChecked) {
                $(this).parent().siblings().find('input').each(function () {
                    $(this).prop('checked', false)
                })
            }
        })
    </script>
</body>
</html>