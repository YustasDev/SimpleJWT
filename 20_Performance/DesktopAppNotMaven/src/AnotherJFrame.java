import javax.swing.*;
import java.awt.*;

public class AnotherJFrame extends JFrame {

    private final JPanel newPanel;
    private final JButton expandButton;
    private JTextArea textAreaNew;

    public AnotherJFrame(String surname, String name, String lastName) {

        newPanel = new JPanel();
        newPanel.setLayout(new BorderLayout(7, 8));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        newPanel.add(panel1, BorderLayout.SOUTH);
        expandButton = new JButton();
        expandButton.setContentAreaFilled(false);
        expandButton.setPreferredSize(new Dimension(130, 35));
        expandButton.setText("Expand");
        panel1.add(expandButton);


        textAreaNew = new JTextArea();
        textAreaNew.setLineWrap(true);
        textAreaNew.setName("FullName");
        textAreaNew.setRows(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(surname + "  " + name + "  " + lastName);
        textAreaNew.setText(String.valueOf(stringBuilder));
        newPanel.add(textAreaNew, BorderLayout.NORTH);

    }

    public JPanel getNewPanel() {
        return newPanel;
    }



}