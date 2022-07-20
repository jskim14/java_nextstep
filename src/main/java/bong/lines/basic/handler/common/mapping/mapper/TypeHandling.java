package bong.lines.basic.handler.common.mapping.mapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class TypeHandling{

    private final InputStream inputStream;
    private final OutputStream outputStream;

    public TypeHandling(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public String process() throws Exception {
        BufferedReader bufferedReader = getBufferedReaderForRequest(inputStream);
        return readRequestContent(bufferedReader);
    }

    protected BufferedReader getBufferedReaderForRequest(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    protected String readRequestContent(BufferedReader bufferedReader) throws Exception {
        return bufferedReader.readLine();
    }

    protected void doProcess(String request) throws Exception {

    }




}
