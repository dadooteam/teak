<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.teak.data.po.*,org.apache.commons.lang3.time.*" %>

<%
  List<Category> categories = (List<Category>)request.getAttribute("categories");
%>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <jsp:include page="../partial/head.jsp" flush="true">
    <jsp:param name="title" value="新增文章" />
  </jsp:include>
  <script src="http://cdn.bootcss.com/ckeditor/4.3.2/ckeditor.js"></script>
</head>
<body>
  <jsp:include page="../partial/header.jsp" flush="true" />
  <jsp:include page="../partial/nav.jsp" flush="true" />
  <div class="container" style="margin-top: 20px">
    <div class="row">
      <div class="col-md-3">
        <jsp:include page="partial/leftsidebar.jsp" flush="true" />
      </div>
      <div class="col-md-9">
        <form id="new-post-form" enctype="multipart/form-data" action="/admin/archive" method="post">
          <div class="form-group">
            <label for="title">标题</label>
            <input name="title" type="text" class="form-control">
          </div>
          <div class="form-group">
            <label for="author">作者</label>
            <input name="author" type="text" class="form-control">
          </div>
          <div class="form-group">
            <label for="categoryId">分类</label>
            <select name="categoryId" class="form-control">
              <% if (categories != null) { %>
                <% for (Category c : categories) { %>
                  <option value="<%= c.getId() %>"><%= c.getName() %></option>
                <% } %>
              <% } %>
						</select>
          </div>
          <div class="form-group">
            <label for="thumbnail">缩略图</label>
            <input type="file" name="thumbnail">
          </div>
          <div class="form-group">
            <label for="html">内容</label>
            <textarea id="html" name="html" class="form-control" rows="10"></textarea>
          </div>
          <div class="form-group">
            <button type="submit" class="btn btn-default">保存</button>
          </div>
        </form>
      </div>
    </div>
  </div>
  <jsp:include page="../partial/footer.jsp" flush="true" />
  <script>
    $("#admin-archive-li").addClass("active");
    CKEDITOR.replace("html",{
      toolbar:"Basic",
      uiColor:"#9AB8F3",
      filebrowserUploadUrl: "/api/upload/media"
    });
  </script>
</body>
</html>