<!-- 商品リスト画面
商品を選ぶと詳細ページへ飛ぶ
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="model.*"%>
<% ItemList itemList = (ItemList)session.getAttribute("itemList");%>
<% NowPage nowPage = (NowPage)session.getAttribute("nowPage");%>
<%int j=0; %>
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
</style>
<script style = "text/javascript">
</script>
<title>商品リスト</title>
</head>
<body>
<%@include file="header.jsp"%>
<div>
<form action="ItemSearchController" method="post">
  <input type="text" name="textSearch">
  <select name="categorySearch">
  	<%for(int i=1;i<=2;i++){%>
    <option value="<%=i%>">かてごり<%=i%></option>
    <%} %>
  </select>
  <select name="nowPage">
  	<%for(int i=1;i<=2;i++){%>
    <option value="<%=i%>"><%=i%>ページ目</option>
    <%} %>
  </select>
  <input type="submit" value="検索">
</form>
</div>
<%if(itemList.getItemList().size()==0){%>
<div>
<p>検索結果がありません</p>
</div>
<%}else{%>
  <%int i = 1;%>
  <%for(Item it: itemList){  %>
    <%if(!(i>(nowPage.getCount() - 1)*10 && i<=(nowPage.getCount())*10)){i++;continue;} %>
    <div class="relative">
      <div><%=it.getProduct_name()%></div>
      <div>\<%=it.getProduct_price()%></div>
      <a href="Detail?itemID=<%=it.getProduct_id()%>">詳細</a>
    </div>
    <%if(i==nowPage.getCount()*10)break;i++; %>
  <%}%>
<%}%>
</body>
</html>