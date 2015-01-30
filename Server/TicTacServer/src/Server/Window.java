package Server;
 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 
/*
 * LabelDemo.java needs one other file:
 *   images/middle.gif
 */
public class Window extends JPanel {
    public Window() throws IOException {
        super(new GridLayout(3,1));  //3 rows, 1 column
        JLabel label1, label2, label3;
        JButton b1;
        //Create the labels.
        label1 = new JLabel(TicTacServer.ip(), JLabel.CENTER);
        label2 = new JLabel(TicTacServer.getIpAddress(),JLabel.CENTER);
        //Create buttons.
         b1 = new JButton("Save Log");
         //Format buttons.
         b1.setVerticalTextPosition(AbstractButton.CENTER);
         b1.setHorizontalTextPosition(AbstractButton.CENTER);
         b1.setMnemonic(KeyEvent.VK_D);
         b1.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e)
            {
             try {
                 //Save log when button is pressed
                 saveLog();
             } catch (IOException ex) {
                 Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
             }
            }
         });
        //Add the labels.
        add(label1);
        add(label2);
        add(b1);

    } 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() throws IOException {
        //Create and set up the window.
        JFrame frame = new JFrame("Server");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new Window());
 
        //Display the window.
        frame.pack();
        frame.setSize(405, 163);
        frame.setVisible(true);
    }
 
    public static void bo(){
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
        //Turn off metal's use of bold fonts
            UIManager.put("swing.boldMetal", Boolean.FALSE);
                 
                try {
                    createAndShowGUI();
                } catch (IOException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public void actionPerformed(ActionEvent e) throws IOException {
    if ("log".equals(e.getActionCommand())) {
        saveLog();
    }
}
    public static void saveLog() throws IOException{
        //Create file
        try{
            BufferedWriter WriteFile = new BufferedWriter(new FileWriter("log.txt"));
            for(int c=0; c<TicTacServer.log.size(); c++){
                System.out.println(TicTacServer.log.get(c));
                WriteFile.write(TicTacServer.log.get(c));
                WriteFile.newLine();
            }
            WriteFile.close();
       }catch(IOException e){TicTacServer.log.add("Error " + e);}
    }
}