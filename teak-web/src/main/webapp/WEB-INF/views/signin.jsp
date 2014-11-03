<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.teak.data.po.*,org.apache.commons.lang3.time.*" %>

<!DOCTYPE html>
<html>
<head>
  <jsp:include page="partial/head.jsp" flush="true">
    <jsp:param name="title" value="登录" />
  </jsp:include>
</head>
<body>
  <jsp:include page="partial/header.jsp" flush="true" />
  <jsp:include page="partial/nav.jsp" flush="true" />
  
  <div class="container">
    <div class="row">
      <div class="col-md-8">
        <form id="signin-form" action="/signin" method="post">
          <div class="form-group">
            <label for="name">用户名</label>
            <input id="name" name="name" type="text" class="form-control">
          </div>
          <div class="form-group">
            <label for="password">密码</label>
            <input id="password" name="password" type="password" class="form-control">
          </div>
          <div class="form-group">
            <button type="submit" class="btn btn-default">保存</button>
          </div>
        </form>
      </div>
      <div class="col-md-4">
        
      </div>
    </div>
  </div>
  <jsp:include page="partial/footer.jsp" flush="true" />
</body>
</html>