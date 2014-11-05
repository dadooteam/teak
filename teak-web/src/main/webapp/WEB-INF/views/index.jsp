<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.teak.data.po.*,org.apache.commons.lang3.time.*" %>

<%
  List<Archive> imageArchives = (List<Archive>)request.getAttribute("imageArchives");
  List<Archive> latestArchives = (List<Archive>)request.getAttribute("latestArchives");
  Page introductionPage = (Page)request.getAttribute("introductionPage");
  List<Link> links = (List<Link>)request.getAttribute("links");
%>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
  <meta name="description" content="中国当代写作研究中心">
  <jsp:include page="partial/head.jsp" flush="true">
    <jsp:param name="title" value="主页" />
  </jsp:include>
</head>
<body>
  <jsp:include page="partial/header.jsp" flush="true" />
  <jsp:include page="partial/nav.jsp" flush="true" />
  <div class="container">
    <div class="row">
      <div class="col-md-8">
        <div class="board">
          <div class="board-head" style="padding:1px 0px;border-bottom:3px solid #3DBAF4 ">
            <h3>
              <a href="<%= request.getContextPath() %>/category/4" style="color:#3DBAF4">图片新闻</a>
            </h3>
          </div>
          <div class="board-body">
            <div id="teak-carousel" class="carousel slide" data-ride="carousel">
              <div class="carousel-inner" style="text-align:center">
                <% for (Archive archive : imageArchives) { %>
                  <div class="item">
                    <div>
                      <img src="<%= archive.getThumbnailPath() %>" style="width:400px;height:250px">
                    </div>
                    <div class="carousel-caption" style="left:0%;right:0%;padding-bottom:0px">
                      <h3><a href="<%= request.getContextPath() %>/archive/<%= archive.getId() %>"><%= archive.getTitle() %></a></h3>
                      <div style="color:#3DBAF4"><%= archive.getText() %></div>
                    </div>
                  </div>
                <% } %>
              </div>
              <a class="left carousel-control" href="#teak-carousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
              </a>
              <a class="right carousel-control" href="#teak-carousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
              </a>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="board">
          <div class="board-head" style="padding:1px 0px;border-bottom: 3px solid #58B329">
            <h3>
              <a href="<%= request.getContextPath() %>/page/1" style="color:#58B329">中心介绍</a>
            </h3>
          </div>
          <div class="board-body">
            <div style="margin:20px 10px;color:#58B329">
              <%= introductionPage.getText().substring(0, 265) %>...<a href="<%= request.getContextPath() %>/page/<%= introductionPage.getId() %>">显示全文</a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-8">
        <div class="board">
          <div class="board-head" style="padding:1px 0px;border-bottom:3px solid #FF8F3F">
            <h3 style="color:#FF8F3F">
              最新动态
            </h3>
          </div>
          <div class="board-body">
            <div style="margin:20px 0px">
              <table>
                <tbody>
                  <% for (Archive archive : latestArchives) { %>
                  <tr style="border-bottom: 1px dotted #b7b7b7">
                    <td class="col-md-6"><img src="<%= request.getContextPath() %>/resources/img/list-item.gif" style="margin-right:10px"><a href="<%= request.getContextPath() %>/archive/<%= archive.getId() %>" style="color:#FF8F3F"><%= archive.getTitle() %></a></td>
                    <td class="col-md-1"><%= DateFormatUtils.format(archive.getPublishDatetime(), "MM-dd",TimeZone.getTimeZone("GMT+8")) %></td>
                    <td class="col-md-1"><span class="badge pull-right"><%= archive.getClick() %></span></td>
                  </tr>
                  <% } %>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="board">
          <div class="board-head" style="padding:1px 0px;border-bottom: 3px solid #5F1885">
            <h3 style="color:#5F1885">
              友情链接
            </h3>
          </div>
          <div class="board-body">
            <div style="margin:20px 10px">
              <% for (Link link : links) { %>
                <h5><img src="<%= link.getUrl() %>/favicon.ico" height="15px" width="15px">&nbsp;<a href="<%= link.getUrl() %>" style="color:#5F1885"><%= link.getName() %></a></h5>
              <% } %>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <jsp:include page="partial/footer.jsp" flush="true" />
  <script>
    $(function() {
      $(".item:first").addClass("active");
    });
  </script>
</body>
</html>