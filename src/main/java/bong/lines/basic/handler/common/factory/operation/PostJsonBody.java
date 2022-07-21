package bong.lines.basic.handler.common.factory.operation;

import java.util.Objects;
public class PostJsonBody implements LinesPost<Object>{

    private final String queryContent;

    public PostJsonBody(String queryContent) {
        this.queryContent = queryContent;
    }

    @Override
    public Object post() {
        String screenName = queryContent.split(" ")[1];
        try {
            return Objects.requireNonNull(
                            GetScreen.class
                                    .getResourceAsStream("/templates/user/" + screenName))
                    .readAllBytes();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
