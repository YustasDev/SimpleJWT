package config;

import main.Parrot;
import main.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "main")
public class ProjectConfig {

    @Bean
    public Parrot parrot1() {
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean
    public Parrot parrot2() {
        Parrot p = new Parrot();
        p.setName("Miki");
        return p;
    }


//    @Bean
//    public Person person(Parrot parrot) {
//        Person p = new Person();
//        p.setName("capitan Flint");
//        p.setParrot(parrot);
//        return p;
//    }
//
//
//    @Bean
//    public Parrot parrot() {
//        Parrot p = new Parrot();
//        p.setName("Keks");
//        return p;
//    }
//
//
//



//    @Bean
//    @Primary
//    Parrot parrot() {
//        var p = new Parrot();
//        p.setName("testName_ForParrot");
//        return p;
//    }
//
//    @Bean
//    String hello() {
//        return "Hello";
//    }
//
//    @Bean
//    Integer ten() {
//        return 10;
//    }
//
//    @Bean("miki")
//    Parrot parrot2() {
//        var p = new Parrot();
//        p.setName("Miki");
//        return p;
//    }
//
//    @Bean
//    Parrot parrot3() {
//        var p = new Parrot();
//        p.setName("Riki");
//        return p;
//    }


}
