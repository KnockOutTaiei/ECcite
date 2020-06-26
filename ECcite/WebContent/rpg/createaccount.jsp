<!-- アカウント作成画面
 -->
<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ page import="model.SystemMessage" %>
<%SystemMessage systemMessage = (SystemMessage)session.getAttribute("systemMessage"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/gameWindow.css" />
<title>アカウント作成</title>
</head>
<body class="gameWindow">
<script>
var receivedData;

function insertAddress () {
	var elemPref = document.getElementById("prefecture");
	elemPref.value = receivedData.data.pref;
	var elemCity = document.getElementById("city");
	elemCity.value = receivedData.data.city;
	var elemTown = document.getElementById("town");
	elemTown.value = receivedData.data.town;
}

function getJSON(zipcode){
	// XMLHttpRequestオブジェクトの作成
	var request = new XMLHttpRequest();

	// URLを開く
	var url = "https://api.zipaddress.net/?zipcode=" + zipcode;
	request.open('GET', url, true);
	request.responseType = 'json';

	// レスポンスが返ってきた時の処理を記述
	request.onload = function () {
	  receivedData = this.response;
	  insertAddress ();
	}

	// リクエストをURLに送信
	request.send();
}

document.body.onload = getJSON("");
</script>
<img src="../image/shopBackground.png" alt="shopBackground">
<div class="upperzone">
  <div class="upperzone portrait">
   <img src="../image/master.png" alt="master">
  </div>
  <div class="upperzone textzone">
    <img src="../image/textzone.png" alt="textzone">
    <div class="upperzone textzone textbox">
      <p>アカウントをつくるのね。</p>
      <p>あなたのことをおしえて？</p>
      <p><%if(systemMessage!=null){ %><%=systemMessage.getMessage() %><%} %></p>
    </div>
  </div>
</div>
<div class="downerzone">
<img src="../image/downerzone.png" alt="textbox">
  <div class="downerzone textbox">
    <form action="CreateAccount" method="post"><!-- /をつけないとプロジェクト直下から、たいして/をつけると鯖直下から -->
    <p>ログイン名　　<input type="text" name="login_name">　ログインID　　<input type="text" name="acc_id"></p>
    <p>氏名　　　　　<input type="text" name="fullname">　性別　　　　　<select name="gender"><option value="O">そのほか</option><option value="M">男性</option><option value="F">女性</option></select></p>
    <p>メールアドレス<input type="text" name="mail">　電話番号　　　<input type="text" name="tel_no"></p>
    <p>郵便番号　　　<input type="text" name="zip" onchange="searchAddress()" id="zip">　都道府県　　　<input type="text" name="prefecture" id="prefecture"></p>
    <p>市区町村　　　<input type="text" name="city" id="city"></p>
    <p>住所　　　　　<input type="text" name="address" id="town"></p>
    <p>パスワード　　<input type="password" name="login_pw"></p>
    <input type="submit" value="作成">
    </form>
    <script>
    function searchAddress(){
    	var elemZip = document.getElementById("zip");
    	getJSON(elemZip.value);
    }
    </script>
  </div>
</div>
</body>
</html>