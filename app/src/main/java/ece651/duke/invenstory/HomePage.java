package ece651.duke.invenstory;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

    Button camera, list;

    Button bLogout;
    TextView etUsername;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        etUsername = (TextView) findViewById(R.id.etUsername);
        bLogout = (Button) findViewById(R.id.bLogout);
        camera = (Button) findViewById(R.id.btnCamera);
        list = (Button) findViewById(R.id.btnList);

        camera.setOnClickListener(this);
        list.setOnClickListener(this);
        bLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

        //this.deleteDatabase("collection.db");
    }

    @Override
    protected void onStart(){
        super.onStart();
        /*start app, authenticate user*/
        if (authenticate() == true){
            displayUserDetails();
        }else{
            startActivity(new Intent(this, Login.class));
        }
    }

    private void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();
        etUsername.setText(user.username);
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCamera:
                startActivity(new Intent(this, PhotoActivity.class));
                break;
            case R.id.btnList:
                startActivity(new Intent(this, ShowList.class));
                break;
            case R.id.bLogout:
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, Login.class));
                break;
        }
    }
}
