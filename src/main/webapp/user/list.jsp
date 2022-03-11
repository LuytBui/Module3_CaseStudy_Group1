<%--
  Created by IntelliJ IDEA.
  User: PC - ADMIN
  Date: 11/03/2022
  Time: 8:19 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
    <div class="container">
        <h1>Danh sách người dùng</h1>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Username</th>
                <th scope="col">Số bài viết</th>
                <th scope="col">Ngày viết bài gần đây</th>
                <th scope="col">block</th>
                <th colspan="2"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}" varStatus="loop">
                <tr>
                    <td>${loop.index}</td>
                    <td><a href="/users?action=view&id=${user.id}">${user.username}</a></td>
                    <td>
                        ${blogCounts.get(loop.index)}
                    </td>
                    <td>
                        <a class="btn btn-info" href="/users?action=edit&id=${user.id}">
                            <i class="fas fa-edit"></i>
                        </a>
                    </td>
                    <td><a class="btn btn-danger" href="/users?action=delete&id=${user.id}"><i class="fas fa-trash"></i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
