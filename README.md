### Spring Cloud Config Client
Suppose we have a configuration property file in our configuration repository in `GitHub` named `db-application.yml`that stores the username and password for our database. In our local
`application.yml`file (config client app) we can use it as follows.
#### application.yml - config repo
the `application.yml`which store the credentials in config repository looks like this
```yaml
rtas:
  db:
    username: admin
    password: !admin@123
app2:
  db:
    username: app2DbUsername
    password: app2@password@123
```

#### application.yml - local
the `application.yml`in config client or rtas app will look like this, in order to use the username and password stored in the config repository
```yaml
app:
  db:
    username: ${rtas.db.username}
    password: ${rtas.db.password}
```

Now we can use the properties using `@Value` in our code as follows.
```java
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@Getter
@Setter
public class DBConfig {
    @Value("${app.db.username}")
    private String dbUsername;
    @Value("${app.db.password}")
    private String dbPassword;
}
```
In the above code the`@RefreshScope` is a Spring Cloud annotation used in a Spring Boot application to enable dynamic
reloading of configuration properties without restarting the entire application. This annotation is
commonly used in conjunction with Spring Cloud Config Server and the actuator's `cur -X POST http://{base_url}/actuator/refresh` endpoint.  

To enable the actuator in spring-boot project, we have to follow these steps.  
1. Add the required dependency in `pom.xml`
    ```xml
    <dependencies>
        <!-- Other dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>
    ```
2. Expose all actuator endpoints 
    ```yaml
    management:
      endpoints:
        web:
          exposure:
            include: "*"    
    ```
   Or specific endpoints
    ```yaml
    management:
      endpoints:
        web:
          exposure:
            include: health,info,refresh
    ```
3. Refresh the component without restarting the service. This will load all updated properties from config repository
    `curl -X POST http://localhost:8080/actuator/refresh`