package cn.laraboot.contracts.auth;

import java.util.Map;

public interface GuardProvider {
    public Map<String, Guard> guards();

    public String defaultGuard();
}
