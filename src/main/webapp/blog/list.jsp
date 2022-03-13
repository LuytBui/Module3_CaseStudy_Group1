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

    <link rel="icon" href="images/fav.png" type="image/png" sizes="16x16">

    <link rel="stylesheet" href="css/main.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/color.css">
    <link rel="stylesheet" href="css/responsive.css">

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
    <%--    -------------------------------------------------------------------------------------------------%>
    <%--    <div class="topbar stick">--%>
    <%--    </div><!-- topbar -->--%>

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
                                                 alt="Cinque Terre">
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
                                                    <i class="bi bi-caret-down-fill"></i>
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
                                                        <i class="bi bi-caret-down-fill"></i>
                                                    </a>
                                                    <ul class="submenu collapse">
                                                        <li><a class="nav-link" href="/users">Quản lý Users </a></li>
                                                        <li><a class="nav-link" href="/categories">Quản lý
                                                            Categories </a></li>
                                                    </ul>
                                                </li>
                                            </c:if>

                                            <li class="nav-item"><a href="/logout">Đăng xuất </a></li>
                                        </ul>
                                    </div><!-- Shortcuts -->
                                </aside>
                            </div><!-- left sidebar -->
                            <%-- kết thúc left sidebar--%>
                            <%------------------------------------------------------------------------------%>
                            <%--bắt đầu Phân thân--%>
                            <div class="col-lg-6">
                                <c:forEach var="blog" items="${blogs}" varStatus="loop">
                                    <div class="central-meta item blog-container">
                                        <c:if test="${blog.user_id == loginUserId}">
                                            <div class="dropMenu-edit">
                                                <li style="list-style-type: none" class="nav-item dropdown">
                                                    <a href="#" class="nav-link dropdown-toggle container-username"
                                                       role="button"
                                                       data-bs-toggle="dropdown" aria-expanded="false">
                                                        ...
                                                    </a>
                                                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                                        <li>
                                                            <a class="dropdown-item"
                                                               href="/blogs?action=edit&id=${blog.id}">
                                                                Sửa
                                                            </a>
                                                        </li>
                                                        <hr>
                                                        <li>
                                                            <a class="dropdown-item"
                                                               href="/blogs?action=delete&id=${blog.id}">
                                                                Xóa
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </li>
                                            </div>
                                        </c:if>
                                        <div class="user-post">
                                            <div class="friend-info">
                                                    <%--                                                    <p class="text-right">--%>
                                                    <%--                                                         <span class="span-category-label"> Danh mục:--%>
                                                    <%--                                                          <a class="span-category"--%>
                                                    <%--                                                             href="/blogs?action=viewCategoryBlog&id=${blog.category_id}">--%>
                                                    <%--                                                                  ${map_categoryId_categoryName.get(blog.category_id)}--%>
                                                    <%--                                                          </a>--%>
                                                    <%--                                                         </span>--%>
                                                    <%--                                                    </p>--%>
                                                <figure>
                                                    <img src="https://picsum.photos/150"
                                                         class="image-avatar rounded mr-1" alt="Cinque Terre">
                                                    <!-- Ảnh đại diện -->
                                                </figure>

                                                <div class="friend-name">
                                                    <ins>
                                                        <a class="author"
                                                           href="/blogs?action=viewUserBlog&user_id=${blog.user_id}">
                                                                ${map_userId_userName.get(blog.user_id)}
                                                        </a>
                                                    </ins>
                                                    <span>Cập nhật lúc ${blog.dateModified}</span>
                                                </div>
                                                <div class="description">
                                                    <div class="mb-1">
                                                        <p class="blog-content-preview "
                                                           id="tittle${loop.count}"></p>
                                                        <script>
                                                            document.getElementById(`tittle${loop.count}`).innerHTML = `${blog.tittle}`;
                                                        </script>
                                                    </div>
                                                    <div class="mb-1">
                                                        <p class="blog-content-preview "
                                                           id="content${loop.count}"></p>
                                                        <script>
                                                            document.getElementById(`content${loop.count}`).innerHTML = `${blog.getContentPreview()}`;
                                                        </script>
                                                    </div>
                                                    <div>
                                                        <a href="/blogs?action=view&id=${blog.id}"
                                                           class="btn-view-blog">Xem bài viết >>></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
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
                                            <li>
                                                <%--                                                <figure>--%>
                                                <%--                                                    <img src="images/resources/friend-avatar.jpg" alt="">--%>
                                                <%--                                                    <span class="status f-online"></span>--%>
                                                <%--                                                </figure>--%>
                                                <%--                                                <div class="friendz-meta">--%>
                                                <%--                                                    <a href="time-line.html">bucky barnes</a>--%>
                                                <%--                                                    <i><a href="https://wpkixx.com/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="a0d7c9ced4c5d2d3cfccc4c5d2e0c7cdc1c9cc8ec3cfcd">[email&#160;protected]</a></i>--%>
                                                <%--                                                </div>--%>
                                            </li>
                                            <li>

                                            </li>
                                            <li>

                                            </li>
                                            <li>

                                            </li>

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

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-4">
                    <div class="widget">
                        <div class="foot-logo">
                            <div class="logo">
                                <a href="index-2.html" title=""><img src="images/logo.png" alt=""></a>
                            </div>
                            <p>
                                The trio took this simple idea and built it into the world’s leading carpooling
                                platform.
                            </p>
                        </div>
                        <ul class="location">
                            <li>
                                <i class="ti-map-alt"></i>
                                <p>33 new montgomery st.750 san francisco, CA USA 94105.</p>
                            </li>
                            <li>
                                <i class="ti-mobile"></i>
                                <p>+1-56-346 345</p>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4">
                    <div class="widget">
                        <div class="widget-title"><h4>follow</h4></div>
                        <ul class="list-style">
                            <li><i class="fa fa-facebook-square"></i> <a href="https://web.facebook.com/shopcircut/"
                                                                         title="">facebook</a></li>
                            <li><i class="fa fa-twitter-square"></i><a href="https://twitter.com/login?lang=en"
                                                                       title="">twitter</a></li>
                            <li><i class="fa fa-instagram"></i><a href="https://www.instagram.com/?hl=en" title="">instagram</a>
                            </li>
                            <li><i class="fa fa-google-plus-square"></i> <a href="https://plus.google.com/discover"
                                                                            title="">Google+</a></li>
                            <li><i class="fa fa-pinterest-square"></i> <a href="https://www.pinterest.com/" title="">Pintrest</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4">
                    <div class="widget">
                        <div class="widget-title"><h4>Navigate</h4></div>
                        <ul class="list-style">
                            <li><a href="about.html" title="">about us</a></li>
                            <li><a href="contact.html" title="">contact us</a></li>
                            <li><a href="terms.html" title="">terms & Conditions</a></li>
                            <li><a href="#" title="">RSS syndication</a></li>
                            <li><a href="sitemap.html" title="">Sitemap</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4">
                    <div class="widget">
                        <div class="widget-title"><h4>useful links</h4></div>
                        <ul class="list-style">
                            <li><a href="#" title="">leasing</a></li>
                            <li><a href="#" title="">submit route</a></li>
                            <li><a href="#" title="">how does it work?</a></li>
                            <li><a href="#" title="">agent listings</a></li>
                            <li><a href="#" title="">view All</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4">
                    <div class="widget">
                        <div class="widget-title"><h4>download apps</h4></div>
                        <ul class="colla-apps">
                            <li><a href="https://play.google.com/store?hl=en" title=""><i class="fa fa-android"></i>android</a>
                            </li>
                            <li><a href="https://www.apple.com/lae/ios/app-store/" title=""><i class="ti-apple"></i>iPhone</a>
                            </li>
                            <li><a href="https://www.microsoft.com/store/apps" title=""><i class="fa fa-windows"></i>Windows</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </footer> <!-- footer -->

</div>


<%-----------------------------------------------------------------------------------------------------------%>

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
