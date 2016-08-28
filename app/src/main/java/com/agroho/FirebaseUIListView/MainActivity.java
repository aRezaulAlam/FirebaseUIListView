package com.agroho.FirebaseUIListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView fruitList;
    EditText fruitNameEditText,editTextSummary;
    Button fruitSubmitButton;
    static boolean calledAlready = false;
    DatabaseReference firebaseRef;
    //DatabaseReference fruitRef = firebaseRef.child("Fruit");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        firebaseRef =  FirebaseDatabase.getInstance().getReference();

        fruitNameEditText = (EditText)findViewById(R.id.fruitNameEditText);
        fruitSubmitButton = (Button)findViewById(R.id.fruitSubmitButton);
        editTextSummary = (EditText)findViewById(R.id.editTextSummary);
        fruitList = (ListView)findViewById(R.id.fruitListView);

    }

    @Override
    protected void onStart(){
        super.onStart();


        fruitSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fruitName = fruitNameEditText.getText().toString();
                String fruitSummary = editTextSummary.getText().toString();
                String key = firebaseRef.child("Fruit").push().getKey();
                Fruit fruit = new Fruit(key,fruitName,fruitSummary);
                Map<String, Object> fruitValues = fruit.toMap();

                HashMap<String,Object> values = new HashMap<String, Object>();
                values.put("/Fruit/" + key, fruitValues);
                firebaseRef.updateChildren(values);
                fruitNameEditText.setText("");
                editTextSummary.setText("");
            }
        });

        FirebaseListAdapter<Fruit> adapter = new FirebaseListAdapter<Fruit>(this,Fruit.class,R.layout.fruitlist_row,firebaseRef.child("Fruit")) {
            @Override
            protected void populateView(View v, Fruit fruit, int position) {
                TextView textViewTitle = (TextView)v.findViewById(R.id.fruitTitle);
                TextView textViewSummary = (TextView)v.findViewById(R.id.fruitSummary);
                textViewTitle.setText(fruit.getFruitName());
                textViewSummary.setText(fruit.getFruitSummary());
            }
        };

        fruitList.setAdapter(adapter);
    }
}
