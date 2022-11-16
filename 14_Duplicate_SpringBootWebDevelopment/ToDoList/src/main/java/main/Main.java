package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Main {


  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Scheduled(cron="0 0 10 * * *")
  public void exitIn11pmEveryDay  () {
    System.exit(0);
  }




}
