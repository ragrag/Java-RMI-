package serverPackage;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;






public class server implements Runnable
{

    
Socket csocket;

server(Socket csocket)
{
	this.csocket = csocket;	
         
}

server() throws IOException
{

    ServerSocket socket = new ServerSocket(1037);
    JOptionPane.showMessageDialog(null, "Server Started", "Success", JOptionPane.INFORMATION_MESSAGE);  
   
    
   while (true) {  
           Socket clientSocket = socket.accept(); 
   Thread Client = new Thread (new server(clientSocket));
           Client.start();
   }
}
 

	@Override
	public void run() {
		
		try {
			
		
		DataInputStream input = new DataInputStream(csocket.getInputStream());
		ObjectOutputStream output = new ObjectOutputStream(csocket.getOutputStream());
        boolean found = false;
        String id = input.readUTF();
        String file= "persons.ser";
        ArrayList<Person> allPersons = readPerson(file);
        
  
        for(Person v:allPersons)
        {
            if (v.getId().equals(id))
            {
                 output.writeObject( (Person) (v));
                 found=true;
            }
        }
        
       if (!found)
           output.writeObject((Person) new Person());
       
        
        output.close();
        input.close();
		csocket.close();
		}
		catch (Exception o)
		{
		System.out.println(o);	
		
		}
	}
	
	
     @SuppressWarnings("unchecked")
    public  ArrayList<Person> readPerson(String file)
    {
        ArrayList<Person> persons ;
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