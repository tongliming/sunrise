import cn.hutool.core.codec.Base64;
import com.sunrise.AuthApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.AlgorithmConstraints;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @program: sunrise
 * @description: RSA非对称加密算法生成工具
 * @author: T.LM
 * @date: 2023-03-02 19:47
 **/
@Slf4j
@SpringBootTest(classes = AuthApplication.class)
public class RSATest {

    @Test
    public void generateKeyBytes() throws Exception {
        KeyPairGenerator rsa256 = KeyPairGenerator.getInstance("RSA");
        rsa256.initialize(2048);

        KeyPair keyPair = rsa256.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        log.info("private key [{}]", Base64.encode(privateKey.getEncoded()));
        log.info("public key [{}]", Base64.encode(publicKey.getEncoded()));
    }
}
