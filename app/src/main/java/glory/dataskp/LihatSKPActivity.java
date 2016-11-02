package glory.dataskp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Adapter.RecycleAdapter;
import Adapter.RecycleAdapterSKP;

public class LihatSKPActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListView listView;
    private DatabaseReference root;
    private String namaSKP,namaTerima;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_dari_skp = new ArrayList();

    DrawerLayout drawerLayout;
    private Toolbar toolbar;
    NavigationView navigationView;
    Intent i;
    TextView txtJudulDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_skp);
        namaTerima = getIntent().getExtras().get("nama_pegawai").toString();

        root = FirebaseDatabase.getInstance().getReference().child("skp");
        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_dari_skp);
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
                list_dari_skp.clear();
                list_dari_skp.addAll(set);

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
                Intent i = new Intent(getApplicationContext(), PenjelasanSKPActivity.class);
                i.putExtra("nama_skp", ((TextView) view).getText().toString());
                startActivity(i);

            }
        });

        toolbar = (Toolbar) findViewById(R.id.layout_toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View header = navigationView.getHeaderView(0);
        txtJudulDrawer = (TextView)header.findViewById(R.id.txtjudulDrawer);
        txtJudulDrawer.setText(namaTerima);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);

                drawerLayout.closeDrawers();


                switch (item.getItemId()){
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.navigation1:
                        Toast.makeText(getApplicationContext(), "Profil di klik", Toast.LENGTH_SHORT).show();
                        i = new Intent(getApplicationContext(),PenjelasanPegawaiActivity.class);
                        i.putExtra("nama_pegawai",namaTerima);
                        startActivity(i);
                        return true;


                    case R.id.navigation2:
                        Toast.makeText(getApplicationContext(),"SKP di klik",Toast.LENGTH_SHORT).show();
                        i = new Intent(getApplicationContext(),LihatSKPActivity.class);
                        i.putExtra("nama_pegawai",namaTerima);
                        startActivity(i);
                        return true;


                    case R.id.navigation3:
                        Toast.makeText(getApplicationContext(),"Tentang di klik",Toast.LENGTH_SHORT).show();
                        i = new Intent(getApplicationContext(),TentangActivity.class);
                        i.putExtra("nama_pegawai",namaTerima);
                        startActivity(i);
                        return true;

                    case R.id.navigation4:
                        Toast.makeText(getApplicationContext(),"Logout di klik",Toast.LENGTH_SHORT).show();
                        i = new Intent(getApplicationContext(),LoginActivity.class);
                        i.putExtra("cekKirim",0);
                        startActivity(i);
                        return true;


                    default:
                        Toast.makeText(getApplicationContext(),"Terjadi kesalahan",Toast.LENGTH_SHORT).show();
                        return true;

                }


            }
        });



        //inisialiasi drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.openDrawer,R.string.closeDrawer)

        {
            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView){

                super.onDrawerOpened(drawerView);
            }

        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


    }

    public void klikDrawerHome(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    public void btnInputSKP(View view) {
        i = new Intent(getApplicationContext(),InputSKPActivity.class);
        startActivity(i);
    }
}
