package com.example.android_developer_certification_tutorial.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_developer_certification_tutorial.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String[] mRecyclerViewDataset = {"A", "B", "C", "D", "E"};
    static View.OnClickListener mOnClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recycler_view );
        recyclerView = findViewById( R.id.recyclerView );

        mOnClickListener = new RecyclerViewOnClickListener(this);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize( true );

        // use a linear layout manager
        layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );

        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewAdapter( mRecyclerViewDataset );
        recyclerView.setAdapter( mAdapter );
    }


    private class RecyclerViewOnClickListener implements View.OnClickListener {

        private final Context context;

        private RecyclerViewOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            removeItem( v );
        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition( v );
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForPosition( selectedItemPosition );
            TextView textView = viewHolder.itemView.findViewById( 100 );
            String selectedName = (String) textView.getText();
            for(int i =0;i<mRecyclerViewDataset.length;i++)
                    if(selectedName.equals(mRecyclerViewDataset[i])) {
                        mRecyclerViewDataset[i] = "Removed";
                        break;
                    }
            mAdapter.notifyItemChanged( selectedItemPosition );
        }
    }
}



