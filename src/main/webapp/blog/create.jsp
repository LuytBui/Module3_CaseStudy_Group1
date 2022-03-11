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

    <script src="https://cdn.ckeditor.com/4.17.2/standard/ckeditor.js"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/33.0.0/classic/ckeditor.js"></script>

</head>
<body>
<div class="container">
    <h1>Create blog</h1>
<%--    <a class="btn btn-primary float-end" href="/blogs">Back to blogs list</a>--%>
    <form action="/blogs?action=create" method="post">
        <div class="mb-3">
            <label for="tittle" class="form-label">Tittle</label>
<%--            <input type="text" class="form-control" id="tittle" name="tittle">--%>
            <textarea name="tittle" id="tittle" rows="2"></textarea>
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">Content </label>
            <textarea name="content" id="content"></textarea>
            <%--            <textarea style="resize: none" class="form-control"  name="content" id="content" cols="30" rows="10"></textarea>--%>
        </div>
        <div class="mb-3">
            <label for="category" class="form-label">Thêm bài viết vào</label>
            <select  class="form-control" name="category_id" id="category">
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Create</button>
    </form>
</div>


<script>
    CKEDITOR.replace( 'content' );
</script>
<script>
    ClassicEditor
        .create( document.querySelector( '#tittle' ) )
        .then( editor => {
            console.log( editor );
        } )
        .catch( error => {
            console.error( error );
        } );
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
