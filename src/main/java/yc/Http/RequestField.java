package yc.Http;

import java.util.HashMap;
import java.util.Map;

public enum RequestField {
    Accept("Accept"),
    Accept_Encoding("Accept-Encoding"),
    Accept_Language("Accept-Language"),
    Connection("Connection"),
    Content_Type("Content-Type"),
    Content_Length("Content-Length"),
    Host("Host"),
    Origin("Origin"),
    Referer("Referer"),
    User_Agent("User-Agent"),
    Cache_Control("Cache-Control"),
    Cookie("Cookie"),
    Set_Cookie("Set-Cookie"),
    Range("Range");

    private static final Map<String, RequestField> map;

    static {
        map = new HashMap<>();
        for (RequestField v : RequestField.values()) {
            map.put(v.getValue(), v);
        }
    }

    private final String value;

    RequestField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RequestField getEnum(String value) {
        RequestField requestField = map.get(value);
        if (requestField == null) {
            throw new IllegalArgumentException("Invalid Request Field");
        }
        return requestField;
    }

}
