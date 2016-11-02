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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class PenjelasanSKPActivity extends AppCompatActivity {

    private TextView txtKegiatan,txtAngkaKredit,txtKuantitas,txtKualitas,txtOutput,txtBulan;
    private String kegiatan,AngKredit,kuantitas,kualitas,output,bulan,skpTerima;
    private DatabaseReference root;
    private int  temp_id;
    private long idPegawai;


    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjelasan_skp);

        skpTerima = getIntent().getExtras().get("nama_skp").toString();
        root = FirebaseDatabase.getInstance().getReference().child("skp").child(skpTerima);

        txtKegiatan = (TextView) findViewById(R.id.txtpenjelKegiatan);
        txtAngkaKredit= (TextView) findViewById(R.id.txtpenjelAngkaKredit);
        txtKuantitas= (TextView) findViewById(R.id.txtpenjelKuantitas);
        txtKualitas= (TextView) findViewById(R.id.txtpenjelKualitas);
        txtOutput= (TextView) findViewById(R.id.txtpenjelOutput);
        txtBulan= (TextView) findViewById(R.id.txtpenjelBulan);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ambilDataSKP(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }




    private void ambilDataSKP(DataSnapshot dataSnapshot){

        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()){

            //idPegawai = (long)((DataSnapshot)i.next()).getValue();
            AngKredit  = (String)((DataSnapshot)i.next()).getValue();
            kegiatan = (String) ((DataSnapshot)i.next()).getValue();
            kualitas = (String) ((DataSnapshot)i.next()).getValue();
            kuantitas = (String) ((DataSnapshot)i.next()).getValue();
            output = (String) ((DataSnapshot)i.next()).getValue();
            bulan = (String) ((DataSnapshot)i.next()).getValue();


            //text_obrolan.append(username_chat+ " : "+pesan_chat + "\n");

        }
        txtKegiatan.setText(kegiatan+" ");
        txtAngkaKredit.setText(AngKredit+" ");
        txtKuantitas.setText(kuantitas+" ");
        txtKualitas.setText(kualitas+" ");
        txtOutput.setText(output+" ");
        txtBulan.setText(""+ bulan+" Bulan");

    }


}
