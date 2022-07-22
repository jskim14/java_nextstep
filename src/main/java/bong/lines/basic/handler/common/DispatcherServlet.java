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
            
            //todo inputstream은 이미 사용되었으니 또 불러올수가 없음 -> 타입핸들링에서 반환해서 보내줌

            String requestType = new TypeHandling(in, out).process().split(" ")[0];
            HTTP_METHOD httpMethod = HTTP_METHOD.of(requestType);

            HandlerMapping handlerMapping = null;
            switch (httpMethod) {
                case GET:
                    handlerMapping = new GetMapping(in, out);
                    break;
                case POST:
                    handlerMapping = new PostMapping(in, out);
                    break;
                case PUT:
                    handlerMapping = new PutMapping(in, out);
                    break;
                case DELETE:
                    handlerMapping = new DeleteMapping(in, out);
                    break;
            }

//            handlerMapping = new PostMapping(in, out);
            handlerMapping.process();

        }catch (Exception exception){
            log.error(exception.getMessage());
        }
    }
}
