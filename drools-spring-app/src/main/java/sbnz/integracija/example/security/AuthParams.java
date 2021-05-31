package sbnz.integracija.example.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AuthParams {

    private Map<String, String> params = new HashMap<String, String>();

    public void addParam(String key, String value) {
        this.params.put(key, value);
    }

    public String getValue(String key) {
        return this.params.get(key);
    }

    public Set<String> getKeys() {
        return params.keySet();
    }
}
