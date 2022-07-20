package bong.lines.basic.handler.common.method;

import java.util.Arrays;
import java.util.Optional;

public enum HTTP_METHOD {
    GET("GET"),
    POST("POST"),
    DELETE("DELETE"),
    PUT("PUT");

    private final String httpMethod;

    public String getHttpMethod() {
        return httpMethod;
    }

    HTTP_METHOD(String httpMethod){
        this.httpMethod = httpMethod;
    }

    public boolean isContain(String requestContent){
        return requestContent.contains(this.httpMethod);
    }

    public static HTTP_METHOD of (String requestContent) throws Exception {
        Optional<HTTP_METHOD> method = Arrays.stream(values())
                .filter(v -> v.getHttpMethod().equals(requestContent))
                .findFirst();

        if(method.isPresent()) {
            return method.get();
        }
        throw new Exception();
    }

}
