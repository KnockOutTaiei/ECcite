<%/*
ヘッダー
①session のログイン情報よりログイン名
②画面表示日時（yyyy/mm/dd hh:mm:ss）
③マイアカウントのリンク
クリックすると、マイアカウント画面へ遷移する。
尚、下記画面は除外する。
●商品詳細画面ポップアップならたしかに除外しなければならない
④カートのリンク
クリックすると、カート画面へ遷移する。
尚、下記画面は除外する。
●ログイン画面
●アカウント作成画面
●カート画面
●購入完了画面

くわえて、システムメッセージもヘッダーに入れ込んでしまう（独断,あとで相談）

システム
*/%>
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.time.*" %>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%Account account = (Account)session.getAttribute("account"); %>
<%SystemMessage systemMessage = (SystemMessage)session.getAttribute("systemMessage"); %>
<!-- 戻るボタン -->
<button type="button" onclick="history.back()">戻る</button>
<!-- ①session のログイン情報よりログイン名 -->
<% if(account != null){%>
<div><%=account.getLogin_name()%>さん、ようこそ。</div>
<%}else{ %>
<div>ログインは<a href="login.jsp">こちら</a></div>
<%} %>
<!-- ②画面表示日時（yyyy/mm/dd hh:mm:ss） -->
<%LocalDateTime localdatetime = LocalDateTime.now(); %>
<%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");%>
<% String timeStr = localdatetime.format(formatter);%>
<div><%=timeStr%></div>
<div>
<!-- ③マイアカウントのリンク -->
<a href="myaccount.jsp"><img src="image/人物アイコン.png" alt="アカウント"></a>
<!-- ④カートのリンク -->
<a href="cart.jsp"><img src="image/ショッピングカートのアイコン12.png" alt="カート"></a>
</div>
<!-- システムメッセージ -->
<div>
<%if(systemMessage!=null){ %>
<%=systemMessage.getMessage() %>
<%} %>
</div>