package clientPackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import serverPackage.Person;
import serverPackage.server;
import serverPackage.serverUI;

import javax.swing.*;



public class clientUI {

   

	private static JTextField age;
    private static JTextField fname;
    private static JTextField id;
    private static JLabel jLabel1;
    private static JLabel jLabel2;
    private static JLabel jLabel3;
    private static JLabel jLabel4;
    private static JTextField lname;
    private static JTextField searchField;
    private static JButton search_btn;
    private static JButton startServer;


    private static void buildFrame() {

	
		JFrame frame = new JFrame("Find Person y ID");
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	
        search_btn = new JButton();
        searchField = new JTextField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        fname = new JTextField();
        lname = new JTextField();
        age = new JTextField();
        id = new JTextField();
        startServer = new JButton();

  

        search_btn.setText("Search By ID");
        search_btn.addActionListener(new ActionListener() {
         
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
			           
		            connect();
		        } catch (IOException ex) {
		            Logger.getLogger(clientUI.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, "Cannot Connect, Make Sure That The Server Is Running","Connection Failure" ,JOptionPane.ERROR_MESSAGE);
		        } catch (ClassNotFoundException ex) {
		            Logger.getLogger(clientUI.class.getName()).log(Level.SEVERE, null, ex);
		        }
				
			}
        });

      

        jLabel1.setText("First Name");

        jLabel2.setText("Last Name");

        jLabel3.setText("Age");

        jLabel4.setText("ID");

        fname.setEditable(false);
        fname.setBackground(new Color(255, 255, 255));
        fname.setText(" ");

        lname.setEditable(false);
        lname.setBackground(new Color(255, 255, 255));
        lname.setText(" ");

        age.setEditable(false);
        age.setBackground(new Color(255, 255, 255));
        age.setText(" ");

        id.setEditable(false);
        id.setBackground(new Color(255, 255, 255));
        id.setText(" ");

        startServer.setText("Start Server");
     
        startServer.addActionListener(new ActionListener() {
        	
        	@Override
			public void actionPerformed(ActionEvent arg0) {
        		 t.start();
        	        
				
			}
        });


        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS); 
        frame.setLayout(boxLayout);
        frame.add(startServer);
        frame.add(searchField);
		frame.add(search_btn);
        frame.add(jLabel1);
        frame.add(fname);
        frame.add(jLabel2);
        frame.add(lname);
        frame.add(jLabel3);
        frame.add(age);
        frame.add(jLabel4);
        frame.add(id);
        frame.setVisible(true);
    }


    public static void main(String args[]) throws IOException, ClassNotFoundException {    
        buildFrame(); 
    }
 
    
    static Thread t = new Thread(new Runnable() {
    public void run() {
        try {
            new serverUI();
        } catch (IOException ex) {
            Logger.getLogger(serverUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});
    

  
private void startServerActionPerformed(ActionEvent evt) {
        t.start();   
    }


public static void connect() throws IOException, ClassNotFoundException
{
	Socket clientSocket = new Socket("localhost", 1037);

	ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
	DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

    String pid = searchField.getText();
    output.writeUTF(pid);
    Person obj = (Person) input.readObject();

	if (obj.getfName() == null)
        {
          JOptionPane.showMessageDialog(null,  "ID " + pid+" Was Not Found","Not Found" ,JOptionPane.ERROR_MESSAGE);

          fname.setText("");
          lname.setText("");
          age.setText("");
          id.setText("");
        }
    else 
        {
         JOptionPane.showMessageDialog(null, "ID "+ pid+ " Was Found", "Found", JOptionPane.INFORMATION_MESSAGE);  
         
          fname.setText(obj.getfName());
          lname.setText(obj.getlName());
          age.setText((String.valueOf(obj.getAge())));
          id.setText(obj.getId());

        }

             output.close();
             input.close();
             clientSocket.close(); 
	}   
}