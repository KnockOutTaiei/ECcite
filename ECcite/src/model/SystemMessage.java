/*
 * システムメッセージを格納するJavaBeans
 * エラーメッセージ"数値が入力されていません！"など
 */
package model;
import java.io.Serializable;

public class SystemMessage implements Serializable{
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void erase() {
		this.message = "";
	}

	public SystemMessage(){
		message="";
	}
}