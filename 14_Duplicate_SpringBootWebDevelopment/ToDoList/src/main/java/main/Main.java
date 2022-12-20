package main;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.PrintStream;

@SpringBootApplication
@EnableScheduling
public class Main {


  public static void main(String[] args) {
   // SpringApplication.run(Main.class, args);

    SpringApplication app = new SpringApplication (Main.class);
    app.setBanner(new Banner() {
    @Override
    public void printBanner(Environment environment, Class<?> sourceclass, PrintStream out) {
        out.print("\n\n\tThis is my own banner!\n\n".toUpperCase());
      }
    });
    app.run(args);
  }

  @Scheduled(cron="0 0 10 * * *")
  public void exitIn11pmEveryDay  () {
    System.exit(0);
  }




}
