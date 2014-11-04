<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.teak.data.po.*,org.apache.commons.lang3.time.*" %>

<%
  List<Archive> archives = (List<Archive>)request.getAttribute("archives");
  Map<Integer, Category> categoryMap = (Map<Integer, Category>)request.getAttribute("categoryMap");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <jsp:include page="../partial/head.jsp" flush="true">
    <jsp:param name="title" value="文章管理" />
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
      <div class="col-md-9">
        <a class="btn btn-primary pull-right" href="<%= request.getContextPath() %>/admin/archive/add">新文章</a>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>标题</th>
              <th>作者</th>
              <th>分类</th>
              <th>点击量</th>
              <th>发布时间</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <% if (archives != null) { %>
              <% for (Archive ar : archives) { %>
              <tr>
                <td><a href="<%= request.getContextPath() %>/archive/<%= ar.getId() %>"><%= ar.getTitle() %></a></td>
                <td><%= ar.getAuthor() %></td>
                <td><%= categoryMap.get(ar.getCategoryId()).getName() %></td>
                <td><%= ar.getClick() %></td>
                <td><%= DateFormatUtils.format(ar.getPublishDatetime(), "yyyy-MM-dd HH:mm",TimeZone.getTimeZone("GMT+8")) %></td>
                <td>
                  <a href="<%= request.getContextPath() %>/admin/archive/<%= ar.getId() %>/update">修改</a>
                  <a href="<%= request.getContextPath() %>/admin/archive/<%= ar.getId() %>/delete">删除</a>
                </td>
              </tr>
              <% } %>
            <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <jsp:include page="../partial/footer.jsp" flush="true" />
  <script>
    $("#admin-archive-li").addClass("active");
  </script>
</body>
</html>