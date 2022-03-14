<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Edit blog</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link type="text/css" rel="stylesheet" href="style/blog-list.css">
    <script src="https://cdn.ckeditor.com/4.17.2/standard/ckeditor.js"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/33.0.0/classic/ckeditor.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link rel="icon" href="images/fav.png" type="image/png" sizes="16x16">
    <link rel="stylesheet" href="css/main.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/color.css">
    <link rel="stylesheet" href="css/responsive.css">
    <link type="text/css" rel="stylesheet" href="style/blog-sidebar.css">
    <link type="text/css" rel="stylesheet" href="style/blog-list.css">
</head>
<body>

<div class="theme-layout">
    <!-- Navbar  -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light d-flex justify-content-around fixed-top">
        <div class="d-flex navbar-div-left">
            <a class="navbar-brand main-logo" href="/blogs">BlogSieuHay.com</a>

            <form class="form-inline my-2 my-lg-0 d-flex" action="/search" method="get">
                <input class="form-control mr-sm-2 input-search" type="search" placeholder="Tìm gì đó ..."
                       aria-label="Search" name="q">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm kiếm</button>
            </form>
        </div>
    </nav>

    <section>
        <div class="gap gray-bg">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row" id="page-contents">
                            <%-- bắt đầu left sidebar--%>
                            <div class="col-lg-3">
                                <aside class="sidebar static">
                                    <div class="widget friend-list stick-widget">
                                        <div class="sidebar-heading d-flex justify-content-between mb-4">
                                            <a class="nav-link sidebar-heading ml-1 username"
                                               href="#">User: ${username} </a>
                                            <%-- avatar: random image   credit: https://picsum.photos/--%>
                                            <img src="https://picsum.photos/150" class="image-avatar rounded mr-1"
                                                 alt="User avatar">
                                        </div>
                                        <ul class="naves">
                                            <li class="nav-item">
                                                <a class="nav-link" href="/blogs?action=create">Bài viết mới </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="/blogs?action=viewMyBlog">Quản lý Blog </a>
                                            </li>
                                            <li class="nav-item has-submenu">
                                                <a class="nav-link" href="#"> Danh mục bài viết
                                                </a>
                                                <ul class="submenu collapse">
                                                    <c:forEach var="category" items="${categories}">
                                                        <li><a class="nav-link"
                                                               href="/blogs?action=viewCategoryBlog&id=${category.id}">${category.name}</a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </li>
                                            </li>
                                            <c:if test="${isAdmin}">
                                                <li class="nav-item has-submenu">
                                                    <a class="nav-link" href="#"> Admin menu
                                                    </a>
                                                    <ul class="submenu collapse">
                                                        <li><a class="nav-link" href="/users">Quản lý Users </a></li>
                                                        <li><a class="nav-link" href="/categories">Quản lý
                                                            Categories </a></li>
                                                    </ul>
                                                </li>
                                            </c:if>
                                            <a href="/logout" class="btn">Đăng xuất</a>
                                            <%--                                            <li class="nav-item"><a href="/logout">Đăng xuất </a></li>--%>
                                        </ul>
                                    </div><!-- Shortcuts -->
                                </aside>
                            </div><!-- left sidebar -->
                            <%-- kết thúc left sidebar--%>
                            <%------------------------------------------------------------------------------%>
                            <%--bắt đầu Phân thân--%>
                            <div class="col-lg-6">
                                <div  class="blog-container border">
                                    <a class="btn btn-primary float-end" href="/blogs">Quay lại</a>
                                    <h2>Sửa bài viết</h2>
                                    <form action="/blogs?action=edit&id=${blog.id}" method="post">
                                        <div class="mb-3">
                                            <div class="mb-3">
                                                <label for="tittle" class="form-label">Tiêu đề</label>
                                                <input type="text" class="form-control" id="tittle" name="tittle" value="${blog.tittle}">

                                            </div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="content" class="form-label">Nội dung </label>
                                            <textarea name="content" id="content">${blog.content}</textarea>
<%--                                                        <textarea style="resize: none" class="form-control"  name="content" id="content" cols="30" rows="10"></textarea>--%>
                                        </div>
                                        <div class="mb-3">
                                            <label for="category" class="form-label">Thêm bài viết vào</label>
                                            <select  class="form-control" name="category_id" id="category" >
                                                <c:forEach var="category" items="${categories}">
                                                    <option value="${category.id}"
                                                            <c:if test="${blogCategory.id == category.id}"> selected </c:if>
                                                    >${category.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Cập nhật</button>
                                    </form>
                                </div>
                            </div>
                            <%--kết thúc Phân thân--%>
                            <%------------------------------------------------------------------------------%>
                            <%--bắt đầu right sidebar--%>
                            <div class="col-lg-3">
                                <aside class="sidebar static">
                                    <div class="widget friend-list stick-widget">
                                        <h4 class="widget-title">Friends</h4>
                                        <div id="searchDir"></div>
                                        <ul id="people-list" class="friendz-list">
                                        </ul>
                                    </div><!-- friends list sidebar -->
                                </aside>
                            </div><!-- right sidebar -->
                            <%--kết thúc right sidebar--%>
                            <%------------------------------------------------------------------------------%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>


<script>
    CKEDITOR.replace( 'content' );
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="js/sidebar.js"></script>


<script data-cfasync="false" src="../../cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
<script src="js/main.min.js"></script>
<script src="js/script.js"></script>
<script src="js/map-init.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8c55_YHLvDHGACkQscgbGLtLRdxBDCfI"></script>
</body>
</html>
