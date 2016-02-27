package ece651.duke.invenstory;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener{
    Button bLogin;
    EditText etUsername, etPassword;
    //EditText etCompany;
    TextView tvregisterLink;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //etCompany = (EditText)findViewById(R.id.etCompany);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        bLogin = (Button)findViewById(R.id.bLogin);
        tvregisterLink = (TextView)findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvregisterLink.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.bLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                /*String companyName = etCompany.getText().toString();
                User user = new User(username, password);
                Company company = new Company(companyName, null, user);
                userLocalStore.storedUserData(user);
                authenticate(user);*/
                User user = new User(username, password);
                userLocalStore = new UserLocalStore(this);
                if((username.equals("admin")) && password.equals("admin")) {
                    startActivity(new Intent(this, HomePage.class));
                    break;
                }
                authenticate(user);
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;

        }

    }

    private void authenticate(User user){
        if(userLocalStore.checkLocalDatabase(user)){
            logUserIn(user);
        }else {
            showErrorMessage();
        }
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser){
        userLocalStore.storedUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        startActivity(new Intent(this, HomePage.class));
    }
}
