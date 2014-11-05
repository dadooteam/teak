<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.teak.data.po.*,org.apache.commons.lang3.time.*" %>

<%
  Archive archive = (Archive)request.getAttribute("archive");
%>

<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="中国当代写作研究中心">
  <jsp:include page="partial/head.jsp" flush="true">
    <jsp:param name="title" value="<%= archive.getTitle() %>" />
  </jsp:include>
</head>
<body>
  <jsp:include page="partial/header.jsp" flush="true" />
  <jsp:include page="partial/nav.jsp" flush="true" />
  <div class="container">
    <div class="row">
      <div class="col-md-8">
        <h2 style="font-weight:bold"><%= archive.getTitle() %></h2>
        <small style="margin:15px 0px;color:#666">
          发布时间:<%= DateFormatUtils.format(archive.getPublishDatetime(), "yyyy-MM-dd HH:mm",TimeZone.getTimeZone("GMT+8")) %>&nbsp;
          作者:<%= archive.getAuthor() %>&nbsp;
          浏览次数:<%= archive.getClick() %>
        </small>
        <% if(archive.getThumbnailPath() != null) { %>
        <div class="thumbnail-wrapper">
          <img src="<%= archive.getThumbnailPath() %>" width="400px">
        </div>
        <% } %>
        <div style="margin-top:30px"><%= archive.getHtml() %></div>
      </div>
      <div class="col-md-4">
        
      </div>
    </div>
  </div>
  <jsp:include page="partial/footer.jsp" flush="true" />
</body>
</html>