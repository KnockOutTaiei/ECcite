<!-- 所持アイテム画面
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%//header.jsp%>
<%@ page import="model.*"%>
<%@ page import="java.time.*" %>
<%@ page import="java.time.format.DateTimeFormatter"%>
<% NowPage nowPage = (NowPage)session.getAttribute("nowPage");%>
<%Account account = (Account)session.getAttribute("account"); %>
<%History history = (History)session.getAttribute("history"); %>
<%SystemMessage systemMessage = (SystemMessage)session.getAttribute("systemMessage"); %>
<!DOCTYPE html>
<html>
<head>
<title>もちもの</title>
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
     <img src="../image/master.png" alt="master">
    </div>
    <div class="upperzone textzone">
      <img src="../image/textzone.png" alt="textzone">
      <div class="upperzone textzone textbox">
        <p>あなたが今もっていものをかくにんするのね。</p>
        <p><%if(systemMessage!=null){ %><%=systemMessage.getMessage() %><%} %></p>
      </div>
    </div>
  </div>
  <div class="downerzone">
  <img src="../image/downerzone.png" alt="textbox">
    <div class="downerzone textbox">

      <%if(history.getHistory().size()==0){%>
        <p>検索結果がありません</p>
      <%}else{%>
        <div class="itemList">
        <%int i = 1;%>
        <%for(EveryHistory everyHistory: history){%>
          <%if(!(i>(nowPage.getCount() - 1)*10 && i<=(nowPage.getCount())*10)){i++;continue;} %>
          <%//4+4+2 %>
          <div class="item">
            <p><%=everyHistory.getProduct_name()%></p>
            <p>個数：<%=everyHistory.getStock()%></p>
          </div>
          <%if(i==4 || i==8){%></div><div class="itemList"><%}%><%//Row Reset%>
          <%if(i==nowPage.getCount()*10)break;i++; %>
        <%}%>
        </div>
      <%}%>
      <div>
        <p><a href="itemList.jsp">買い物を続ける</a></p>
      </div>
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