package edu.wgu.d387_sample_code.model.response;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

@RestController
public class WelcomeResponse {


    static ExecutorService messageExecutor = newFixedThreadPool(5);
    static Properties properties1 = new Properties();
    static Properties properties2 = new Properties();

    String english = "";
    String french = "";

    @GetMapping("/")
    @CrossOrigin
    public String WelcomeMessage() {
    messageExecutor.execute(() -> {
        try {
            InputStream stream = new ClassPathResource("translation_fr_CA.properties").getInputStream();
            properties1.load(stream);
            System.out.println(properties1.getProperty("hello") + " " + properties1.getProperty("welcome"));
            this.french = properties1.getProperty("hello") + " " + properties1.getProperty("welcome");
        } catch (Exception e) {
            e.printStackTrace();
        }
    });

    messageExecutor.execute(() -> {
        try {
            InputStream stream = new ClassPathResource("translation_en_US.properties").getInputStream();
            properties2.load(stream);
            System.out.println(properties2.getProperty("hello") + " " + properties2.getProperty("welcome"));
            this.english = properties2.getProperty("hello") + " " + properties2.getProperty("welcome");
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
    return (this.english + " | " + this.french);
    }
}



