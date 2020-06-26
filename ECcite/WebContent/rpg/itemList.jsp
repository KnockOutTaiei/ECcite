<!-- 商品リスト画面
商品を選ぶと詳細ページへ飛ぶ
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%//header.jsp%>
<%@ page import="model.*"%>
<%@ page import="java.time.*" %>
<%@ page import="java.time.format.DateTimeFormatter"%>
<% ItemList itemList = (ItemList)session.getAttribute("itemList");%>
<% NowPage nowPage = (NowPage)session.getAttribute("nowPage");%>
<%Account account = (Account)session.getAttribute("account"); %>
<%SystemMessage systemMessage = (SystemMessage)session.getAttribute("systemMessage"); %>
<!DOCTYPE html>
<html>
<head>
<title>商品リスト</title>
<link rel="stylesheet" type="text/css" href="../css/gameWindow.css" />
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
        <p>なににする？</p>
        <%//header.jsp%><p><%if(systemMessage!=null){ %><%=systemMessage.getMessage() %><%} %></p>
      </div>
    </div>
  </div>
  <div class="downerzone">
  <img src="../image/downerzone.png" alt="textbox">
    <div class="downerzone textbox">
      <form action="ItemSearchController" method="post">
        <input type="text" name="textSearch">
        <select name="categorySearch">
      	  <%for(int i=1;i<=2;i++){%>
          <option value="<%=i%>"><%if(i==1){%>槍<%}else if(i==2){ %>銃<%} %></option>
          <%} %>
        </select>
        <select name="nowPage">
        <%for(int i=1;i<=2;i++){%>
          <option value="<%=i%>"><%=i%>ページ目</option>
        <%} %>
        </select>
        <input type="submit" value="検索">
      </form>
      <%if(itemList.getItemList().size()==0){%>
        <p>検索結果がありません</p>
      <%}else{%>
        <div class="itemList">
        <%int i = 1;%>
        <%for(Item it: itemList){  %>
          <%if(!(i>(nowPage.getCount() - 1)*10 && i<=(nowPage.getCount())*10)){i++;continue;} %>
          <%//4+4+2 %>
          <div class="item">
            <p><%=it.getProduct_name()%>  \<%=it.getProduct_price()%></p>
            <p><a href="Detail?itemID=<%=it.getProduct_id()%>">詳細</a></p>
          </div>
          <%if(i==4 || i==8){%></div><div class="itemList"><%}%><%//Row Reset%>
          <%if(i==nowPage.getCount()*10)break;i++; %>
        <%}%>
        </div>
      <%}%>
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
      <a href="History">もちもの</a>
    </div>
  </div>
</body>
</html>