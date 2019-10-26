package com.example.android_developer_certification_tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ContentProviderExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_content_provider_example );
    }
    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.StudentText )).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText)findViewById(R.id.GradeText)).getText().toString());

        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }
    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.android_developer_certification_tutorial.StudentsProvider";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
    public void onClickDeleteStudent(View view){
        // delete a student record
        String URL = "content://com.example.android_developer_certification_tutorial.StudentsProvider";
        Uri students = Uri.parse(URL);

        String SName = ((EditText)findViewById(R.id.StudentText )).getText().toString();
        int success = getContentResolver().delete( StudentsProvider.CONTENT_URI,StudentsProvider.NAME + "=" + "\"" +SName+"\"",
                null);
        Toast.makeText(this,SName+"deleted"+ success, Toast.LENGTH_SHORT).show();

    }
    public void onClickUpdateStudent(View view){
        // update a student record
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.StudentText )).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText)findViewById(R.id.GradeText)).getText().toString());
        String SName = ((EditText)findViewById(R.id.StudentText )).getText().toString();
        int success = getContentResolver().update(StudentsProvider.CONTENT_URI, values,
                StudentsProvider.NAME + " = " +"\""+ SName+"\"",null);

        Toast.makeText(this,SName+"updated"+ success, Toast.LENGTH_SHORT).show();

    }
}
