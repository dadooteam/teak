<%@page language="java" contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %> 
<%@page import="java.util.*,im.dadoo.teak.data.po.*,im.dadoo.teak.web.constant.*,org.apache.commons.lang3.time.*" %>

<%
  UserPO visitor = (UserPO)session.getAttribute(Cons.VISITOR);
%>
<div class="footer" style="background-color: #444444;color:white">
  <div class="container">
    <div class="row">
      <div class="col-md-2 col-md-offset-2">
        <h3><span class="glyphicon glyphicon-cog"></span>管理</h3>
        <br>
        <ul>
          <li><span class="glyphicon glyphicon-expand"></span><a href="<%= request.getContextPath() %>/admin">管理员入口</a></li>
          <% if (visitor != null) { %>
            <li><span class="glyphicon glyphicon-expand"></span><a href="<%= request.getContextPath() %>/signout">退出</a></li>
          <% } %>
        </ul>
      </div>
      <div class="col-md-3">
        <h3><span class="glyphicon glyphicon-facetime-video"></span>关于我们</h3>
        <br>
        <ul>
          <li>
            <h4>地址</h4>
            <span class="glyphicon glyphicon-home"></span>湖北省武汉市珞喻路1037号华中科技大学东五楼三楼
          </li>
          <li>
            <span class="glyphicon glyphicon-expand"></span>430074
          </li>
          <br>
          <li>
            <span class="glyphicon glyphicon-earphone"></span>(027)87559614 
          </li>
          <li>
            <span class="glyphicon glyphicon-envelope"></span>
            <a href="mailto:jiangjun1870@126.com">jiangjun1870@126.com</a>
          </li>
        </ul>
      </div>
      <div class="col-md-3">
        <h3><span class="glyphicon glyphicon-eye-open"></span>版权所有</h3>
        <br>
        <ul>
          <li>&copy;华中科技大学中国当代写作研究中心&nbsp;2012</li>
          <br>
          <li><span class="glyphicon glyphicon-fire"></span>Designed By <a href="#">Dadoo Team</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>