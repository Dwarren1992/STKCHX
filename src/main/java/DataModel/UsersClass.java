package DataModel;

import java.util.HashMap;
import org.json.JSONObject;

public class UsersClass {

    private Integer userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Integer location;
    private String salt ="";

    public UsersClass(){
    }

    public UsersClass(Integer userID, String firstName, String lastName, Integer location, String username, String password){

        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.username = username;

        HashMap<String, String> hashedPassword = HashClass.saltAndHashPassword(password);

        this.password = hashedPassword.get("password");
        this.salt = hashedPassword.get("salt");
    }

    public Integer getUserID(){
        return userID;
    }

    public void setUserID(Integer userID){
        this.userID = userID;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        HashMap<String, String> hashedPassword = HashClass.saltAndHashPassword(password);

        this.password = hashedPassword.get("password");
        this.salt = hashedPassword.get("salt");
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public Integer getLocation(){
        return location;
    }

    public void setLocation(Integer location){
        this.location = location;
    }

    public String getSalt(){
        return salt;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

}
