<!-- アカウント画面
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="content-language" content="ja">
<meta name="keywords" content="">
<meta name="description" content="">
<style type="text/css">
.warning {
  color:red;
  font-size:18px;
}
</style>
<script style = "text/javascript">
</script>
<title>アカウント管理</title>
</head>
<body>
<h1>アカウント管理画面</h1>
<%@include file="header.jsp"%>
<form action="MyAccountController" method="post"><!-- /をつけないとプロジェクト直下から、たいして/をつけると鯖直下から -->
<p>
ログイン名　　<input type="text" name="login_name" value="<%=account.getLogin_name() %>">
ログインID　　<input type="text" name="acc_id" value="<%=account.getAcc_id() %>">
</p>
<p>
氏名　　　　　<input type="text" name="fullname" value="<%=account.getFullname() %>">
性別　　　　　<select name="gender" >
<option value="O">そのほか</option>
<option value="M">男性</option>
<option value="F">女性</option>
</select>
</p>
<p>
メールアドレス<input type="text" name="mail" value="<%=account.getMail() %>">
電話番号　　　<input type="text" name="tel_no" value="<%=account.getTel_no() %>">
</p>
<p>
郵便番号　　　<input type="text" name="zip" value="<%=account.getZip() %>">
都道府県　　　<input type="text" name="prefecture" value="<%=account.getPrefecture() %>">
</p>
<p>
市区町村　　　<input type="text" name="city" value="<%=account.getCity() %>">
</p>
<p>
住所　　　　　<input type="text" name="address" value="<%=account.getAddress() %>">
</p>
<p>
パスワード　　<input type="password" name="login_pw" value=""><%//accountインスタンスにはパスワードは入れていない意図的に %>

<input type="submit" value="更新">
</p>
</form>


<form action="MyAccountController" method="get">
<p>アカウントを削除します</p>
<p class ="warning">もとに戻せません</p>
<p>
<input type="submit" value="アカウントを削除します">
</p>
</form>


</body>
</html>
<!-- 行ぞろえしたいんならcssから -->