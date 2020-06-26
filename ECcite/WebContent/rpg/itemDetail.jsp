<!-- 商品詳細画面
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.time.*" %>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.Random" %>
<% ItemList itemList = (ItemList)session.getAttribute("itemList");%>
<% NowPage nowPage = (NowPage)session.getAttribute("nowPage");%>
<%Account account = (Account)session.getAttribute("account"); %>
<%SystemMessage systemMessage = (SystemMessage)session.getAttribute("systemMessage"); %>
<% Item theItem = (Item)session.getAttribute("theItem");%>

<!DOCTYPE html>
<html>
<head>
<title>商品詳細</title>
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
        <%Random random = new Random();int randomNum = (random.nextInt()%3);%>
        <%String line = new String();%>
        <%switch(randomNum){
          case 0:line="さすが、おめがたかい！";break;
          case 1:line="とくとごらんあれ。";break;
          case 2:line="こういうアイテムだよ！";break;
          default:line="こんなアイテムだよ！";break;
        }%>
        <p><%=line%></p>
        <p><%if(systemMessage!=null){ %><%=systemMessage.getMessage() %><%} %></p>
      </div>
    </div>
  </div>
  <div class="downerzone">
  <img src="../image/downerzone.png" alt="textbox">
    <div class="downerzone textbox">
      <div><%=theItem.getProduct_name()%></div>
      <div>\<%=theItem.getProduct_price()%></div>
      <div><%=theItem.getProduct_detail() %></div>
      <form action="Cart?product_id=<%=theItem.getProduct_id()%>" method="post">
      <select name="stockInCart">
  	  <%for(int i = 1;i <= theItem.getStock() ;i++){ %>
      <option value="<%=i%>">個数：<%=i%></option>
      <%} %>
      </select>
      <input type="submit" value="カートへ">
      </form>
      <p><a href="itemList.jsp">買い物を続ける</a></p>
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