package glory.dataskp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class PenjelasanPegawaiActivity extends AppCompatActivity {

    private TextView txtNama,txtNip,txtJabatan,txtPangkat,txtUnit,txtJudulDrawer;
    private String nama,nip,jabatan,pangkat,unit,namaTerima,pass;
    private DatabaseReference root;
    private int  temp_id;
    private long idPegawai;


    DrawerLayout drawerLayout;
    private Toolbar toolbar;
    NavigationView navigationView;
    Intent i;
    public Profil profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjelasan_pegawai);
        profil = new Profil();
        //profil.setNamaProfil("arito");

        txtNama = (TextView) findViewById(R.id.txtpenjelNama);
        txtNip = (TextView) findViewById(R.id.txtpenjelNip);
        txtJabatan = (TextView) findViewById(R.id.txtpenjelJabatan);
        txtPangkat = (TextView) findViewById(R.id.txtpenjelPangkat);
        txtUnit = (TextView) findViewById(R.id.txtpenjelUnit);

        //nama_room = getIntent().getExtras().get("room_name").toString();
        namaTerima = getIntent().getExtras().get("nama_pegawai").toString();
        profil.setNamaProfil(namaTerima);
        root = FirebaseDatabase.getInstance().getReference().child("staff").child(namaTerima);

        Random random = new Random(1000);
        temp_id = random.nextInt();
        /*DatabaseReference pesan_root = root.child("id");
                pesan_root.setValue(temp_id);*/

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ambilDataPegawai(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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




    private void ambilDataPegawai(DataSnapshot dataSnapshot){

        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()){

            idPegawai = (long)((DataSnapshot)i.next()).getValue();
            jabatan  = (String)((DataSnapshot)i.next()).getValue();
            nama = (String) ((DataSnapshot)i.next()).getValue();
            nip = (String) ((DataSnapshot)i.next()).getValue();
            pangkat = (String) ((DataSnapshot)i.next()).getValue();
            //pass = (String) ((DataSnapshot)i.next()).getValue();
            unit = (String) ((DataSnapshot)i.next()).getValue();


            //text_obrolan.append(username_chat+ " : "+pesan_chat + "\n");

        }
        txtNama.setText(nama+" ");
        txtJabatan.setText(jabatan+" ");
        txtNip.setText(nip+" ");
        txtPangkat.setText(pangkat+" ");
        txtUnit.setText(unit+" ");

    }


    public void klikDrawerHome(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);
    }
}
