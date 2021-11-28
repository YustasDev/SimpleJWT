import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainForm {

  private JPanel mainPanel;
  private JTextArea textArea1;
  private JButton countButton;
  private JButton clearItButton;

  public MainForm() {
    clearItButton.addActionListener(new Action() {
      @Override
      public Object getValue(String key) {
        return null;
      }

      @Override
      public void putValue(String key, Object value) {

      }

      @Override
      public void setEnabled(boolean b) {

      }

      @Override
      public boolean isEnabled() {
        return false;
      }

      @Override
      public void addPropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void removePropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void actionPerformed(ActionEvent e) {
        textArea1.setText("");
      }
    });

    countButton.addActionListener(new Action() {
      @Override
      public Object getValue(String key) {
        return null;
      }

      @Override
      public void putValue(String key, Object value) {

      }

      @Override
      public void setEnabled(boolean b) {

      }

      @Override
      public boolean isEnabled() {
        return false;
      }

      @Override
      public void addPropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void removePropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void actionPerformed(ActionEvent e) {
        String text = textArea1.getText();
        int textLength = text.length();
        JOptionPane.showMessageDialog(
           mainPanel,
           textLength + "  chars",
            "Length of text",
            JOptionPane.PLAIN_MESSAGE
        );

      }
    });

    textArea1.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        textArea1.setBackground(Color.GREEN);
      }

      @Override
      public void mousePressed(MouseEvent e) {

      }

      @Override
      public void mouseReleased(MouseEvent e) {

      }

      @Override
      public void mouseEntered(MouseEvent e) {

      }

      @Override
      public void mouseExited(MouseEvent e) {

      }
    });

    textArea1.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'К'){
          textArea1.setText(textArea1.getText() + "  Пушок  ");

        }
      }

      @Override
      public void keyPressed(KeyEvent e) {

      }

      @Override
      public void keyReleased(KeyEvent e) {

      }
    });

  }

  public JPanel getMainPanel(){
    return mainPanel;
  }
}
