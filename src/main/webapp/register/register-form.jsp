<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/3/2022
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <style>
        .message {
            color: red;
        }
    </style>

</head>
<body>
<section class="vh-100" style="background-color: #eee;">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-11">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Đăng ký tài khoản</p>

                                <form class="mx-1 mx-md-4" method="post">

                                    <c:if test='${requestScope["message"] != null}'>
                                        <div class="d-flex flex-row align-items-center mb-4 text-center">
                                            <p class="message">${requestScope["message"]}</p>
                                        </div>
                                    </c:if>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" id="form3Example1c" class="form-control"
                                                   name="username" value="${username}"/>
                                            <label class="form-label" for="form3Example1c">Tên người dùng</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="email" id="form3Example3c" class="form-control" name="email" value="${email}"/>
                                            <label class="form-label" for="form3Example3c">Email</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="phone" id="form3Example3c-1" class="form-control"
                                                   name="phone" value="${phone}"/>
                                            <label class="form-label" for="form3Example3c-1">Số điện thoại</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" id="form3Example4c" class="form-control"
                                                   name="password"/>
                                            <label class="form-label" for="form3Example4c">Mật khẩu</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" id="form3Example4cd" class="form-control"
                                                   name="password-repeat"/>
                                            <label class="form-label" for="form3Example4cd">Nhập lại mật khẩu</label>
                                        </div>
                                    </div>

                                    <div class="form-check d-flex justify-content-center mb-5">
                                        <input
                                                class="form-check-input me-2"
                                                type="checkbox"
                                                value=""
                                                id="form2Example3c"
                                                name="agree-ToS"
                                                <c:if test="${agreed}">checked</c:if>
                                        />
                                        <label class="form-check-label" for="form2Example3c">
                                            Tôi đồng ý với <a href="register/terms-of-service.html"> điều khoản dịch vụ</a>.
                                        </label>
                                    </div>

                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="submit" class="btn btn-primary btn-lg">Đồng ý</button>
                                    </div>

                                </form>

                            </div>
                            <div class="col-md-10 col-lg-6 col-xl-7 d-flex flex-column align-self-center align-items-center order-1 order-lg-2">
                                <a href="/blogs" target="_blank" rel="noopener noreferrer"><h2>BlogSieuHay.com</h2></a>
                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                     class="img-fluid" alt="Sample image">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
