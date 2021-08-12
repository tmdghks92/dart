package cc.qwer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "dart.api")
@PropertySource(value = "classpath:application.yml", factory = YamlPropertyFactory.class)
public class YamlProperties {
    private String key;
}
