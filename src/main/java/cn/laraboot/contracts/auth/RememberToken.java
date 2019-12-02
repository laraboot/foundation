package cn.laraboot.contracts.auth;

public interface RememberToken {
    public AuthenticateAble retrieveByToken(String identifier, String token);

    public void updateRememberToken(AuthenticateAble identifier, String token);
}
