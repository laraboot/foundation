package cn.laraboot.http.middlewares;

import cn.laraboot.contracts.kernel.pipeline.Dockable;
import cn.laraboot.contracts.kernel.pipeline.Stack;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

abstract public class HttpMiddleware implements Dockable {
    @Override
    public Object handle(Object passable, Stack stack) throws Throwable {
        if (passable instanceof HttpServletRequest) {
            return handle((HttpServletRequest) passable, stack);
        }
        return stack.next(passable);
    }

    abstract public ResponseEntity handle(HttpServletRequest request, Stack stack) throws Throwable;
}
