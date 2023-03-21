import com.sunrise.AuthApplication;
import com.sunrise.entity.SunriseUser;
import com.sunrise.model.vo.UsernameAndPassword;
import com.sunrise.service.IJwtService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Description sunrise
 * @Author TLM
 * @Date 2023/3/21 15:25
 * @Version 1.0
 */
@Slf4j
@SpringBootTest(classes = AuthApplication.class)
public class CreateTestUser {

    @Autowired
    IJwtService jwtService;

    private static String num = "%04d";

    @Test
    public void create_2000_user() throws Exception {
        StringBuffer tokens = new StringBuffer();
        for (int i = 0; i < 2000; i++) {
            UsernameAndPassword u =
                    new UsernameAndPassword("test" + String.format(num, i + 1), "123456");
            String token = jwtService.registerAndGenerateToken(u);
            tokens.append(token).append("\r\n");
        }
        Files.write(Paths.get("tokens.txt"), tokens.toString().getBytes());
    }
}
