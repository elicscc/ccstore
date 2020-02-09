package com.ccstay.ccstore.pay;

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101600697610";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCI+3weCJTZ53qzyWAKLBk57W+X//CdXgtSavMDyZxnnsf0R+0EDrUqtuDgy4T08+QSd/f9gBSEjqtln167qkW4JbSJoTEXvKrHwBH+/G5wkvgOlDdIHKRXRIeZKQSIg+/K43NsXgWwDPOsNoYO15InsNTR32UyDKl82QS3r0l5O7Vv3KP00DOL1ter8RKHILvFp/I3i+RiVuky3oxwsN+7pGOaM1M3OZW1N60uIFQpV5cOY2+DMaYdD1e/LPoxgXhb3knA2ggDrOBaAdqG6HiWcuwMZaQsljI95tGlxRsMTEVK8rbwZF2bXiiMQH6sGtdm7vKC14qZ3LSe5vBJYOADAgMBAAECggEATu/M+GmtjVxtQwl6hKIg71a6BfeBKs407Auher9FmBjR1R1Vog7vq2uzbxcYySd6eIIVsEmMiU7FykvqhW57usBPpzfyWGFqcK1oMW46HjkzJQOYT7/hnEBre4E9kYWmO16S9xG/aVItYUISQp9LJAAsw7xeTlcpnD5bD5a9WlSIBxfRs8UgeFT4rTufFJi+ruq/Yw4BGlhl/XNxeu+j79E6Z0ozymnJPDm+SSeZ6RsliHxNr1ONKzV+Dh/bwwfv1+028UBMPR9Qi4cRyce2kHsvdG5XD2MnWAI4V5Xlr8h/rd23Ng7kymWEGZpVZdi7qc3udnqidFsxCblRrNzmwQKBgQDkUgoeSsKTTKr36EoaTF10WiYNXAZs6BzLhCU0c6QQymMPydCEylfWGSIck815h0gmNVe94R2txcJGaHVICSZE9bXtgAqr2Q2zONubrBTWImuN/kNVkTKY0EW2StBwt5Gufu/pZ2qt2MlK8REVwUlJBEXaSmjZ+3lZhnPes6LY0wKBgQCZlsDaC/+GXqsg3UlsTfYAUJ0kTm+gAtGg6rtbf0lA/S128192xiidOoil74t2J/URWogDD1qc9ebg+CgqvhxbfQ/luD2AfXWH6oe56E5PoZUimJhCA6RpQ+q4S10FEj5GAd03EMpTW/cY9ydx6wHq/aKgQ4q0zRhOtivQ+GFeEQKBgDIkllZKwurIpq+lU0jtRoT8G2lJsZuPamuDzQv07a5GVHUuNF5Fdf7uLCCskS7EeCL0Ch4IZ5U3XlBKNp+oasAwWlEbkrxlAe4/aXOJ2lWg35AirDUJm1wWOYksaxSmLBdvh07ySGp/ts1/nenyNh8UEic7ukVNRjxUG/Rn7TbDAoGAX6ZA6Ao80gO28zqcdTSAFWYe1vN6FN5siphkofC9QBnr7H5YX3G87V38p8psweLUfkAxICa5ubJyXHxvF19Z7agO9HMu6gw327M1X5PK4VCxvd/mQoZeJ6hCTw+wGG5qFdMnXxm3xv4o/oU9LXa7tQrL8p7djQeFMc/FrdhhWuECgYASL06Oeq8nX5H8gDERsi/2gWG4hfuYet2w5QZJifjuk8sy/dgx4XD2vkDreHhjQNsUzxDZo0lMsDjnLvKlQrMex2M5JhUqrBCm7NFt8SrNdC8xqN4q69fPShIFLSJjPxRvmmEjdnhivCFIxrDyMVZj649v5vCNbu11Ld27lRYjaA==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnbL1ChVSoJv5HI6tUh8fd283L2r7iYJTwUPJ0hLlrDBfNme5KL3OEBguV7lzSguuxEOSwxYV0cn3xs955kBooEV7hnaZ3H3TDtBgLLYeonCWQ/KQBXKpy5aiExsCQUn/WygQ6VlJWSbAYFKEHm5xMj0KX4GQxBdTNXNbxQRHzgf3RaHA0ivFrFvDqPr/ohICvrjkLvRCb1FSTmhellqPVJoeT5l6MraI6uMLDRZ657LPnAHJ53zywSxAA+3mgfI1LoeEcz62qnZHWmKLd9yGMlWWbdLWlb+GJo2gFsNoq5com1BLgzEpvZp2lcJHTBPr5D3FCT8fVGguHmj5JmuiEQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.ccstay.com/web/paySuccess.html";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://www.ccstay.com/web/paySuccess.html";
    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";



}


