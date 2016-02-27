package ece651.duke.invenstory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button bRegister;
    EditText etUsername, etCompany,etcompanyPassword, etPassword;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etCompany = (EditText)findViewById(R.id.etCompany);
        etcompanyPassword = (EditText)findViewById(R.id.etcompanyPassword);

        bRegister = (Button)findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        userLocalStore = new UserLocalStore(this);
        switch (v.getId()) {
            case R.id.bRegister:
                String companyName = etCompany.getText().toString();
                String companyPassword = etcompanyPassword.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if (!(username == null) || (password == null)){
                    User newUser = new User (username, password);
                    userLocalStore.storedUserData(newUser);
                    startActivity(new Intent(this, Login.class));
                    break;
                }
        }
    }



}
