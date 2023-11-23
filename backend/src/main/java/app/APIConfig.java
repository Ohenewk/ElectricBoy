package app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Configuration
public class APIConfig implements WebMvcConfigurer {

    @Value("${repository.type:INMEMORY}")
    public String repositoryType;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:*", getHostIPAddressPattern())
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    private String getHostIPAddressPattern() {
        String hostIp = "http://192.168.*.*:*";
        try {
            hostIp = String.format("http://%s:*", Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException ignored) {
        }
        return hostIp;
    }
}
