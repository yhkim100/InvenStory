package ece651.duke.invenstory;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by petermurphy on 2/22/16.
 */
public class UserLocalStore {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storedUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("Username", user.username);
        spEditor.putString("Password", user.password);
        spEditor.putString(user.username, user.password);
        spEditor.commit();
    }

    public boolean checkLocalDatabase(User user){
        SharedPreferences spEditor = userLocalDatabase;
        String pwString = spEditor.getString(user.username, "NO_USER");
        if (pwString.equals(user.password)){
            return true;
        }else {
            return false;
        }
    }

    public User getLoggedInUser(){
        String username = userLocalDatabase.getString("Username", "");
        String password = userLocalDatabase.getString("Password", "");
        User storedUser =  new User(username, password);
        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("loggedIn", false) == true){
            return true;
        }else{
             return false;
        }
    }

}
