package token;

import util.SHAUtil;

import java.util.Map;
import java.util.Set;

public class AuthToken {

    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1*60*1000;
    private String token;
    private long createTime;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long createTime){
        this.token = token;
        this.createTime = createTime;
    }


    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    // 把 URL、AppID、密码、时间戳拼接为一个字符串；
    public static AuthToken createToken(String baseUrl, String appId, String secretKey, long timestamp, Map<String, String> params) {
        String preToken = baseUrl + appId + secretKey + timestamp;

        if(params != null) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                preToken = preToken + params.get(key);
            }
        }
        AuthToken token = new AuthToken(SHAUtil.getResult(preToken),timestamp);
        return token;
    }

    public String getToken(){
        return this.token;
    }

    public boolean isExpired(){
        long currentStamp = System.currentTimeMillis();
        System.out.println(currentStamp);
        if(this.createTime + expiredTimeInterval < currentStamp){
            return true;
        }
        return false;
    }

    public boolean match (AuthToken authToken) {
        if(this.getToken().equals(authToken.getToken())){
            return true;
        }
        return false;
    }
}
