package glory.dataskp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private Toolbar toolbar;
    NavigationView navigationView;
    Intent i;
    private String namaTerimaMain,namaProfil;
    private TextView txtJudulDrawer,txtJudulMain;
    public Profil profil;
    //private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profil = new Profil();




        Intent intent = getIntent();
        namaTerimaMain = intent.getExtras().getString("nama_pegawaimain");
        txtJudulDrawer = (TextView) findViewById(R.id.txtjudulDrawer);
        txtJudulMain = (TextView) findViewById(R.id.txtjudulUtama);
        profil.setNamaProfil(namaTerimaMain);
        txtJudulMain.setText(profil.getNamaProfil());


        toolbar = (Toolbar) findViewById(R.id.layout_toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //set nama profil di header
        View header = navigationView.getHeaderView(0);
        txtJudulDrawer = (TextView)header.findViewById(R.id.txtjudulDrawer);
        txtJudulDrawer.setText(namaTerimaMain);



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
                        i = new Intent(MainActivity.this,PenjelasanPegawaiActivity.class);
                        i.putExtra("nama_pegawai",namaTerimaMain);
                        startActivity(i);
                        return true;


                    case R.id.navigation2:
                        Toast.makeText(getApplicationContext(),"SKP di klik",Toast.LENGTH_SHORT).show();
                        i = new Intent(MainActivity.this,LihatSKPActivity.class);
                        i.putExtra("nama_pegawai",namaTerimaMain);
                        startActivity(i);
                        return true;


                    case R.id.navigation3:
                        Toast.makeText(getApplicationContext(),"Tentang di klik",Toast.LENGTH_SHORT).show();
                        i = new Intent(MainActivity.this,TentangActivity.class);
                        i.putExtra("nama_pegawai",namaTerimaMain);
                        startActivity(i);
                        return true;

                    case R.id.navigation4:
                        Toast.makeText(getApplicationContext(),"Logout di klik",Toast.LENGTH_SHORT).show();
                        i = new Intent(MainActivity.this,LoginActivity.class);
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
    @Override
    public void onBackPressed(){

        //super.onBackPressed();
        new AlertDialog.Builder(this).setMessage("Apa anda yakin ingin keluar ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //MainActivity.this.finish();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            finishAffinity();
                        }
                        System.exit(0);


                    }
                })
                .setNegativeButton("Tidak",null).show();


    }








    public void klikDrawerHome(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);
    }


}
