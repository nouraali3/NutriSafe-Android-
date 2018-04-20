package datatypes;

import java.io.Serializable;
import java.util.List;


/**
 * Created by user on 16/04/2018.
 */

public class User implements Serializable
{
    String name;
    float age;
    List<String> allergies;

    public User(String name, float age)
    {
        this.name = name;
        this.age = age;
    }

    public User(String name, float age, List<String> allergies)
    {
        this.name = name;
        this.age = age;
        this.allergies = allergies;
    }

    public String getName() {return name;}
    public float getAge() {return age;}
    public List<String> getAllergies() {return allergies;}

    public void setName(String name) {this.name = name;}
    public void setAge(float age) {this.age = age;}
    public void setAllergies(List<String> allergies) {this.allergies = allergies;}
}

