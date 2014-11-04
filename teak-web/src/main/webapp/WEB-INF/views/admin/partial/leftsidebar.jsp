<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 

<ul class="nav nav-pills nav-stacked">
  <li id="admin-archive-li"><a href="<%= request.getContextPath() %>/admin/archive">文章管理</a></li>
  <li id="admin-category-li"><a href="<%= request.getContextPath() %>/admin/category">分类管理</a></li>
  <li id="admin-page-li"><a href="<%= request.getContextPath() %>/admin/page">页面管理</a></li>
  <li id="admin-link-li"><a href="<%= request.getContextPath() %>/admin/link">链接管理</a></li>
<!--  <li id="admin-user-li"><a href="/admin/user">用户管理</a></li>-->
</ul>