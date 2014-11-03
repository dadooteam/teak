<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.teak.data.po.*,org.apache.commons.lang3.time.*" %>

<%
  Category category = (Category)request.getAttribute("category");
  List<Archive> archives = (List<Archive>)request.getAttribute("archives");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="中国当代写作研究中心">
  <jsp:include page="partial/head.jsp" flush="true">
    <jsp:param name="title" value="<%= category.getName() %>" />
  </jsp:include>
</head>
<body>
  <jsp:include page="partial/header.jsp" flush="true" />
  <jsp:include page="partial/nav.jsp" flush="true" />
  <div class="container">
    <div class="row">
      <div class="col-md-8">
        <div class="board" style="border:1px solid #eee;margin-top: 30px">
          <div class="board-head" style="background:#eee;padding:1px 0px">
            <button class="btn btn-success" disabled="disabled"><%= category.getName() %></button>
          </div>
          <div class="board-body">
            <ul style="list-style-type: none;padding:0px 10px">
              <% if (archives != null) { %>
                <% for (Archive archive : archives) { %>
                  <li style="border-bottom:1px solid #eee;padding:5px">
                    <h3 style="font-weight:bold"><a href="archive/<%= archive.getId() %>"><%= archive.getTitle() %></a></h3>
                    <small style="color:#666 "><%= archive.getAuthor() %>&nbsp;发布于&nbsp;<%= DateFormatUtils.format(archive.getPublishDatetime(), "yyyy-MM-dd HH:mm",TimeZone.getTimeZone("GMT+8")) %>&nbsp;<%= archive.getClick() %>浏览</small>
                    <div style="margin:20px 0px">
                      <% if(archive.getText().length() < 230) { %>
                        <%= archive.getText() %>
                      <% } else { %>
                        <%= archive.getText().substring(0, 230) %>...
                      <% } %>
                    </div>
                    <div><a href="archive/<%= archive.getId() %>">显示全文</a></div>
                  </li>
                <% } %>
              <% } %>
            </ul>
          </div>
        </div>
        <jsp:include page="partial/pagination.jsp" flush="true" />
      </div>
      <div class="col-md-4">
        
      </div>
    </div>
  </div>
  <jsp:include page="partial/footer.jsp" flush="true" />
</body>
</html>