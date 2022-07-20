package bong.lines.basic.handler.common;

import bong.lines.basic.handler.common.mapping.DeleteMapping;
import bong.lines.basic.handler.common.mapping.GetMapping;
import bong.lines.basic.handler.common.mapping.PostMapping;
import bong.lines.basic.handler.common.mapping.PutMapping;
import bong.lines.basic.handler.common.mapping.mapper.HandlerMapping;
import bong.lines.basic.handler.common.mapping.mapper.TypeHandling;
import bong.lines.basic.handler.common.method.HTTP_METHOD;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
public class DispatcherServlet implements Runnable {

    private final Socket connection;

    public DispatcherServlet(Socket connection){
        this.connection = connection;
    }

    @Override
    public void run() {
        try(InputStream in = connection.getInputStream();
            OutputStream out = connection.getOutputStream()) {

            HandlerMapping handlerMapping = null;
            String requestType = new TypeHandling(in, out).process().split(" ")[0];
            HTTP_METHOD httpMethod = HTTP_METHOD.of(requestType);

            switch (httpMethod) {
                case GET:
                    handlerMapping = new GetMapping(in, out);
                case POST:
                    handlerMapping = new PostMapping(in, out);
                case PUT:
                    handlerMapping = new PutMapping(in, out);
                case DELETE:
                    handlerMapping = new DeleteMapping(in, out);
            }

            handlerMapping.process();

        }catch (Exception exception){
            log.error(exception.getMessage());
        }
    }
}
