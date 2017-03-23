package livestreaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import livestreaming.kafka.ProducerMain;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ProducerMain.class, args);
        ProducerMain mainObj = ctx.getBean(ProducerMain.class);
        mainObj.init();
    }
}