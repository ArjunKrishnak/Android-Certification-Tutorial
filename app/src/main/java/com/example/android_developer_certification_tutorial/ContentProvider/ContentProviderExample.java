package com.example.android_developer_certification_tutorial.ContentProvider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_developer_certification_tutorial.R;

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
                ((EditText)findViewById(R.id.NameEditText )).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText)findViewById(R.id.AgeEfitText )).getText().toString());

        Uri uri = getContentResolver().insert(//Content Providers are triggered by content resolvers and not intents
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }
    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.android_developer_certification_tutorial.ContentProvider.StudentsProvider";

        Uri students = Uri.parse(URL);
        Cursor c =  getContentResolver().query(students,null,null,null,"name");
//        Cursor c = managedQuery(students, null, null, null, "name");

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
        String URL = "content://com.example.android_developer_certification_tutorial.ContentProvider.StudentsProvider";
        Uri students = Uri.parse(URL);

        String SName = ((EditText)findViewById(R.id.NameEditText )).getText().toString();
        int success = getContentResolver().delete( StudentsProvider.CONTENT_URI,StudentsProvider.NAME + "=" + "\"" +SName+"\"",
                null);
        Toast.makeText(this,SName+"deleted"+ success, Toast.LENGTH_SHORT).show();

    }
    public void onClickUpdateStudent(View view){
        // update a student record
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.NameEditText )).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText)findViewById(R.id.AgeEfitText )).getText().toString());
        String SName = ((EditText)findViewById(R.id.NameEditText )).getText().toString();
        int success = getContentResolver().update(StudentsProvider.CONTENT_URI, values,
                StudentsProvider.NAME + " = " +"\""+ SName+"\"",null);

        Toast.makeText(this,SName+"updated"+ success, Toast.LENGTH_SHORT).show();

    }
}
