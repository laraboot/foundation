package cn.laraboot.hashing;

import cn.laraboot.contracts.kernel.SecretProvider;

public class Sha1Hasher extends BaseHasher {
    public Sha1Hasher(SecretProvider secretProvider) {
        super(secretProvider);
    }

    /**
     * @return 算法名称
     */
    @Override
    public String algorithm() {
        return "SHA-1";
    }
}
