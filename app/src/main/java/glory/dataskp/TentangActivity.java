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

import org.w3c.dom.Text;

public class TentangActivity extends AppCompatActivity {

    TextView txtp1,txtJudulDrawer;
    String p1;
    DrawerLayout drawerLayout;
    private Toolbar toolbar;
    NavigationView navigationView;
    Intent i;
    private  String namaTerima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
        namaTerima = getIntent().getExtras().get("nama_pegawai").toString();

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

        txtp1 = (TextView) findViewById(R.id.txtpenjelTentang1);

        p1 = "Aplikasi ini berguna untuk agar pegawai di Balai Pengkajian Teknologi Pertanian \n"+
                "dapat menginputkan Sasaran Kerja Pegawai ( SKP ) dan dapat dilihat secara mobile" +
                "\n";
        txtp1.setText(p1);


    }

    public void klikDrawerHome(View view) {
        drawerLayout.openDrawer(Gravity.LEFT);
    }
}
