package yc.Http;

import java.util.Map;

public class Request {
    public enum Method{
        GET,
        POST,
        PUT,
        DELETE,
        HEAD,
        OPTIONS,
        CONNECT,
        TRACE,
        PATCH
    }
    private Method method;
    private String target;
    private String version;
    private Map<RequestField,String> fields;
    private StringBuilder body;

    public Request(){
        this(Method.GET,"/");
    }
    public Request(String target){
        this(Method.GET,target);
    }
    public Request(Method method, String target){
        this(method,target,"HTTP/1.1");
    }

    public Request(Method method, String target, String version) {
        this.method = method;
        this.target = target;
        this.version = version;
    }
    void setBody(String body){
        this.body=new StringBuilder(body);
    }
    String getBody() {
        return body.toString();
    }
    void setMethod(String method){
        this.method=Method.valueOf(method);
    }
    String getMethod(){
        return this.method.name();
    }
    void setTarget(String target){
        this.target=target;
    }
    String getTarget(){
        return this.target;
    }
    void setVersion(String version){
        this.version=version;
    }
    String getVersion(){
        return this.version;
    }
    void put(String key,String value){
        fields.put(RequestField.getEnum("key"),value);
    }






}
