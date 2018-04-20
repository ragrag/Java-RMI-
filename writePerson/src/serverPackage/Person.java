package serverPackage;





import java.io.Serializable;


public class Person implements Serializable {
    
    private String fName;
    private String lName;
    private int age;
    private String id;
  public Person() {
     
    }
    public Person(String fName, String lName, int age, String id) {
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.id = id;
    }

    
    
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}