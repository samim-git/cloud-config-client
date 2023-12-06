package sam.cloud.config.client.api.v1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sam.cloud.config.client.config.AppConfig;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ConfigController {
    private final AppConfig appConfig;
    @GetMapping("/config-test")
    public ResponseEntity<Object> configTest() {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("db-username", appConfig.getUsername());
        resMap.put("db-password", appConfig.getPassword());
        resMap.put("rtsa-db-username", appConfig.getRtasUser());
        resMap.put("rtsa-db-password", appConfig.getRtasPass());

        return ResponseEntity.ok(resMap);
    }
}
