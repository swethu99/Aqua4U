package com.example.jahnavigottimukkala.proj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    //SQLiteDatabase db;
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="registeruser";
    public static final String TABLE_NAME1="cart";
    SQLiteDatabase database;

    public static final String COL_1="ID";
    public static final String COL_2="username";
    public static final String COL_3="password";
    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser(ID INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE cart(ID INTEGER PRIMARY KEY AUTOINCREMENT,itemname, quantity , cost)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(sqLiteDatabase);
    }
    public long addUser(String user,String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",pass);
        long res=db.insert("registeruser",null,contentValues);
        db.close();
        return res;
    }

    public long addUser1(String itemname,String quantity, String cost){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("itemname",itemname);
        contentValues.put("quantity",quantity);
        contentValues.put("cost" ,cost);
        long res=db.insert("cart",null,contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

    public boolean checkUser1(String itemname,String quantity, String cost){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { itemname, quantity, cost };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        Cursor c = db.rawQuery("SELECT * FROM cart", null);
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext())
        {
            buffer.append("Rollno: " + c.getString(0) + "\n");
            buffer.append("Name: " + c.getString(1) + "\n");
            buffer.append("Marks: " + c.getString(2) + "\n\n");
        }
        // Displaying all records 
        showMessage("Student Details", buffer.toString());


        if(count>0)
            return  true;
        else
            return  false;
    }

    public void showMessage(String title, String message){
       /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();*/
        //Toast.makeText(cart2.this,title,Toast.LENGTH_LONG).show();
    }

}
