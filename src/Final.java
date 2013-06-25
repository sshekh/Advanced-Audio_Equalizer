
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Final extends JFrame
{
    SoundGUI themaineq;
    public Final(){
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           //System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
           themaineq.name=chooser.getSelectedFile().getAbsolutePath();
           themaineq=new SoundGUI();themaineq.setVisible(true);
        }
        //setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //JLabel label = new JLabel("Waiting ends!");
        //this.add(label); 
        }
}
