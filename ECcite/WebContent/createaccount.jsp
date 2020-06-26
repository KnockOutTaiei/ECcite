<!-- アカウント作成画面
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
.table1 {
  border: 1px solid indigo;
}
.table1 th, .table1 td {
  border: 1px solid gray;
}
.table2 {
  border: 1px solid indigo;
}
.table2 th, .table2 td {
  border: 1px solid blue;
}
</style>
<script style = "text/javascript">
</script>
<title>アカウント作成</title>
</head>
<body>
<h1>アカウント作成</h1>
<%if(systemMessage!=null){ %>
<%=systemMessage.getMessage() %>
<%} %>
<form action="CreateAccount" method="post"><!-- /をつけないとプロジェクト直下から、たいして/をつけると鯖直下から -->
<p>
ログイン名　　<input type="text" name="login_name">
ログインID　　<input type="text" name="acc_id">
</p>
<p>
氏名　　　　　<input type="text" name="fullname">
性別　　　　　<select name="gender">
<option value="O">そのほか</option>
<option value="M">男性</option>
<option value="F">女性</option>
</select>
</p>
<p>
メールアドレス<input type="text" name="mail">
電話番号　　　<input type="text" name="tel_no">
</p>
<p>
郵便番号　　　<input type="text" name="zip">
都道府県　　　<input type="text" name="prefecture">
</p>
<p>
市区町村　　　<input type="text" name="city">
</p>
<p>
住所　　　　　<input type="text" name="address">
</p>
<p>
パスワード　　<input type="password" name="login_pw">

<input type="submit" value="作成">
</p>

</form>
</body>
</html>