package bong.lines.basic.handler.common.factory;

import bong.lines.basic.handler.common.code.TYPE;
import bong.lines.basic.handler.common.factory.operation.LinesPost;
import bong.lines.basic.handler.common.factory.operation.PostJsonBody;

public class PostFactory {
    public static LinesPost<Object> post(TYPE type, String request){
        return new PostJsonBody(request);
//        switch (type){
//            case REQUEST_BODY_JSON:
//            default:
//                throw new RuntimeException("Post Exception!");
//        }

    }
}
