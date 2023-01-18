package main;

import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);


        Person person = context.getBean(Person.class);
        //Parrot parrot = context.getBean(Parrot.class);
        System.out.println("Person's name: " + person.getName());
        //System.out.println("Parrot's name: " + parrot.getName());
        System.out.println("Person's parrot: " + person.getParrot());



//        Parrot x = new Parrot();
//        x.setName("Kiki");
//        System.out.println("x_parrot ==> " + x.getName());
//
//        Supplier<Parrot> parrotSupplier = () -> x;
//
//        //context.registerBean("parrot1", Parrot.class, parrotSupplier);
//
//        context.registerBean("parrot1",
//                Parrot.class,
//                parrotSupplier,
//                bc -> bc.setPrimary(true));
//
//        Parrot p = context.getBean(Parrot.class);
//
//        System.out.println("p_parrot ==> " + p.getName());

    }



}
