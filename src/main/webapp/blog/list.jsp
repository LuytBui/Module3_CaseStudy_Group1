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
</head>
<body>
<div class="container">
        <h1>Blogs Management</h1>
        <a class="btn btn-primary float-end" href="/products?action=create">Add New blog</a>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Tittle </th>
                <th scope="col">Author</th>
                <th scope="col">Date Modify</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="blog" items="${blogs}">
                <tr>
                    <td>${blog.id}</td>
                    <td><a href="/blogs?action=view&id=${blog.id}"> ${blog.tittle}</a></td>
                    <td>${map_userId_userName.get(blog.user_id)}</td>
                    <td>${blog.dateModified}</td>
                    <td>
                        <a class="btn btn-info" href="/blog?action=edit&id=${blog.id}">
                            <i class="fas fa-edit"></i>
                        </a>
                    </td>
                    <td><a class="btn btn-danger" href="/blog?action=delete&id=${blog.id}"><i
                            class="fas fa-trash"></i></a></td>
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
