package glory.dataskp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class InputSKPActivity extends AppCompatActivity {

    EditText etKegiatan,etAngkaKredit,etKuantitas,etMutu,etWaktu,etOutput;
    private DatabaseReference root;
    private String temp_key,temp_namaSKP;
    int temp_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_skp);
        root = FirebaseDatabase.getInstance().getReference().child("skp");

        etKegiatan = (EditText) findViewById(R.id.editKegiatan);
        etAngkaKredit = (EditText) findViewById(R.id.editKredit);
        etKuantitas = (EditText) findViewById(R.id.editKuantitas);
        etMutu = (EditText) findViewById(R.id.editKualitas);
        etWaktu = (EditText) findViewById(R.id.editWaktu);
        etOutput = (EditText) findViewById(R.id.editOutput);


        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(getBaseContext(), "Data di Input", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void btnSimpanSKP(View view) {

        Map<String, Object> map = new HashMap<String, Object>();
        temp_namaSKP = etKegiatan.getText().toString();

        root.push().child(temp_namaSKP);

        root.updateChildren(map);

        //DatabaseReference pesan_root = root.child(temp_key);
        DatabaseReference namaPegawai_root = root.child(temp_namaSKP);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("kegiatan", etKegiatan.getText().toString());
        map2.put("angka", etAngkaKredit.getText().toString());
        map2.put("kuantitas", etKuantitas.getText().toString());
        map2.put("kualitas", etMutu.getText().toString());
        map2.put("output", etOutput.getText().toString());
        map2.put("waktu", etWaktu.getText().toString());

        namaPegawai_root.updateChildren(map2);
        dialogPemastian();
    }

    private void dialogPemastian(){

        new AlertDialog.Builder(this).setMessage("Apakah ingin Input data lagi ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //MainActivity.this.finish();
                        etKegiatan.setText("");
                        etAngkaKredit.setText("");
                        etKuantitas.setText("");
                        etMutu.setText("");
                        etOutput.setText("");
                        etWaktu.setText("");
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getBaseContext(),LoginActivity.class);
                        startActivity(i);
                    }
                }).show();
    }
}
