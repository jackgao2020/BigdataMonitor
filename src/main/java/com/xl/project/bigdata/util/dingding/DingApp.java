package com.xl.project.bigdata.util.dingding;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.apache.commons.net.util.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @program: IAEngine
 * @description:
 * @author: XL.Gao
 * @create: 2021-05-12 15:40
 **/
public class DingApp {

    //                                    https://oapi.dingtalk.com/robot/send?access_token=40ff1d82579aa28f6b725fd148f02cdd2f4a64ad4c6452ae9d370be7b87adb19
//    public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=40ff1d82579aa28f6b725fd148f02cdd2f4a64ad4c6452ae9d370be7b87adb19";

    public static void main(String args[]) throws Exception {

//        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"【监控】Kafka积压监控：用户合并程序\"}," +
//                "\"at\":{\"atMobiles\":[\"18561808500\"],\"isAtAll\":false}}";
//
//        sendDingDing(textMsg);

        textMessage();

    }

    public static void textMessage() throws ApiException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {


        String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=40ff1d82579aa28f6b725fd148f02cdd2f4a64ad4c6452ae9d370be7b87adb19";
        Long timestamp = System.currentTimeMillis();
        String secret = "SEC70f91423fd01ef62f79dedeb6ebb7d7f2eb7beb40a8df7677fe38f8504bd53e7";

        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        System.out.println(sign);

        WEBHOOK_TOKEN = WEBHOOK_TOKEN + "&timestamp=" + timestamp + "&sign=" + sign;

        //这个是通过钉钉获取的机器人的连接，需要PC版才可以
        DingTalkClient client = new DefaultDingTalkClient(WEBHOOK_TOKEN);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("测试文本消息[监控]");
        request.setText(text);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(true);//设置@所有的人
//        at.setAtMobiles( new Ar);
        request.setAt(at);
        OapiRobotSendResponse response = client.execute(request);
        System.out.println(response.getErrcode());
    }

    public static boolean sendDingDing(String message) throws Exception {

        String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=40ff1d82579aa28f6b725fd148f02cdd2f4a64ad4c6452ae9d370be7b87adb19";

        Long timestamp = System.currentTimeMillis();
        String secret = "SEC70f91423fd01ef62f79dedeb6ebb7d7f2eb7beb40a8df7677fe38f8504bd53e7";

        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        System.out.println(sign);

        WEBHOOK_TOKEN = WEBHOOK_TOKEN + "&timestamp=" + timestamp + "&sign=" + sign;

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(message, "utf-8");
        httppost.setEntity(se);

        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
            return true;
        }

        return false;
    }
}
