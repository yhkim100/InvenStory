package ece651.duke.invenstory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Young-hoon Kim on 2/19/2016.
 */
public class CollectionFunction {
    private DBHelper dbHelper;

    CollectionFunction(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean insert(Item item){
        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Item.KEY_Title, item.item_Title);
        values.put(Item.KEY_Author, item.author);
        values.put(Item.KEY_Quantity, item.quantity);
        values.put(Item.KEY_Price, item.price);

        // Inserting Row
        long item_Id = db.insert(Item.TABLE, null, values);
        db.close(); // Closing database connection
        return true;
        //return (int) item_Id;
    }

    public void delete(int item_ID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Item.TABLE, Item.KEY_ID + "= ?", new String[]{String.valueOf(item_ID)});
        db.close(); // Closing database connection
    }

    public void update(Item item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Item.KEY_Title, item.item_Title);
        values.put(Item.KEY_Author, item.author);
        values.put(Item.KEY_Price, item.price);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Item.TABLE, values, Item.KEY_ID + "= ?", new String[]{String.valueOf(item.id)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getItemList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Item.KEY_ID + "," +
                Item.KEY_Title + "," +
                Item.KEY_Author + "," +
                Item.KEY_Price +
                " FROM " + Item.TABLE;

        //Item item = new item();
        ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if(cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> item = new HashMap<String, String>();
                    item.put("id", cursor.getString(cursor.getColumnIndex(Item.KEY_ID)));
                    item.put("title", cursor.getString(cursor.getColumnIndex(Item.KEY_Title)));
                    itemList.add(item);

                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        db.close();
        return itemList;

    }

    public Item getItemById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Item.KEY_ID + "," +
                Item.KEY_Title + "," +
                Item.KEY_Author + "," +
                Item.KEY_Price +
                " FROM " + Item.TABLE
                + " WHERE " +
                Item.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Item item = new Item();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if(cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    item.id = cursor.getInt(cursor.getColumnIndex(Item.KEY_ID));
                    item.item_Title = cursor.getString(cursor.getColumnIndex(Item.KEY_Title));
                    item.author = cursor.getString(cursor.getColumnIndex(Item.KEY_Author));
                    item.price = cursor.getInt(cursor.getColumnIndex(Item.KEY_Price));

                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        db.close();
        return item;
    }


}
