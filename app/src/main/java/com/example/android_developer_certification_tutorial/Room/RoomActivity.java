package com.example.android_developer_certification_tutorial.Room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_developer_certification_tutorial.R;

import java.util.List;

public class RoomActivity extends AppCompatActivity {
    RoomDb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_room );
        db = db.getRoomDb(this);
    }

    public void onClickAddName(View view) {
        RoomEntity entity = new RoomEntity();
        String CurrName = ((EditText)findViewById(R.id.NameEditText)).getText().toString();
        entity.setFirstName(CurrName);
        int CurrAge = Integer.parseInt(((EditText)findViewById(R.id.AgeEfitText)).getText().toString());
        entity.setAge(CurrAge);
        db.entityDao().insertAll(entity);
        Toast.makeText(getBaseContext(),
                "Added entity", Toast.LENGTH_LONG).show();
    }
    public void onClickRetrieve(View view) {
        List<RoomEntity> Entries = db.entityDao().getAll();
        Toast.makeText(getBaseContext(),
                "Retrieved entities", Toast.LENGTH_LONG).show();
    }
    public void onClickDelete(View view){
        String CurrName = ((EditText)findViewById(R.id.NameEditText)).getText().toString();
        db.entityDao().delete(CurrName);
        Toast.makeText(getBaseContext(),
                "Entry Deleted", Toast.LENGTH_LONG).show();
    }
    public void onClickCount(View view){
        int count = db.entityDao().countEntities();
        Toast.makeText(getBaseContext(),
                "No of Entities = "+count, Toast.LENGTH_LONG).show();
    }
}
