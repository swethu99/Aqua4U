import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jahnavigottimukkala.proj.R;

public class SQLite extends AppCompatActivity implements View.OnClickListener {

    EditText editRollno, editName, editMarks;
    Button btnAdd, btnDelete, btnModify, btnViewAll, btnShowInfo, btnView;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Creating database and table  
        SharedPreferences sp = getSharedPreferences("mycredentials",Context.MODE_PRIVATE);
        String name = sp.getString("name1","NA");
        String q = sp.getString("pass","NA");
        String cost = sp.getString("pass1","NA");
        db = openOrCreateDatabase("cart", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS cart(itemname VARCHAR,quantity VARCHAR,cost VARCHAR);");
        db.execSQL("INSERT INTO cart VALUES('" + name + "','" + q +
                "','" + cost + "');");

        Cursor c = db.rawQuery("SELECT * FROM cart", null);
        // Checking if no records found 
        if (c.getCount() == 0) {
            showMessage("Error", "No records found");
            return;
        }
        // Appending records to a string buffer 
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext())
        {
            buffer.append("Rollno: " + c.getString(0) + "\n");
            buffer.append("Name: " + c.getString(1) + "\n");
            buffer.append("Marks: " + c.getString(2) + "\n\n");
        }
        // Displaying all records 
        showMessage("My Cart Details", buffer.toString());
    }

    @Override
    public void onClick(View view) {
        // Adding a record
        // Deleting a record 
        if (view == btnDelete) {
            // Checking empty roll number 
            if (editRollno.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            // Searching roll number 
            Cursor c = db.rawQuery("SELECT * FROM cart WHERE rollno='" + editRollno.getText() + "'", null);
            if (c.moveToFirst()) {
                // Deleting record if found 
                showMessage("Success", "Record Deleted");
                db.execSQL("DELETE FROM student WHERE rollno='" + editRollno.getText() + "'");
            } else {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
        // Modifying a record 
        if (view == btnModify) {
            // Checking empty roll number 
            if (editRollno.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            // Searching roll number 
            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "'", null);
            if (c.moveToFirst()) {
                // Modifying record if found 
                db.execSQL("UPDATE student SET name='" + editName.getText() + "',marks='" + editMarks.getText() +
                        "' WHERE rollno='" + editRollno.getText() + "'");
                showMessage("Success", "Record Modified");
            }
            else {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
        // Viewing a record 
        if (view == btnView) {
            // Checking empty roll number 
            if (editRollno.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            // Searching roll number 
            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "'", null);
            if (c.moveToFirst()) {
                // Displaying record if found 
                editName.setText(c.getString(1));
                editMarks.setText(c.getString(2));
            } else {
                showMessage("Error", "Invalid Rollno");
                clearText();
            }
        }
        // Viewing all records 
        if (view == btnViewAll) {
            // Retrieving all records 
            Cursor c = db.rawQuery("SELECT * FROM student", null);
            // Checking if no records found 
            if (c.getCount() == 0) {
                showMessage("Error", "No records found");
                return;
            }
            // Appending records to a string buffer 
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext())
            {
                buffer.append("Rollno: " + c.getString(0) + "\n");
                buffer.append("Name: " + c.getString(1) + "\n");
                buffer.append("Marks: " + c.getString(2) + "\n\n");
            }
            // Displaying all records 
            showMessage("Student Details", buffer.toString());
        }
        // Displaying info 
        if (view == btnShowInfo){
            showMessage("SQLite demo", "SQLite Connectivity");
        }
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText(){

        editRollno.setText("");
        editName.setText("");
        editMarks.setText("");
        editRollno.requestFocus();
    }
}
