package serverPackage;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;


public class writePersonsUI  {

 
 
 
    private static JTextField age;
    private static JTextField fname;
    private static JTextField id;
    private static JLabel label1;
    private static JLabel label2;
    private static JLabel label3;
    private static JLabel label4;
    private static JTextField lname;
    private static JButton writeBtn;
 
   

    private static void buildFrame()  {
  
    	JFrame frame = new JFrame("Write Persons");


		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        fname = new JTextField();
        lname = new JTextField();
        age = new JTextField();
        id = new JTextField();
     
        label4 = new JLabel();
        label3 = new JLabel();
        label2 = new JLabel();
        label1 = new JLabel();
        writeBtn = new JButton();

        fname.setAlignmentX(Component.CENTER_ALIGNMENT);
       lname.setAlignmentX(Component.CENTER_ALIGNMENT);
        age.setAlignmentX(Component.CENTER_ALIGNMENT);
        id.setAlignmentX(Component.CENTER_ALIGNMENT);
        fname.setMaximumSize(new Dimension(400,40));
        lname.setMaximumSize(new Dimension(400,40));
        age.setMaximumSize(new Dimension(400,40));
        id.setMaximumSize(new Dimension(400,40));


        label4.setText("ID");

        label3.setText("Age");

        label2.setText("Last Name");

        label1.setText("First Name");

        writeBtn.setText("Write");
        writeBtn.addActionListener((ActionListener) new ActionListener() {
           @Override
			public void actionPerformed(ActionEvent arg0) {
				Save();
			}

			
		});
		
		
        

        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS); 
        frame.setLayout(boxLayout);
  
		frame.add(label1);
		frame.add(fname);
		frame.add(label2);
		frame.add(lname);
		frame.add(label3);
		frame.add(age);
		frame.add(label4);
		frame.add(id);
		frame.add(writeBtn);


        frame.setVisible(true);
        
    }

    private static void Save() {
      
        
        
            
           String file= "persons.ser";
           ArrayList <Person>  personList = new ArrayList<Person>();
           Person p1= new Person("Ahmed","Mohamed",23,"144711");
           Person p2= new Person("Amro","Lotfy",18,"244122");

           personList.add(p1);
           personList.add(p2);


           writePerson(personList,file);
        
        
        if(fname.getText().equals("") || lname.getText().equals("") || age.getText().equals("") || id.getText().equals("") )
             JOptionPane.showMessageDialog(null,  "Please Fill All Fields","Empty Fields" ,JOptionPane.ERROR_MESSAGE);
        else 
        {
           
        ArrayList<Person> plist = readPerson(file);
        int page = Integer.parseInt(age.getText());
        plist.add(new Person(fname.getText(),lname.getText(),page,id.getText()));
     
        writePerson(plist,file);
          JOptionPane.showMessageDialog(null,  "Person Written Successfully To The File ","Success" ,JOptionPane.INFORMATION_MESSAGE);
        
        }
    }

  
   
    public static void main(String args[]) {
    
       buildFrame();
    }

    
    
        
         public static  void writePerson(ArrayList<Person> persons, String file)
    {
        FileOutputStream fileOut;
        try
         {
            fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(persons);
            out.close();
            fileOut.close();
         }catch(Exception e)
         {
             e.printStackTrace();
             return;
         }  
        return;
    }
     
    
    public static ArrayList<Person> readPerson(String file)
    {
        ArrayList<Person> persons = null;
          try
          {
             FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fileIn);
             persons = (ArrayList<Person>) in.readObject();
             in.close();
             fileIn.close();
         }catch(Exception e)
         {
             e.printStackTrace();   
             return null;
         }
         return persons;
    }

   
}