<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@page import="java.util.*,im.dadoo.teak.data.po.*,org.apache.commons.lang3.time.*" %>

<%
  List<CategoryPO> categoryNav = (List<CategoryPO>)request.getAttribute("categoryNav");
  List<PagePO> pageNav = (List<PagePO>)request.getAttribute("pageNav");
%>

<style>
  .navbar-inverse .navbar-nav li a {
    color:white;
    font-weight: bold;
  }
</style>
<div class="navbar navbar-inverse" role="navigation" style="background-color: #368ee0;border-color:transparent">
  <div class="container">
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="<%=request.getContextPath()%>/">首页</a></li>
        <%
          if (categoryNav != null) {
        %>
          <%
            for (CategoryPO category : categoryNav) {
          %>
            <li><a href="<%=request.getContextPath()%>/category/<%=category.getId()%>"><%=category.getName()%></a></li>
          <%
            }
          %>
        <%
          }
        %>
        <%
          if (pageNav != null) {
        %>
          <%
            for (PagePO p : pageNav) {
          %>
            <li><a href="<%= request.getContextPath() %>/page/<%= p.getId() %>"><%= p.getName() %></a></li>
          <% } %>
        <% } %>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</div>