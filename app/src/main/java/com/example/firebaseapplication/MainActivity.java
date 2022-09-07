package com.example.firebaseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.CDATASection;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView send;
    private EditText message;
    private ListView listView;
    message msg;
    ArrayList<String> msglist;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = findViewById(R.id.imageButton);
        message = findViewById(R.id.editText);
        listView = findViewById(R.id.listview);
        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://fir-1-project-2752f-default-rtdb.firebaseio.com/chat/");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                message msg = new message("Chitrakshi",message.getText().toString());
                ref.push().setValue(msg);
                message.setText("");
            }
        });

        msg = new message();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mref = firebaseDatabase.getReference("chat");
        msglist = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.listlayout, R.id.textView, msglist);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                msglist.clear();
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    msg = ds.getValue(message.class);
                    msglist.add(msg.getName() + "\n" + msg.getMessage());
                }listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });





    }
}