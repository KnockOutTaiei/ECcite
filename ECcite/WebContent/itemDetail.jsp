<!-- 商品詳細画面
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ page import = "model.Item"%>
<% Item theItem = (Item)session.getAttribute("theItem");%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="content-language" content="ja">
<meta name="keywords" content="">
<meta name="description" content="">
<style type="text/css">
.row{
	display:flex;

}
.relative {
  postion: relative;
  display:block;
  border: 1px solid indigo;
}
</style>
<script style = "text/javascript">
</script>
<title>商品詳細</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="row">
<img src="image/spear.png" alt="spear">
<div class="relative">
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
</div>
<div>
</div>
<p><a href="itemList.jsp">買い物を続ける</a></p>
</div>
</body>
</html>