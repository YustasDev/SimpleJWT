import java.awt.Color;
import java.awt.Graphics2D;
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
  private JButton countButton;
  private JButton clearItButton;
  private JPanel drawPanel;

  public MainForm() {

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

      Graphics2D graphics2D = (Graphics2D) drawPanel.getGraphics();
      graphics2D.setColor(Color.RED);
      graphics2D.drawRect(50,50, 60,30);
      }
    });

  }

  public JPanel getMainPanel(){
    return mainPanel;
  }
}
