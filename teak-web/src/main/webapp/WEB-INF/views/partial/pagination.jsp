<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 

<%
  Integer pagecount = (Integer)request.getAttribute("pagecount");
  Integer maxPagecount = (Integer)request.getAttribute("maxPagecount");
%>

<ul class="pagination">
  <% if (pagecount > 1) { %>
    <li><span><a href="?pagecount=<%= pagecount - 1 %>">&laquo;</a></span></li>
  <% } %>
  <% for (Integer i = 1; i <= maxPagecount; i++) { %>
    <% if (i.equals(pagecount)) { %>
      <li class="active"><span><%= i %></span></li>
    <% } else { %>
      <li><span><a href="?pagecount=<%= i %>"><%= i %></a></span></li>
    <% } %>
  <% } %>
  <% if (pagecount < maxPagecount) { %>
    <li><span><a href="?pagecount=<%= pagecount + 1 %>">&raquo;</a></span></li>
  <% } %>
</ul>