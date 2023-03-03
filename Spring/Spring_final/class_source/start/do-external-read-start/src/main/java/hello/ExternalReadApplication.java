package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import hello.config.MyDataSourceConfigV1;
import hello.config.MyDataSourceConfigV2;
import hello.config.MyDataSourceConfigV3;
import hello.config.MyDataSourceEnvConfig;
import hello.config.MyDataSourceValueConfig;

@Import(MyDataSourceConfigV3.class)
// @Import(MyDataSourceConfigV2.class)
// @Import(MyDataSourceConfigV1.class)
// @Import(MyDataSourceValueConfig.class)
// @Import(MyDataSourceEnvConfig.class)
@SpringBootApplication(scanBasePackages = "hello.datasource")
@ConfigurationPropertiesScan({"hello"})
public class ExternalReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalReadApplication.class, args);
    }

}
