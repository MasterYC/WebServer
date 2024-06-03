package yc.Http;


import java.util.HashMap;
import java.util.Map;


public enum ResponseField{
    Access_Control_Allow_Origin("Access-Control-Allow-Origin"),
    Access_Control_Allow_Methods("Access-Control-Allow-Methods"),
    Access_control_Allow_Credentials("Access-Control-Allow-Credentials"),
    Access_Control_Allow_Headers("Access-Control-Allow-Headers"),
    Content_Type("Content-Type"),
    Content_Length("Content-Length"),
    Content_Encoding("Content-Encoding"),
    Date("Date"),
    Server("Server"),
    Connection("Connection"),
    Cache_Control("Cache-Control");

    private final String value;
    private static final Map<String,ResponseField> map;
    static {
        map = new HashMap<>();
        for (ResponseField v : ResponseField.values()) {
            map.put(v.getValue(),v);
        }
    }

    ResponseField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static ResponseField getEnum(String value){
        return map.get(value);
    }
}
