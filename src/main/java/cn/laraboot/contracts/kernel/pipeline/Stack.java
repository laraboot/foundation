package cn.laraboot.contracts.kernel.pipeline;

public interface Stack {
    public Object next(Object passable) throws Throwable;
}
