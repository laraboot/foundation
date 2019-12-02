package cn.laraboot.encryption;

import cn.laraboot.contracts.encryption.DecryptException;
import cn.laraboot.contracts.encryption.EncryptException;
import cn.laraboot.contracts.encryption.Encrypter;
import cn.laraboot.contracts.kernel.SecretProvider;
import cn.laraboot.foundation.manager.*;

import java.util.HashMap;

public class EncrypterManager extends Manager<Encrypter> implements Encrypter {

    private SecretProvider secretProvider;

    public EncrypterManager(SecretProvider secretProvider) {
        this.secretProvider = secretProvider;
    }

    /**
     * 设置驱动供应商
     */
    @Override
    public void setDriverProviders() {
        driverProviders = new HashMap<>();
        driverProviders.put("aes", () -> new AesEncrypter(secretProvider));
        driverProviders.put("des", () -> new DesEncrypter(secretProvider));
    }

    /**
     * 获取默认驱动
     *
     * @return 驱动名
     */
    @Override
    protected String defaultDriver() {
        return "aes";
    }

    @Override
    public String encrypt(String value) throws EncryptException {
        try {
            return driver().encrypt(value);
        } catch (DriverException e) {
            throw new EncryptException(e.getMessage());
        }
    }

    @Override
    public String decrypt(String value) throws DecryptException {
        try {
            return driver().decrypt(value);
        } catch (DriverException e) {
            throw new DecryptException(e.getMessage());
        }
    }
}
