<!-- ログイン画面
最初に表示される画面で、現状何を入れてもログインできる
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="model.SystemMessage" %>
<%SystemMessage systemMessage = (SystemMessage)session.getAttribute("systemMessage"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="content-language" content="ja">

<link rel="stylesheet" type="text/css" href="../css/gameWindow.css" />

<title>ログイン</title>
</head>
<body class="gameWindow">
<img src="../image/shopBackground.png" alt="shopBackground">
<div class="upperzone">
  <div class="upperzone portrait">
   <img src="../image/master_entrance.png" alt="master">
  </div>
  <div class="upperzone textzone">
    <img src="../image/textzone.png" alt="textzone">
    <div class="upperzone textzone textbox">
      <p>アイテム屋へいらっしゃい！</p>
      <p>ログインIDと、パスワードをおしえて？</p>
      <p><%if(systemMessage!=null){ %><%=systemMessage.getMessage() %><%} %></p>
    </div>
  </div>
</div>
<div class="downerzone">
  <img src="../image/downerzone.png" alt="textbox">
  <div class="downerzone textbox">
    <form action="Login" method="post">
    <p>ログインID　<input type="text" name="acc_id"></p>
    <p>パスワード　<input type="password" name="login_pw"></p>
    <input type="submit" value="ログイン">
    </form>
    <form action="./createaccount.jsp" method="get">
    <input type="submit" value="アカウントを作成する">
    </form>
  </div>
</div>
</body>
</html>