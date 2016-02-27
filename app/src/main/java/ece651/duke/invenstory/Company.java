package ece651.duke.invenstory;

import java.util.Map;

/**
 * Created by petermurphy on 2/22/16.
 */
public class Company {
    String companyName, password;
    private Map<String,String> userMap;

    Company (String companyName, String companyPassword, User user){
        this.companyName = companyName;
        this.password = companyPassword;
        if(userMap.containsKey(user.username)){
            /*username already exists!*/
        }else{
            createNewUser(user.username,user.password);
            userMap.put(user.username, user.password);
        }
    }

    public void createNewUser(String userName, String userPassword) {
        User newUser = new User(userName, userPassword);
    }

    public boolean isValidUser(String username, String password){
        if (userMap.get(username) == password){
            return true;
        }else{
            return false;
        }
    }

}
