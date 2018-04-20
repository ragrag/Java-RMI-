package serverPackage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;



public class serverUI  {


 private JLabel jLabel1;

      public serverUI() throws IOException {
        buildFrame();         
   
        
        t.start();	
    }

    Thread t = new Thread(new Runnable() {
    public void run() {
        try {
            new server();
        } catch (IOException ex) {
            Logger.getLogger(serverUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});
    
  
    private void buildFrame() {

        jLabel1 = new JLabel();

		JFrame frame = new JFrame("Write Persons");
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLabel1.setForeground(new java.awt.Color(0, 150, 0));
        jLabel1.setText("Server Running");

		
		
        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS); 
        frame.setLayout(boxLayout);
		frame.add(jLabel1);
        frame.setVisible(true);
		
    }



   
    
}
