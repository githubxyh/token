package auth;

import request.ApiRequest;

public interface ApiAuthenticator {
    void auth (String url);
    void auth (ApiRequest apiRequest);
}
