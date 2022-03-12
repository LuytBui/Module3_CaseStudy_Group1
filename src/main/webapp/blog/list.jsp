<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lis all blogs</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="style/blog-list.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light d-flex justify-content-around fixed-top">
    <div class="d-flex navbar-div-left">
        <a class="navbar-brand main-logo" href="/blogs">BlogSieuHay.com</a>

        <form class="form-inline my-2 my-lg-0 d-flex">
            <input class="form-control mr-sm-2 input-search" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>

    <div class="d-flex navbar-div-right">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li>
                    <a class="button-new-blog btn btn-success" href="/blogs?action=create">Tao bai moi</a>
                </li>
                <%--                <li class="nav-item active container-username">--%>
                <%--                    <a class="nav-link" href="#">Username <span class="sr-only">(current)</span></a>--%>
                <%--                </li>--%>
                <li class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle container-username" id="navbarDropdown-1" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        ${username}
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li>
                            <a class="dropdown-item" href="/blogs?action=viewMyBlog">
                                Quan ly bai viet
                            </a>
                        </li>
                        <hr>
                        <li>
                            <a class="dropdown-item" href="/logout">
                                Dang xuat
                            </a>
                        </li>
                    </ul>
                </li>

                <li>
                    <%--                    random image   credit: https://picsum.photos/--%>
                    <img src="https://picsum.photos/150" class="image-avatar rounded" alt="Cinque Terre">
                </li>
            </ul>
        </div>
    </div>

</nav>

<div class="div-middle container">

    <c:forEach var="blog" items="${blogs}" varStatus="loop">
        <div class="blog-container border" style="margin: 15px">
                <div class="mb-3">
                    <a href="/blogs?action=viewUserBlog&user_id=${blog.user_id}">
                            ${map_userId_userName.get(blog.user_id)}
                    </a>
                </div>
                <div class="mb-3">
                    <p style="font-size: 150%">${blog.tittle}</p>
                </div>
                <div class="mb-3">
                    <p  id="content${loop.count}"></p>
                    <script>
                        document.getElementById(`content${loop.count}`).innerHTML = `${blog.content}`;
                    </script>
                </div>
            <div style="position: relative; left: 1180px">
                <li style="list-style-type: none" class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle container-username" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        ...
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li>
                            <a class="dropdown-item" href="/blogs?action=edit&id=${blog.id}">
                                Sua
                            </a>
                        </li>
                        <hr>
                        <li>
                            <a class="dropdown-item" href="/blogs?action=delete&id=${blog.id}">
                                Xoa
                            </a>
                        </li>
                    </ul>
                </li>
            </div>
<%--                <a class="btn btn-danger" href="/blogs?action=delete&id=${blog.id}"><i class="fas fa-trash"></i></a>--%>
<%--                <a class="btn btn-info" href="/blogs?action=edit&id=${blog.id}"><i class="fas fa-edit"></i>--%>
<%--                </a>--%>
        </div>
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
