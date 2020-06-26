<!-- ログイン画面
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="model.SystemMessage" %>
<%SystemMessage systemMessage = (SystemMessage)session.getAttribute("systemMessage"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="content-language" content="ja">
<meta name="keywords" content="">
<meta name="description" content="">
<style type="text/css">
</style>
<script style = "text/javascript">
</script>
<title>ログイン</title>
</head>
<body>
<h1>ログイン</h1>
<%if(systemMessage!=null){ %>
<%=systemMessage.getMessage() %>
<%} %>
<form action="Login" method="post"><!-- /をつけないとプロジェクト直下から、たいして/をつけると鯖直下から -->
<p>
ログインID　<input type="text" name="acc_id">
</p>
<p>
パスワード　<input type="password" name="login_pw">
<input type="submit" value="ログイン">
</p>
</form>
<div>
<form action="createaccount.jsp" method="get">
<input type="submit" value="アカウントを作成する">
</form>
</div>
</body>
</html>