package storage;

import java.util.HashMap;

public class MysqlCredentialStorage implements CredentialStorage{

    private static HashMap<String,String> AkSkStorage = new HashMap<String, String>();
    {
        AkSkStorage.put("123","321");
    }

    public String getPasswordByAppId(String appId) {
        return AkSkStorage.get(appId);
    }
}
