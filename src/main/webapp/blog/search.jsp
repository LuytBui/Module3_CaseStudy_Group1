<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>BlogSieuHay.com</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link type="text/css" rel="stylesheet" href="style/blog-list.css">
    <link type="text/css" rel="stylesheet" href="style/blog-sidebar.css">
</head>
<body>
<!-- Navbar  -->
<nav class="navbar navbar-expand-lg navbar-light bg-light d-flex justify-content-around fixed-top">
    <div class="d-flex navbar-div-left">
        <a class="navbar-brand main-logo" href="/blogs">BlogSieuHay.com</a>

        <form class="form-inline my-2 my-lg-0 d-flex" action="/blogs/search" method="get">
            <input class="form-control mr-sm-2 input-search" type="search" placeholder="Tìm gì đó ..."
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
        <%--        <h1>Blogs Management</h1>--%>
        <c:forEach var="searchResult" items="${searchResults}" varStatus="loop">

        </c:forEach>

        </tbody>
        </table>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="js/sidebar.js"></script>

</body>
</html>
