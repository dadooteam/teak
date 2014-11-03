<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.teak.data.po.*,org.apache.commons.lang3.time.*" %>

<%
  Category category = (Category)request.getAttribute("category");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <jsp:include page="../partial/head.jsp" flush="true">
    <jsp:param name="title" value="修改分类" />
  </jsp:include>
</head>
<body>
  <jsp:include page="../partial/header.jsp" flush="true" />
  <jsp:include page="../partial/nav.jsp" flush="true" />
  <div class="container" style="margin-top: 20px">
    <div class="row">
      <div class="col-md-3">
        <jsp:include page="partial/leftsidebar.jsp" flush="true" />
      </div>
      <div class="col-md-6">
        <form id="update-category-form" action="/admin/category/<%= category.getId() %>/update" method="post">
          <div class="form-group">
            <label for="name">名称</label>
            <input id="name" name="name" type="text" class="form-control" value="<%= category.getName() %>">
          </div>
          <div class="form-group">
            <label for="description">描述</label>
            <input id="description" name="description" type="text" class="form-control" value="<%= category.getDescription() %>">
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
    $("#admin-category-li").addClass("active");
  </script>
</body>
</html>