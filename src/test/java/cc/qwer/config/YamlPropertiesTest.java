package cc.qwer.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class YamlPropertiesTest {
    @Autowired
    YamlProperties yamlProperties;

//    @Value("${dart.api.key}")
    String key;
//    @Test
    void test() {
        System.out.println("yamlProperties = " + yamlProperties.getKey());
        assertThat(yamlProperties.getKey()).isEqualTo("1cc8f69874fa8960e94471e0097b2c31353cd167");
        assertThat(key).isEqualTo("1cc8f69874fa8960e94471e0097b2c31353cd167");
    }
}