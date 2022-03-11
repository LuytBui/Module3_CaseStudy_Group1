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
        <a class="navbar-brand main-logo" href="#">BlogSieuHay.com</a>

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
                <li class="nav-item active container-username">
                    <a class="nav-link" href="#">Username <span class="sr-only">(current)</span></a>
                </li>
                <li>
                    <%--                    random image   credit: https://picsum.photos/--%>
                    <img src="https://picsum.photos/150" class="image-avatar rounded" alt="Cinque Terre">
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Dang xuat <span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </div>

</nav>

<div class="div-middle container">
    <%--        <h1>Blogs Management</h1>--%>

    <c:forEach var="blog" items="${blogs}">
        <div class="blog-container border" style="margin: 15px">
            <form>
                <div class="mb-3">
                    <a href="/blogs?action=view&id=${blog.id}"> ${map_userId_userName.get(blog.user_id)}</a>
                </div>
                <div class="mb-3">
                    <p style="text-align: center; font-size: 200%">${blog.tittle}</p>
                </div>
                <div class="mb-3">
                    <textarea disabled style="resize: none" class="form-control" name="content" id="content" cols="30"
                              rows="10">${blog.content}</textarea>
                </div>
                <a class="btn btn-danger" href="/blogs?action=delete&id=${blog.id}"><i class="fas fa-trash"></i></a>
                <a class="btn btn-info" href="/blogs?action=edit&id=${blog.id}"><i class="fas fa-edit"></i>
                </a>
            </form>
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
