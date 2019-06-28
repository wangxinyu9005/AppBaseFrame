package com.xinbo.app.appbaselibrary.utils;

//import org.apache.commons.codec.binary.Base64;


//import org.apache.commons.codec.binary.Base64;

import android.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SHA256Utils {

	/**
	 * 注意这里面的Base64方法中  encodeBase64String 会在尾部加一个回车符
	 * encodeToString 中不要使用Base64.Default,否则也会加一个回车符，建议使用Base64.NO_WRAP
	 * 因为后台可能不做trim()处理
	 * @param message
	 * @param secret
	 * @return
	 */
	public static String getHmacSHA256Str(String message, String secret) {
//		LoggerUtils.e("signature>>>getHmacSHA256Str>>>>>message=="+message);
//		LoggerUtils.e("signature>>>getHmacSHA256Str>>>>>secret=="+secret);
		String hash = "";
		try {
			Mac sha256HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
			sha256HMAC.init(secret_key);
			byte[] bytes = sha256HMAC.doFinal(message.getBytes("UTF-8"));
//			hash = Base64.encodeBase64String(bytes);
			hash = Base64.encodeToString(bytes, Base64.NO_WRAP);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		LoggerUtils.e("signature>>>getHmacSHA256Str>>>>>hash=="+hash);
		return hash;
	}

}