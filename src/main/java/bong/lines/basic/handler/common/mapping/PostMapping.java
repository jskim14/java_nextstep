package bong.lines.basic.handler.common.mapping;

import bong.lines.basic.handler.common.mapping.mapper.HandlerMapping;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class PostMapping extends HandlerMapping {

    private byte[] responsebody;
     private final StringBuffer responseContent = new StringBuffer();

    public PostMapping(InputStream inputStream, OutputStream outputStream) {
        super(inputStream, outputStream);
    }

    @Override
    protected BufferedReader getBufferedReaderForRequest(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    @Override
    protected String readRequestContent(BufferedReader bufferedReader) throws IOException, Exception {
        return bufferedReader.readLine();
    }

    @Override
    protected void doProcess(String request) throws Exception {
        switch (whichPostType(request)){
            case "Value" :
                break;
            default:
                throw new RuntimeException("POST Type Missing");
        }


    }

    private String whichPostType(String request) {
        return "Value";
    }

    @Override
    protected void responseHandling(OutputStream outputStream) {

        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        byte[] body = null;
        body = responseContent.toString().getBytes(StandardCharsets.UTF_8);

        response200Header(dataOutputStream, body.length);
        responseBody(dataOutputStream, body);

    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent){
        try{
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8 \r\n");
                        dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body){
        try{
            dos.write(body, 0, body.length);
            dos.writeBytes("\r\n");
            dos.flush();
        }catch (Exception exception){
            exception.getMessage();
        }
    }
}
