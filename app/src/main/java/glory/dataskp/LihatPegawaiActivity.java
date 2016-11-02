package glory.dataskp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Adapter.RecycleAdapter;

public class LihatPegawaiActivity extends AppCompatActivity {


    ListView listView;
    private DatabaseReference root;
    private String namaPegawai;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_dari_pegawai = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pegawai);

        root = FirebaseDatabase.getInstance().getReference().child("pegawai");
        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_dari_pegawai);
        listView.setAdapter(arrayAdapter);

        //mengambil semua child di database, dan ditampilkan sebagai listview
        root.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while (i.hasNext()) {
                    set.add(((com.google.firebase.database.DataSnapshot) i.next()).getKey());
                }
                list_dari_pegawai.clear();
                list_dari_pegawai.addAll(set);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //disini ada intent ke penjelasan pegawai
                Intent i = new Intent(getApplicationContext(), PenjelasanPegawaiActivity.class);
                i.putExtra("nama_pegawai", ((TextView) view).getText().toString());
                startActivity(i);

            }
        });

    }
}
