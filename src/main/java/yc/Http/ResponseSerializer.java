package yc.Http;

import java.nio.charset.StandardCharsets;

public class ResponseSerializer {
    private Response response;
    public ResponseSerializer(Response response){
        this.response=response;
    }
    public byte[] consumeAll(){
        return response.toString().getBytes(StandardCharsets.UTF_8);
    }
}
