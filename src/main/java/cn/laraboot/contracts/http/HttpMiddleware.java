package cn.laraboot.contracts.http;

import cn.laraboot.contracts.kernel.pipeline.Dockable;
import org.springframework.http.server.ServletServerHttpRequest;

public interface HttpMiddleware extends Dockable {
    public void handle(ServletServerHttpRequest request);
}
