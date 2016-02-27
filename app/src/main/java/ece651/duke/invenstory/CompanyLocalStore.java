package ece651.duke.invenstory;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by petermurphy on 2/23/16.
 */
public class CompanyLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences companyLocalDatabase;

    public CompanyLocalStore(Context context){
        companyLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storedCompanyData(Company company){
        SharedPreferences.Editor spEditor = companyLocalDatabase.edit();
        spEditor.putString("CompanyName", company.companyName);
        spEditor.putString("Password", company.password);
        spEditor.commit();
    }

    public Company getLoggedInCompany(User user){
        String companyName = companyLocalDatabase.getString("CompanyName", "");
        String password = companyLocalDatabase.getString("Password", "");
        Company storedCompany =  new Company(companyName, password, user);
        return storedCompany;
    }

    public void setCompanyLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = companyLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = companyLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

    public boolean getCompanyLoggedIn(){
        if (companyLocalDatabase.getBoolean("loggedIn", false) == true){
            return true;
        }else{
            return false;
        }
    }
}
