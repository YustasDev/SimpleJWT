import javax.swing.*;

public class HandlerForms extends JFrame {

    public MainForm mainForm;
    public AnotherForm anotherForm;

    public String surname;
    public String name;
    public String lastName;


    public HandlerForms(){
        mainForm = new MainForm();
        anotherForm = new AnotherForm(surname, name, lastName);
    }
}
