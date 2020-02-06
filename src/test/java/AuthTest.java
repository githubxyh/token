import auth.DefaultApiAuthenticatorImpl;
import org.junit.Test;
import request.ApiRequest;
import token.AuthToken;

public class AuthTest {
    @Test
    public void testCreateToken(){
        String baseUrl = "https://www.baidu.com";
        String appId = "123";
        String secretKey = "321";
        long timestamp = System.currentTimeMillis();
        AuthToken serverAuthToken = AuthToken.createToken(baseUrl, appId, secretKey, timestamp, null);
        System.out.println(serverAuthToken.getToken());
    }

    @Test
    public void testAuth(){
        String baseUrl = "https://www.baidu.com";
        String appId = "123";
        String secretKey = "321";
        long timestamp = System.currentTimeMillis() - 1*60*1000 + 10000;
        System.out.println("时间戳：" + timestamp);
        // 生成token
        AuthToken serverAuthToken = AuthToken.createToken(baseUrl, appId, secretKey, timestamp, null);
        System.out.println("生成的token：" + serverAuthToken.getToken());
        // 创建api请求对象
        ApiRequest request = new ApiRequest(baseUrl,serverAuthToken.getToken(),appId,timestamp);
        // 验证请求token
        DefaultApiAuthenticatorImpl auth = new DefaultApiAuthenticatorImpl();
        auth.auth(request);
    }
}
