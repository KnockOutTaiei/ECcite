package controller;
import model.*;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class ItemDetailController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		//get itemID as request parameter
		int id = Integer.parseInt(request.getParameter("itemID"));

		//looking for the item
		HttpSession session = request.getSession();
		ItemList itemList = (ItemList)session.getAttribute("itemList");
		Item theItem = new Item();
		for(Item it: itemList) {
			if(it.getProduct_id() == id) {
				theItem = it;
				break;
			}
		}

		//Save detail of the item to Session Scoup
		session.setAttribute("theItem", theItem);

		//Forward
		RequestDispatcher dispatcher = request.getRequestDispatcher("itemDetail.jsp");
		dispatcher.forward(request, response);
	}

	public ItemDetailController() {};
}
/*
 * save what to where: session scoup or request scoup
 * now, save itemList to session scoup == includes the detail although itemList.jsp doesn't use it
 *
 * request parameter uses "?"
 *
 *
 * 要件定義→設計
 * 画面遷移図、クラス図を書いても、設計になっとらん
 * もっと細かいレベルの設計
 * Item
 * ID
 * 名前
 * 値段
 * 説明書き
 * 写真（へのポインタ）
 *
 *
 * 要件定義
 * 要件定義とは、システムを開発する際にどんな新しいシステムを盛り込んでいくかを明確にしていきます。
それに対して、「予算はどのくらいなのか・人はどれくらい必要なのか・どのくらいの期間でやるのか」等を計画していきます。
☆具体的にはお客様にヒヤリングして、要件定義書・システム設計書を作成する
 * 例）「パスワード認証」「データベース内検索」などの機能要件
　　「入力データ」「出力データ」の仕様
　　「保守性」「操作性」などの非機能要件
要件定義書には「業務要件」と「システム要件（機能と非機能）」の２つを書けばよく
https://pm-rasinban.com/rd-write

設計
内部設計
外部設計

内部設計
「何を使って」→「どのような工程で」→「どのようなシステムを」
										の順番で設計していくイメージ。
「何を使って」・・開発方針を定めていく。
					具体的にはハードウェア・ソフトウェア構成など
「どのような工程で」・・開発体制、開発スケジュール、プロジェクト管理ツール
「どのようなシステムを」・・・システム構成図

外部設計
・画面遷移図・・このボタンを押したら、このページに遷移する等が書かれているもの
・画面定義書・・どこにどのボタンを作るなどの配置図
・データベース設計・・どんなテーブルを使い、どんなテーブルにするかを設計する


圧倒されているな。細かく具体的にできるその度合いがぱない。


人って困ったことに
一度話したことがある人や知ってる人のほうが圧倒的に話しやすいからなー

0512
アカウント登録でデータベースに登録ちゃんと入りました
jsp側を整え、fullnameとmailを受け取れるようにせよ
css　余白

0513
TODO:acc_idがvarchar(30)のままだ。名前、サイズともに変更
正規表現は「文字列の指定」と「回数」からなる
[\.]{1,8} -> ......
[a-zA-z0-9]? ->  or 3


20200514
昨日の（というか今日の）自分のツイート、なんか言葉が薄っぺらいな
言葉が貧弱なのか
自分の思い描く世界が貧弱なのか
おそらく両者であろうが、後者が特に原因としては強い気がする

acc_idをacc_cdにしろ→ああ、cはCharactor
三日ほどたってなんだcdって→そもそもIDってなんだ→IDentifierの略じゃん
このクズ、なんで気づかないのよ→あれ、なんでこんな怒ってるんだ？
クズにマウント取りたいだけでは？

SQLを書くときpdfの仕様書からコピペしろ


tomcat/9/libにjar入れたのいつだ？覚えてねえ…いまその話だ



5/19
プロジェクトのクリーン＋サーバーのクリーンを実行する前にずっとやっていた
５月の初めまでTomcatワークディレクトリをクリーンで習慣つけていて、
そこからクリーンでやってみようと思ってクリーンで習慣にしていたが、
SQL文のMAX(?)が効かなくなって、調べても一字一句同じかたちだしそもそもコピペしてたし？？
setStringでは''が入るからダメってだけ？？
*/