package sam.cloud.config.client.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Getter
public class AppConfig {
    @Value("${local.db-pass}")
    private String password;
    @Value("${local.db-user}")
    private String username;

    @Value("${rtas.db-user}")
    private String rtasUser;
    @Value("${rtas.db-pass}")
    private String rtasPass;
}
