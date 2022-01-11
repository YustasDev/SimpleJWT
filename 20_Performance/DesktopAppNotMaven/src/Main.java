import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

  public static JFrame frame;

  public static void main(String[] args) {

    frame = new JFrame();
    frame.setSize(600, 400);
    frame.setTitle("JFrame Based Contact Form");

    HandlerForms visionForm = new HandlerForms();
    frame.add(visionForm.mainForm.getMainPanel());

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
