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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InputPegawaiActivity extends AppCompatActivity {

    EditText etNama,etNip,etPangkat,etJabatan,etUnit,etPass,etUlangPass;
    private DatabaseReference root,root_pass;
    private String temp_key,temp_nama,PasswordJadi,temp_pass;
    int temp_id;
    public static ArrayList<String> arrPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pegawai);

        root = FirebaseDatabase.getInstance().getReference().child("staff");
        root_pass = FirebaseDatabase.getInstance().getReference().child("pass");

        etNama  = (EditText) findViewById(R.id.editdaftarNama);
        etNip  = (EditText) findViewById(R.id.editdaftarNip);
        etPangkat  = (EditText) findViewById(R.id.editdaftarPangkat);
        etJabatan  = (EditText) findViewById(R.id.editdaftarJabatan);
        etUnit  = (EditText) findViewById(R.id.editdaftarUnit);
        etPass  = (EditText) findViewById(R.id.editdaftarPass);
        etUlangPass  = (EditText) findViewById(R.id.editdaftarPassulang);
        Random random = new Random(1000);

        temp_id =  random.nextInt();
        arrPass = new ArrayList<>();


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

        root_pass.addChildEventListener(new ChildEventListener() {
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

    public void btnSimpanDP(View view) {

        //if (etPass.getText().toString().compareTo(etUlangPass.getText().toString()) == 0 ){
            PasswordJadi = etPass.getText().toString().trim();



            //ini buat data pegawai
            Map<String, Object> map = new HashMap<String, Object>();
            temp_nama = etNama.getText().toString();
            root.push().child(temp_nama);
            root.updateChildren(map);
            //DatabaseReference pesan_root = root.child(temp_key);
            DatabaseReference namaPegawai_root = root.child(temp_nama);
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("nama", etNama.getText().toString());
            map2.put("nip", etNip.getText().toString());
            map2.put("pangkat", etPangkat.getText().toString());
            map2.put("jabatan", etJabatan.getText().toString());
            map2.put("unit", etUnit.getText().toString());
            map2.put("id", temp_id);
            //map2.put("pass",etPass.getText().toString());
            namaPegawai_root.updateChildren(map2);
            ///////////////


            //ini buat pass
            Map<String, Object> mapPass = new HashMap<String, Object>();
            temp_pass = etPass.getText().toString();
            root_pass.push().child(temp_pass);
            root_pass.updateChildren(mapPass);
            //DatabaseReference pesan_root = root.child(temp_key);
            DatabaseReference namaPass_root = root_pass.child(temp_pass);
            Map<String, Object> mapPass2 = new HashMap<String, Object>();
            mapPass2.put("passnya", etPass.getText().toString());
            //map2.put("pass",etPass.getText().toString());
            namaPass_root.updateChildren(mapPass2);
            ///*/


            dialogPemastian();




    }

    private void dialogPemastian(){


        new AlertDialog.Builder(this).setMessage("Apakah ingin Input data lagi ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //MainActivity.this.finish();
                        etNama.setText("");
                        etNip.setText("");
                        etPangkat.setText("");
                        etJabatan.setText("");
                        etUnit.setText("");
                        etPass.setText("");
                        etUlangPass.setText("");
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i);
                    }
                }).show();
    }
}
