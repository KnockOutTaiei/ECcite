<!-- 購入完了画面
買い物を続けると商品リスト画面へ
ログアウトならログアウト画面へ
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%//header.jsp%>
<%@ page import="model.*"%>
<%@ page import="java.time.*" %>
<%@ page import="java.time.format.DateTimeFormatter"%>
<% ItemList itemList = (ItemList)session.getAttribute("itemList");%>
<% NowPage nowPage = (NowPage)session.getAttribute("nowPage");%>
<%Account account = (Account)session.getAttribute("account"); %>
<% Cart cart = (Cart)session.getAttribute("cart");%>
<%SystemMessage systemMessage = (SystemMessage)session.getAttribute("systemMessage"); %>
<!DOCTYPE html>
<html>
<head>
<title>カートの中の商品リスト</title>
<link rel="stylesheet" type="text/css" href="../css/gameWindow.css" />
<style>
.bold{
  font-size:24px;
}
</style>
</head>
<body class="gameWindow">
  <img src="../image/shopBackground.png" alt="shopBackground">
  <div class="upperzone">
    <div class="upperzone portrait">
     <img src="../image/master_completed.png" alt="master">
    </div>
    <div class="upperzone textzone">
      <img src="../image/textzone.png" alt="textzone">
      <div class="upperzone textzone textbox">
        <p>まいどあり！</p>
        <p><%if(systemMessage!=null){ %><%=systemMessage.getMessage() %><%} %></p>
      </div>
    </div>
  </div>
  <div class="downerzone">
  <img src="../image/downerzone.png" alt="textbox">
    <div class="downerzone textbox">
      <p><a href="itemList.jsp">買い物を続ける</a></p>
      <p><a href="Logout">ログイン画面へ戻る</a></p>
      <%//旧ヘッダー部分%>
      <% if(account != null){%>
      <div><%=account.getLogin_name()%>さん、ようこそ。</div>
      <%}else{ %>
      <div>ログインは<a href="login.jsp">こちら</a></div>
      <%} %>
      <button type="button" onclick="history.back()">戻る</button>
      <%LocalDateTime localdatetime = LocalDateTime.now(); %>
      <%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");%>
      <% String timeStr = localdatetime.format(formatter);%>
      <div><%=timeStr%></div>
      <a href="myaccount.jsp">アカウント</a>
      <a href="cart.jsp">カート</a>
    </div>
  </div>
</body>
</html>