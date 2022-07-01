package com.zhliang.springboot.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SMSUtil {

	public static String SMS_UID = "mygirl";
	public static String SMS_PWD = "comeon";
	public static String SMS_SIGNATURE = "【聚财袋】";


	public static void main(String[] args) throws Exception {
		String verifyCode = genVerifyCode(6);
		String msg = "欢迎注册，您的验证码是：" + verifyCode + "，请尽快验证。";
		sendSmsMsg("http://sdk.entinfo.cn:8060/webservice.asmx", "13552651943", msg);// 18311337453
	}

	/**
	 * 北京创世漫道短信接口 向多个或某个手机号发送相同的信息
	 *
	 */
	public static boolean sendSmsMsg(String sms_url, String phone, String msg) {

		// 对xml格式的字符串
		if (msg.indexOf("&") >= 0) {
			msg = msg.replace("&", "&amp;");
		}
		if (msg.indexOf("<") >= 0) {
			msg = msg.replace("<", "&lt;");
		}
		if (msg.indexOf(">") >= 0) {
			msg = msg.replace(">", "&gt;");
		}
		String result = "";
		String soapAction = "http://tempuri.org/mt";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<mt xmlns=\"http://tempuri.org/\">";
		xml += "<sn>" + SMS_UID + "</sn>";
		xml += "<pwd>" + SMS_PWD + "</pwd>"; // 请勿泄露！
		xml += "<mobile>" + phone + "</mobile>";
		xml += "<content>" + msg + SMS_SIGNATURE + "</content>";
		xml += "<ext>5</ext>";
		xml += "<stime></stime>";
		xml += "<rrid></rrid>";
		xml += "</mt>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";
		log.info(xml);
		URL url;
		try {
			url = new URL(sms_url);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes("utf-8"));
			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length",
					String.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=utf-8");// 这一句也关键
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);

			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();

			InputStreamReader isr = new InputStreamReader(
					httpconn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = br.readLine())) {
				Pattern pattern = Pattern.compile("<mtResult>(.*)</mtResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			if ("".equals(result) || result == null) {
				log.info("send sms message failed--phone:" + phone
						+ "--message:" + msg + "--result:" + result);
				return false;
			} else {
				if ("-".equals(result.substring(0, 1))) {
					log.info("send sms message failed--phone:" + phone
							+ "--message:" + msg + "--result:" + result);
					return false;
				} else {
					log.info("send sms message success--phone:" + phone
							+ "--message:" + msg + "--result:" + result);
					return true;
				}
			}

		} catch (Exception e) {
			log.error("发送短信出错phone:" + phone + ",msg:" + msg, e);
			return false;
		}
	}

	/**
	 * 根据给定的验证码长度，生成一组对应长度的随机数
	 * 
	 * @param codelength
	 *            验证码的长度
	 * @return 生成的验证码字符串
	 */
	public static String genVerifyCode(int codelength) {
		char[] myCodeChar = { '6', '9', '4', '1', '7', '3', '8', '2', '5' };
		String checkCode = "";
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < codelength; i++){
			checkCode += myCodeChar[random.nextInt(myCodeChar.length)];
		}
		return checkCode;
	}
}