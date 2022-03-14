<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="style/blog-list.css">
    <link type="text/css" rel="stylesheet" href="style/blog-sidebar.css">
</head>
<body>
<!-- Navbar  -->
<nav class="navbar navbar-expand-lg navbar-light bg-light d-flex justify-content-around fixed-top">
    <div class="d-flex navbar-div-left">
        <a class="navbar-brand main-logo" href="/blogs">BlogSieuHay.com</a>

        <form class="form-inline my-2 my-lg-0 d-flex" action="/searchUser" method="get">
            <input class="form-control mr-sm-2 input-search" type="search" placeholder="Tìm người dùng ..."
                   aria-label="Search" name="q">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm kiếm</button>
        </form>
    </div>
</nav>


<div class="wrapper d-flex">
    <!-- Sidebar  -->
    <nav class="sidebar card fixed-top">
        <ul class="nav flex-column" id="nav_accordion">
            <div class="sidebar-heading d-flex justify-content-between mb-4">
                <a class="nav-link sidebar-heading ml-1 username" href="#">User: ${username} </a>
                <%-- avatar: random image   credit: https://picsum.photos/--%>
                <img src="https://picsum.photos/150" class="image-avatar rounded mr-1" alt="Cinque Terre">
            </div>
            <li class="nav-item">
                <a class="nav-link" href="/blogs?action=create">Bài viết mới </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/blogs?action=viewMyBlog">Quản lý Blog </a>
            </li>
            <li class="nav-item has-submenu">
                <a class="nav-link" href="#"> Danh mục bài viết
                    <i class="bi bi-caret-down-fill"></i>
                </a>
                <ul class="submenu collapse">
                    <c:forEach var="category" items="${categories}">
                        <li><a class="nav-link"
                               href="/blogs?action=viewCategoryBlog&id=${category.id}">${category.name}</a></li>
                    </c:forEach>
                </ul>
            </li>
            <c:if test="${isAdmin}">
                <li class="nav-item has-submenu">
                    <a class="nav-link" href="#"> Admin menu
                        <i class="bi bi-caret-down-fill"></i>
                    </a>
                    <ul class="submenu collapse">
                        <li><a class="nav-link" href="/users">Quản lý Users </a></li>
                        <li><a class="nav-link" href="/categories">Quản lý Categories </a></li>
                    </ul>
                </li>
            </c:if>

            <a class="nav-link btn btn-secondary btn-logout" href="/logout">Đăng xuất </a></li>

            <%--            <li class="nav-item">--%>
            <%--                <a class="nav-link" href="#"> Other link </a>--%>
            <%--            </li>--%>
        </ul>
    </nav>

    <!-- Middle Section  -->
    <div class="div-middle container">
        <h1>Danh sách người dùng</h1>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Username</th>
                <th scope="col">Email</th>
                <th scope="col">Số bài viết</th>
                <th scope="col">Vai trò</th>
                <th scope="col">Thay đổi quyền</th>
                <th scope="col">Block</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}" varStatus="loop">

                <tr>
                    <td>${loop.count}</td>
                    <td><a href="/users?action=view&id=${user.id}">${user.username}</a></td>
                    <td>${user.email}</td>
                    <td>
                            ${blogCounts.get(loop.index)}
                    </td>
                    <td>
                        ${map_roleId_roleName.get(user.role_id)}
                    </td>

                    <td>
                        <c:if test="${user.isAdmin()}">
                            <a class="btn btn-secondary" href="/users?action=unsetAdmin&id=${user.id}"><i
                                    class="fas fa-trash">Unset admin </i></a>
                        </c:if>
                        <c:if test="${!user.isAdmin()}">
                            <a class="btn btn-primary" href="/users?action=setAdmin&id=${user.id}"><i
                                    class="fas fa-trash">Set admin</i></a>
                        </c:if>
                    </td>

                    <td>
                        <c:if test="${user.status == true}">
                            <a class="btn btn-danger" href="/users?action=block&id=${user.id}"><i
                                    class="fas fa-trash">Block user</i></a>
                        </c:if>
                        <c:if test="${user.status == false}">
                            <a class="btn btn-success" href="/users?action=unblock&id=${user.id}"><i
                                    class="fas fa-trash">Unblock user</i></a>
                        </c:if>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
