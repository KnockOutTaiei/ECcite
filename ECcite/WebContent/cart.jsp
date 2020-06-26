<!-- カート画面
商品を選ぶと詳細ページへ飛ぶ
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ page import = "model.*"%>
<% Cart cart = (Cart)session.getAttribute("cart");%>

<%/*long calcConsumptionTax(long sum){double tax = 1.10;return (long)sum*tax;}//CAN'T...*/%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="content-language" content="ja">
<meta name="keywords" content="">
<meta name="description" content="">
<style type="text/css">
.relative {
  postion: relative;
  display:block;
  border: 1px solid indigo;
}
.bold{
  font-size:24px;
}
</style>
<script style = "text/javascript">
</script>
<title>カート中の商品リスト</title>
</head>
<body>
<%@include file="header.jsp"%>
<div>
カートに入っている商品です。
</div>
<%for(ItemSet itemSet: cart){  %>
<div class="relative">
<p><%=itemSet.getItem().getProduct_name()%></p>
<p>\<%=itemSet.getItem().getProduct_price()%></p>
<p>個数：<%=itemSet.getStockInCart()%></p>
<a href="Detail?itemID=<%=itemSet.getItem().getProduct_id()%>">詳細</a>
</div>
<%}%>
<div>
<p>うち消費税\<%=cart.calcConsumptionTax() %></p>
<p class="bold">合計金額\<%=cart.calcTotal() %></p>
</div>
<div>
<p><a href="buyConfirm.jsp">カートの商品を全て買う</a></p>
<p><a href="itemList.jsp">買い物を続ける</a></p>
</div>
</body>
</html>