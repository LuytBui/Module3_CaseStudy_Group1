<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 1/3/2022
  Time: 09:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
<link rel="stylesheet" href="style/login-page.css">
<script></script>
<html>
<head>
    <title>Blog</title>
</head>
<body>

<div class="wrapper">
    <div class="logo"><img
            src="https://www.freepnglogos.com/uploads/twitter-logo-png/twitter-bird-symbols-png-logo-0.png" alt="">
    </div>
    <div class="text-center mt-4 name"> BlogSieuHay.com</div>
    <c:if test='${requestScope["message"] != null}'>
        <div class="message text-center mt-3 mb-3">
            <p>${requestScope["message"]}</p>
        </div>
    </c:if>

    <form class="p-3 mt-3" method="post">
        <div class="form-field d-flex align-items-center"><span class="far fa-user"></span> <input type="text"
                                                                                                   name="email"
                                                                                                   id="email"
                                                                                                   placeholder="Email">
        </div>
        <div class="form-field d-flex align-items-center"><span class="fas fa-key"></span> <input type="password"
                                                                                                  name="password"
                                                                                                  id="pwd"
                                                                                                  placeholder="Password">
        </div>
        <button class="btn btn-primary mt-3" type="submit">Đăng nhập</button>
        <a href="/register"><button class="btn btn-secondary mt-3" type="button">Đăng ký</button></a>
    </form>
</div>

</body>
</html>
