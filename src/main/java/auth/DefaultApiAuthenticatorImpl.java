package auth;

import auth.ApiAuthenticator;
import request.ApiRequest;
import storage.CredentialStorage;
import storage.MysqlCredentialStorage;
import token.AuthToken;

public class DefaultApiAuthenticatorImpl implements ApiAuthenticator {

        private CredentialStorage credentialStorage;

        public DefaultApiAuthenticatorImpl() {
            this.credentialStorage = new MysqlCredentialStorage();
        }

        public DefaultApiAuthenticatorImpl(CredentialStorage credentialStorage) {
            this.credentialStorage = credentialStorage;
        }

        public void auth(String url) {
        }

        public void auth(ApiRequest apiRequest) {
            String appId = apiRequest.getAppId();
            String token = apiRequest.getToken();
            long timestamp = apiRequest.getTimestamp();
            String baseUrl = apiRequest.getBaseUrl();

            AuthToken clientAuthToken = new AuthToken(token, timestamp);
            if (clientAuthToken.isExpired()) {
                throw new RuntimeException("Token is expired.");
            }

            String secretKey = credentialStorage.getPasswordByAppId(appId);
            AuthToken serverAuthToken = AuthToken.createToken(baseUrl, appId, secretKey, timestamp, null);
            if (!serverAuthToken.match(clientAuthToken)) {
                throw new RuntimeException("Token verfication failed.");
            }
            System.out.println("验证通过");

    }
}
