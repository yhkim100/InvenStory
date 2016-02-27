package ece651.duke.invenstory;

/**
 * Created by Young-hoon Kim on 2/19/2016.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collection;

public class DetailedListView extends AppCompatActivity implements android.view.View.OnClickListener {

    Button btnSave, btnDelete;
    Button btnClose;
    EditText editTextTitle;
    EditText editTextAuthor;
    EditText editTextPrice;
    EditText editTextQuantity;
    private int _Item_Id = 0;
    int id_To_Update = 0;
    private DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_list_view);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextAuthor = (EditText) findViewById(R.id.editTextAuthor);
        editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        DBHelper mydb = new DBHelper(this);


        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");

            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String title = rs.getString(rs.getColumnIndex(Item.KEY_Title));
                String author = rs.getString(rs.getColumnIndex(Item.KEY_Author));
                String quantity = rs.getString(rs.getColumnIndex(Item.KEY_Quantity));
                String price = rs.getString(rs.getColumnIndex(Item.KEY_Price));

                if (!rs.isClosed())
                {
                    rs.close();
                }

                editTextTitle.setText((CharSequence) title);
                editTextTitle.setFocusable(false);
                editTextTitle.setClickable(false);

                editTextAuthor.setText((CharSequence) author);
                editTextAuthor.setFocusable(false);
                editTextAuthor.setClickable(false);

                editTextQuantity.setText((CharSequence) quantity);
                editTextQuantity.setFocusable(false);
                editTextQuantity.setClickable(false);

                editTextPrice.setText((CharSequence) price);
                editTextPrice.setFocusable(false);
                editTextPrice.setClickable(false);
            }
        }
    }


    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnSave:
                //Toast.makeText(this, "SAVE FUNCTION IS CURRENTLY BROKEN", Toast.LENGTH_SHORT).show();
                try {
                    CollectionFunction repo = new CollectionFunction(this);
                    Item item = new Item();
                    item.price = Integer.parseInt(editTextPrice.getText().toString());
                    item.author = editTextAuthor.getText().toString();
                    item.item_Title = editTextTitle.getText().toString();
                    item.quantity = Integer.parseInt(editTextQuantity.getText().toString());
                    repo.insert(item);
                    Toast.makeText(this, "Added New Item", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(this, "FAILED TO MAKE ITEM", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnDelete:
                final CollectionFunction repo = new CollectionFunction(this);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteContact)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                repo.delete(id_To_Update);
                                Intent intent = new Intent(getApplicationContext(),ShowList.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }
}
