<%@page import="im.dadoo.teak.web.util.PaginationUtil"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="im.dadoo.teak.web.vo.PaginationVO"%>
<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 

<%
  PaginationVO paginationVO = (PaginationVO)request.getAttribute("paginationVO");
	String url = paginationVO.getUrl();
	long cur = paginationVO.getCur();
	long max = paginationVO.getMax();
%>

<ul class="pagination">
  <% if (cur > 1) { %>
  	<li><span><a href="?<%= url.replace(PaginationUtil.TPL, "pagecount=" + (cur - 1)) %>">&laquo;</a></span></li>
  <% } %>
  <% for (int i = 1; i <= max; i++) { %>
    <% if (i == cur) { %>
      <li class="active"><span><%= i %></span></li>
    <% } else { %>
    	<li><span><a href="?<%= url.replace(PaginationUtil.TPL, "pagecount=" + i) %>"><%= i %></a></span></li>
    <% } %>
  <% } %>
  <% if (cur < max) { %>
  	<li><span><a href="?<%= url.replace(PaginationUtil.TPL, "pagecount=" + (cur + 1)) %>">&raquo;</a></span></li>
  <% } %>
</ul>