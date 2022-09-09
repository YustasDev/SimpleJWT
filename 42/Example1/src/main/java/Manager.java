import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.Optional;
import java.util.function.Supplier;

public class Manager {
    private String name;

    public static void main(String[] args) throws GeneralSecurityException, IOException, ParseException {

        Manager mrSlate = new Manager("Mr. Slate");
        Department d = new Department();
        d.setBoss(mrSlate);
        System.out.println("Начальник: " + d.getBoss());
        System.out.println("Имя начальника: " + d.getBoss().get().getName());
        Department d1 = new Department();
        System.out.println("Начальник: " + d1.getBoss());
        System.out.println("Имя начальника: " + d1.getBoss().orElseGet(getEmptyName()).getName());


    }

    private static Supplier<? extends Manager> getEmptyName() {
        return () -> new Manager("There is no manager");
    }

    public Manager(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


class Department {
    private Manager boss;

    public Optional<Manager> getBoss() {
        return Optional.ofNullable(boss);
    }

    public void setBoss(Manager boss) {
        this.boss = boss;
    }
}