<%@page import="im.dadoo.teak.web.vo.PaginationVO"%>
<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 

<%
  PaginationVO paginationVO = (PaginationVO)request.getAttribute("paginationVO");
	String url = paginationVO.getUrl();
	int cur = paginationVO.getCur();
	int max = paginationVO.getMax();
%>

<%= url %>
<ul class="pagination">
  <% if (cur > 1) { %>
  	<li><span><a href="<%= String.format(url, cur - 1) %>">&laquo;</a></span></li>
  <% } %>
  <% for (int i = 1; i <= max; i++) { %>
    <% if (i == cur) { %>
      <li class="active"><span><%= i %></span></li>
    <% } else { %>
    	<li><span><a href="<%= String.format(url, i) %>"><%= i %></a></span></li>
    <% } %>
  <% } %>
  <% if (cur < max) { %>
  	<li><span><a href="<%= String.format(url, cur + 1) %>">&raquo;</a></span></li>
  <% } %>
</ul>