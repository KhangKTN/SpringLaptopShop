<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Admin - T&T Laptop" />
    <meta name="author" content="KhangKTN" />
    <title>Create Product</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
        <jsp:include page="../../common/alert.jsp" />
        <jsp:include page="../layout/sidebar.jsp" />
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <ol class="breadcrumb mt-4">
                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                        <li class="breadcrumb-item active">Product</li>
                    </ol>
                    <h1 class="mt-4">Create Product</h1>
                    <hr />
                    <div class="mt-5">
                        <div class="row">
                            <div class="col-md-10 col-12 mx-auto">
                                <form:form method="post" action="/admin/product/create" class="row"
                                    enctype="multipart/form-data" modelAttribute="product">
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label fw-semibold">Name:</label>
                                        <form:textarea rows="2" type="text" class="form-control" path="name" autofocus="autofocus"/>
                                        <form:errors class="text-danger" path="name"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label fw-semibold">Slug:</label>
                                        <form:textarea rows="2" type="text" class="form-control" path="slug"/>
                                        <form:errors class="text-danger" path="slug"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-3">
                                        <label class="form-label fw-semibold">Price:</label>
                                        <form:input type="number" class="form-control" path="price"/>
                                        <form:errors class="text-danger" path="price"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-3">
                                        <label class="form-label fw-semibold">Quantity:</label>
                                        <form:input type="number" class="form-control" path="quantity"/>
                                        <form:errors class="text-danger" path="quantity"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-3">
                                        <label class="form-label fw-semibold">Factory:</label>
                                        <form:select class="form-select" path="factory">
                                            <form:option value="">-- Choose --</form:option>
                                            <c:forEach var="factory" items="${factoryList}">
                                                <form:option value="${factory.key}">${factory.value}</form:option>
                                            </c:forEach>
                                        </form:select>
                                        <form:errors class="text-danger" path="factory"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-3">
                                        <label class="form-label fw-semibold">Target:</label>
                                        <form:select class="form-select" path="target">
                                            <form:option value="">-- Choose --</form:option>
                                            <c:forEach var="target" items="${targetList}">
                                                <form:option value="${target.key}">${target.value}</form:option>
                                            </c:forEach>
                                        </form:select>
                                        <form:errors class="text-danger" path="target"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-3">
                                        <label class="form-label fw-semibold">CPU Type:</label>
                                        <form:select class="form-select" path="cpuType">
                                            <form:option value="">-- Choose --</form:option>
                                            <c:forEach var="cpu" items="${cpuList}">
                                                <form:option value="${cpu.key}">${cpu.value}</form:option>
                                            </c:forEach>
                                        </form:select>
                                        <form:errors class="text-danger" path="cpuType"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-3">
                                        <label class="form-label fw-semibold">CPU Name:</label>
                                        <form:input type="text" class="form-control" path="cpu"/>
                                        <form:errors class="text-danger" path="cpu"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-3">
                                        <label class="form-label fw-semibold">VGA Type:</label>
                                        <form:select class="form-select" path="vgaType">
                                            <form:option value="">-- Choose --</form:option>
                                            <c:forEach var="vga" items="${vgaList}">
                                                <form:option value="${vga.key}">${vga.value}</form:option>
                                            </c:forEach>
                                        </form:select>
                                        <form:errors class="text-danger" path="vgaType"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-3">
                                        <label class="form-label fw-semibold">VGA Name:</label>
                                        <form:input type="text" class="form-control" path="vga"/>
                                        <form:errors class="text-danger" path="vga"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-3">
                                        <label class="form-label fw-semibold">Storage:</label>
                                        <form:select class="form-select" path="storage">
                                            <form:option value="">-- Choose --</form:option>
                                            <c:forEach var="storage" items="${storageList}">
                                                <form:option value="${storage.key}">${storage.value}</form:option>
                                            </c:forEach>
                                        </form:select>
                                        <form:errors class="text-danger" path="ram"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-3">
                                        <label class="form-label fw-semibold">RAM:</label>
                                        <form:input type="number" class="form-control" path="ram"/>
                                        <form:errors class="text-danger" path="ram"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-3">
                                        <label class="form-label fw-semibold">Screen:</label>
                                        <form:input type="number" class="form-control" path="screen" step="0.1" onchange="setTwoNumberDecimal"/>
                                        <form:errors class="text-danger" path="screen"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-3">
                                        <label class="form-label fw-semibold">Status:</label>
                                        <div>
                                            <c:forEach varStatus="loop" var="status" items="${statusList}">
                                                <div class="form-check form-check-inline">
                                                    <form:radiobutton checked="${loop.index == 0 ? 'checked' : ''}" class="form-check-input" path="status" id="status_${status.key}" value="${status.key}"/>
                                                    <label class="form-check-label" for="status_${status.key}">${status.value}</label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <form:errors class="text-danger" path="status"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label for="productImg" class="form-label fw-semibold">Image:</label>
                                        <!-- <c:choose>
                                            <c:when test="${not empty imageFile}" >
                                                <input value="${imageFile}" class="form-control" type="file" id="productImgReturn"
                                                    accept=".png, .jpg, .jpeg" name="productImg" 
                                                />
                                            </c:when>
                                            <c:otherwise>
                                                <input class="form-control" type="file" id="productImg"
                                                    accept=".png, .jpg, .jpeg" name="productImg" 
                                                />
                                            </c:otherwise>
                                        </c:choose> -->
                                        <input class="form-control" type="file" id="productImg"
                                            accept=".png, .jpg, .jpeg" name="productImg" 
                                        />
                                        <form:errors class="text-danger" path="image"/>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <img style="display: none; width: 100%;" alt="product preview"
                                            id="productImgPreview" />
                                    </div>
                                    <div class="col-12 mt-4 text-center">
                                        <button type="submit" class="btn btn-primary py-2 px-4">Create Product</button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <jsp:include page="../layout/footer.jsp" />
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="/js/scripts.js"></script>
    <script>
        $(document).ready(() => {
            const productImg = $("#productImg");

            productImg.change(function (e) {
                const imgURL = URL.createObjectURL(e.target.files[0]);
                $("#productImgPreview").attr("src", imgURL);
                $("#productImgPreview").css({ "display": "block" });
            });

            $('textarea[name=name]').change(function () {
                $('textarea[name=slug]').val(getSlugify($(this).val()))
            })
        });

        function setTwoNumberDecimal(event) {
            this.value = parseFloat(this.value).toFixed(1);
        }

        $('input[type=number]').focusin(function () {
            const value = $(this).val().trim()
            if (value === '0' || value === '0.0') {
                $(this).val('')
            }
        })

        $('input[type=number]').focusout(function () {
            const value = $(this).val().trim()
            if (value === '') {
                $(this).val(0)
            }
        })
    </script>
</body>
</html>