<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tất cả sản phẩm</title>
    <jsp:include page="../common/linkCss.jsp"/>
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
            <div class="row">
                <div class="mt-3">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="/">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Danh Sách Sản Phẩm</li>
                        </ol>
                    </nav>
                </div>
                <div class="col-3 ms-auto me-3 mb-3">
                    <select class="form-select" id="sortSelect" aria-label="Default select example">
                        <option value="">Mặc định</option>
                        <c:forEach varStatus="loop" var="sort" items="${sortList}">
                            <option value="${sort.key}">${sort.value}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row px-0 mx-0 fruite">
                    <!-- Filter -->
                    <div style="height: fit-content;" class="col-12 col-md-3 shadow py-3 rounded">
                        <div class="row px-2">
                            <!-- Factory filter -->
                            <div class="col-12" id="factoryFilter">
                                <a class="mb-2 d-flex align-items-center justify-content-between" data-bs-toggle="collapse" href="#checkboxFactory" aria-expanded="false">
                                    <b>Hãng sản xuất</b>
                                    <i class="fas fa-chevron-down ms-5"></i>
                                </a>
                                <div class="collapse show" id="checkboxFactory">
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
                            </div>
                            <hr class="my-3">
                            <!-- Target filter -->
                            <div class="col-12" id="targetFilter">
                                <a class="mb-2 d-flex align-items-center justify-content-between" data-bs-toggle="collapse" href="#checkboxTarget" aria-expanded="false">
                                    <b>Mục đích sử dụng</b>
                                    <i class="fas fa-chevron-down ms-5"></i>
                                </a>
                                <div class="collapse show" id="checkboxTarget">
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
                            </div>
                            <hr class="my-3">
                            <!-- Price filter -->
                            <div class="col-12" id="priceFilter">
                                <a class="mb-2 d-flex align-items-center justify-content-between" data-bs-toggle="collapse" href="#checkboxPrice" aria-expanded="false">
                                    <b>Mức giá</b>
                                    <i class="fas fa-chevron-down ms-5"></i>
                                </a>
                                <div class="collapse show" id="checkboxPrice">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input default-check" type="checkbox" id="price-0"
                                            value="">
                                        <label class="form-check-label" for="price-0">Tất cả</label>
                                    </div>
                                    <c:forEach varStatus="loop" var="price" items="${priceList}">
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="checkbox" id="price${loop.index}"
                                                value="${price.key}">
                                            <label class="form-check-label" for="price${loop.index}">${price.value}</label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <hr class="my-3">
                            <!-- CPU filter -->
                            <div class="col-12" id="cpuFilter">
                                <a class="mb-2 d-flex align-items-center justify-content-between" data-bs-toggle="collapse" href="#checkboxCpu" aria-expanded="false">
                                    <b>Công nghệ CPU</b>
                                    <i class="fas fa-chevron-down ms-5"></i>
                                </a>
                                <div class="collapse show" id="checkboxCpu">
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
                            </div>
                            <hr class="my-3">
                            <!-- Card filter -->
                            <div class="col-12" id="vgaFilter">
                                <a class="mb-2 d-flex align-items-center justify-content-between" data-bs-toggle="collapse" href="#checkboxVga" aria-expanded="false">
                                    <b>Card đồ hoạ</b>
                                    <i class="fas fa-chevron-down ms-5"></i>
                                </a>
                                <div class="collapse show" id="checkboxVga">
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
                            </div>
                            <hr class="my-3">
                            <!-- Ram filter -->
                            <div class="col-12" id="ramFilter">
                                <a class="mb-2 d-flex align-items-center justify-content-between" data-bs-toggle="collapse" href="#checkboxRam" aria-expanded="false">
                                    <b>RAM</b>
                                    <i class="fas fa-chevron-down ms-5"></i>
                                </a>
                                <div class="collapse show" id="checkboxRam">
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
                            </div>
                            <hr class="my-3">
                            <!-- Storage filter -->
                            <div class="col-12" id="storageFilter">
                                <a class="mb-2 d-flex align-items-center justify-content-between" data-bs-toggle="collapse" href="#checkboxStorage" aria-expanded="false">
                                    <b>Ổ cứng</b>
                                    <i class="fas fa-chevron-down ms-5"></i>
                                </a>
                                <div class="collapse show" id="checkboxStorage">
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
                            </div>
                            <hr class="my-3">
                            <!-- Screen size filter -->
                            <div class="col-12" id="screenFilter">
                                <a class="mb-2 d-flex align-items-center justify-content-between" data-bs-toggle="collapse" href="#checkboxScreen" aria-expanded="false">
                                    <b>Kích thước màn hình</b>
                                    <i class="fas fa-chevron-down ms-5"></i>
                                </a>
                                <div class="collapse show" id="checkboxScreen">
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
                            </div>
                            <hr class="my-3">
                            <div class="col-12 d-flex justify-content-center gap-3">
                                <button class="btn border-secondary rounded-pill px-3 py-2 text-primary text-capitalize" id="btnClear">
                                    <small>Xoá tất cả</small>
                                </button>
                                <button class="btn border-secondary rounded-pill px-3 py-2 text-primary text-capitalize" id="btnFilter">
                                    <small>Lọc Sản Phẩm</small>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- Show list product -->
                    <div class="col-12 col-md-9 px-4">
                        <div class="row g-3">
                            <c:if test="${totalPages ==  0}">
                                <div>Không tìm thấy sản phẩm</div>
                            </c:if>
                            <!-- Show product list -->
                            <c:forEach var="product" items="${productList}">
                                <div class="col-md-6 col-lg-3 text-center">
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
                                                class="d-flex flex-lg-wrap justify-content-center flex-column">
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

    <script>
        $('input.default-check').change(function () {
            const isChecked = $(this).prop('checked')
            if (isChecked) {
                $(this).parent().siblings().find('input').each(function () {
                    $(this).prop('checked', false)
                })
            }
        })

        $('a[data-bs-toggle=collapse]').click(function () {
            const icon = $(this).find('i')
            icon.removeClass('fa-chevron-up fa-chevron-down')
            if ($(this).hasClass('collapsed')) {
                icon.addClass('fa-chevron-up')
            } else {
                icon.addClass('fa-chevron-down')
            }
        })
    </script>
</body>
</html>