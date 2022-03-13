<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Create blog</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>
<div class="container">
    <a class="btn btn-primary float-end" href="/blogs">Quay lại</a>
    <form action="/blogs?action=vew&id=${blog.id}" method="post">
        <div class="mb-3">
            <a href="/blogs?action=viewUserBlog&user_id=${blog.user_id}">
                ${map_userId_userName.get(blog.user_id)}
            </a>
            <span class="span-date mr-2">Đã tạo lúc ${blog.dateModified}</span>
        </div>
        <div class="mb-3">
            <label for="tittle" class="form-label">Tiêu đề </label>
            <p class="form-control" id="tittle"></p>
            <script>
                document.getElementById(`tittle`).innerHTML = `${blog.tittle}`;
            </script>
        </div>
        <div class="mb-3">
            <label for="content${loop.count}" class="form-label">Nội dung </label>
            <p class="form-control" id="content"></p>
            <script>
                document.getElementById(`content`).innerHTML = `${blog.content}`;
            </script>
        </div>
        <div class="mb-3">
            <label for="category" class="form-label">Danh mục</label>
            <p id="category"class="form-control"></p>
            <script>
                document.getElementById(`category`).innerHTML = `${category.name}`;
            </script>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
