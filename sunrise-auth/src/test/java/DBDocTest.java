import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.sunrise.AuthApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @program: sunrise
 * @description: 生成数据库文档
 * @author: T.LM
 * @date: 2023-03-02 21:31
 **/
@Slf4j
@SpringBootTest(classes = AuthApplication.class)
public class DBDocTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void buildDBDocTest() {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        EngineConfig engineConfig = EngineConfig.builder()
                .fileOutputDir("D:\\tongliming\\workspace\\sunrise")
                .openOutputDir(false)
                .fileType(EngineFileType.HTML)
                .produceType(EngineTemplateType.freemarker)
                .build();
        Configuration configuration = Configuration.builder()
                .dataSource(dataSource)
                .engineConfig(engineConfig)
                .version("1.0")
                .description("db_info")
                .produceConfig(this.processConfig())
                .build();
        new DocumentationExecute(configuration).execute();
    }

    private ProcessConfig processConfig() {
        List<String> ignoreList = Collections.singletonList("undo_log");

        List<String> ignorePrefix = Arrays.asList("a", "b");
        List<String> ignoreSuffix = Arrays.asList("_test", "_Test");

        return ProcessConfig.builder()
                .designatedTableName(Collections.emptyList())
                .designatedTablePrefix(Collections.emptyList())
                .designatedTableSuffix(Collections.emptyList())
                .ignoreTableName(ignoreList)
                .ignoreTablePrefix(ignorePrefix)
                .ignoreTableSuffix(ignoreSuffix)
                .build();
    }

}
