package yc.Http;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getBody() {
        return new String(this.body);
    }

    public void setBody(String body) {
        this.body = new StringBuilder(body);
        this.fields.put(ResponseField.Content_Length,Integer.toString(this.body.toString().getBytes().length));
    }
    public void put(String key, String value){
        ResponseField field=ResponseField.getEnum(key);
        if(field!=null){
            this.fields.put(field,value);
        }
    }
    public String get(ResponseField field){
        return this.fields.get(field);
    }

    public enum Status{
        OK(200,"OK"),
        BAD_REQUEST(400,"Bad Request"),
        NOT_FOUND(404,"Not Found"),
        METHOD_NOT_ALLOWED(405,"Method Not Allowed"),
        INTERNAL_SERVER_ERROR(500,"Internal Server Error"),
        NOT_IMPLEMENTED(501,"Not Implemented");
        private final static Map<Integer,Status> map;
        static {
            map=new HashMap<>();
            for(Status s:Status.values()){
                map.put(s.getCode(),s);
            }
        }
        private final int code;
        private final String message;
        Status(int code, String message){
            this.code=code;
            this.message=message;
        }
        public int getCode(){
            return this.code;
        }
        public String getMessage(){
            return this.message;
        }
    }
    private String version;
    private Status status;
    private final Map<ResponseField,String> fields;
    private StringBuilder body;
    public Response(){
        this(Status.OK);
    }
    public Response(Status status){
        this("HTTP/1.1",status);
    }
    public Response(String version, Status status){
        this.version=version;
        this.status=status;
        this.fields=new HashMap<>();
        this.body=new StringBuilder();
    }
    @Override
    public String toString(){
        var sb=new StringBuilder();
        sb.append(this.version).append(" ").append(this.status.getCode()).append(" ").append(this.status.getMessage()).append("\r\n");
        for(var entry:this.fields.entrySet()){
            sb.append(entry.getKey().getValue()).append(": ").append(entry.getValue()).append("\r\n");
        }
        sb.append("\r\n").append(this.body);

        return sb.toString();
    }
}
