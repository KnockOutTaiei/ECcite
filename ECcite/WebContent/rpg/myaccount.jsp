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
<title>アカウント管理画面</title>
<link rel="stylesheet" type="text/css" href="../css/gameWindow.css" />
<style type="text/css">
.warning {
  color:red;
  font-size:18px;
}
</style>
</head>
<body class="gameWindow">
<img src="../image/shopBackground.png" alt="shopBackground">
<div class="upperzone">
  <div class="upperzone portrait">
   <img src="../image/master.png" alt="master">
  </div>
  <div class="upperzone textzone">
    <img src="../image/textzone.png" alt="textzone">
    <div class="upperzone textzone textbox">
      <p>あなたのアカウントの情報よ！</p>
      <p><%if(systemMessage!=null){ %><%=systemMessage.getMessage() %><%} %></p>
    </div>
  </div>
</div>
<div class="downerzone">
<img src="../image/downerzone.png" alt="textbox">
  <div class="downerzone textbox">
    <form action="MyAccountController" method="post"><!-- /をつけないとプロジェクト直下から、たいして/をつけると鯖直下から -->
    <p>ログイン名　　<input type="text" name="login_name" value="<%=account.getLogin_name() %>">　ログインID　　<input type="text" name="acc_id" value="<%=account.getAcc_id() %>"></p>
    <p>氏名　　　　　<input type="text" name="fullname" value="<%=account.getLogin_name() %>">　性別　　　　　<select name="gender"><option value="O">そのほか</option><option value="M">男性</option><option value="F">女性</option></select></p>
    <p>メールアドレス<input type="text" name="mail" value="<%=account.getMail() %>">　電話番号　　　<input type="text" name="tel_no" value="<%=account.getTel_no() %>"></p>
    <p>郵便番号　　　<input type="text" name="zip" value="<%=account.getZip() %>">　都道府県　　　<input type="text" name="prefecture" value="<%=account.getPrefecture() %>"></p>
    <p>市区町村　　　<input type="text" name="city" value="<%=account.getCity() %>"></p>
    <p>住所　　　　　<input type="text" name="address" value="<%=account.getAddress() %>"></p>
    <p>パスワード　　<input type="password" name="login_pw" value=""></p><%//accountインスタンスにはパスワードは入れていない意図的に %>
    <input type="submit" value="更新">
    </form>
    <p class ="warning">もとに戻せません</p>
    <form action="MyAccountController" method="get">
    <input type="submit" value="アカウントを削除します">
    </form>
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
<%//つけないとrpgがルート、/をつけるとWebContentがルート%>