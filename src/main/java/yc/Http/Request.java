package yc.Http;

import java.util.HashMap;
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
    private final Map<RequestField,String> fields;
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
        this.body=new StringBuilder();
        this.fields=new HashMap<>();
    }
    public void setBody(String body){
        this.body=new StringBuilder(body);
    }
    public void appendBody(String body){
        this.body.append(body);
    }
    public String getBody() {
        return this.body.toString();
    }
    public int getBodyLength(){
        return this.body.length();
    }
    public void setMethod(String method){
        this.method=Method.valueOf(method);
    }
    public void setMethod(Method method){
        this.method=method;
    }
    public String getMethod(){
        return this.method.name();
    }
    public Method getMethodEnum(){
        return this.method;
    }
    public void setTarget(String target){
        this.target=target;
    }
    public String getTarget(){
        return this.target;
    }
    public void setVersion(String version){
        this.version=version;
    }
    public String getVersion(){
        return this.version;
    }
    public void put(String key,String value){
        var enumField=RequestField.getEnum(key);
        if(enumField!=null)
            this.fields.put(enumField,value);
    }
    public String get(RequestField key){
       return this.fields.get(key);
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append(this.method.name()).append(" ").append(this.target).append(" ").append(this.version).append("\r\n");
        for(var entry:this.fields.entrySet()){
            sb.append(entry.getKey().getValue()).append(": ").append(entry.getValue()).append("\r\n");
        }
        sb.append("\r\n");
        sb.append(this.body);
        return sb.toString();
    }
    public void clear(){
        this.method=Method.GET;
        this.target="/";
        this.version="HTTP/1.1";
        this.fields.clear();
        this.body=new StringBuilder();
    }





}
