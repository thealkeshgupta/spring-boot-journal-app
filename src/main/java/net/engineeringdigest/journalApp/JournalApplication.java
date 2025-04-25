package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
        System.out.println("\n########### HEALTH CHECK ###########\n\tFor health check, click on\n http://localhost:8080/public/health-check\n####################################\n");
    }
}