package glory.dataskp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    EditText etUser,etPassword;
    private String user,namaKirim;
    private DatabaseReference root,root_pass,root_isiPegawai;
    private String nama,nip,jabatan,pangkat,unit,namaTerima,pass;
    private long idPegawai;
    Intent i;
    //private Set<String> set = new HashSet<String>();
    private ArrayList<String> list_dari_pegawai = new ArrayList();
    private ArrayList<String> list_dari_pass = new ArrayList();
    private int cek;
    boolean loggedIN = false;
    Profil profil;
    private String listPass[],listStaff[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        profil = new Profil();

        etUser = (EditText) findViewById(R.id.etuser);
        etPassword = (EditText) findViewById(R.id.etpass);
        root = FirebaseDatabase.getInstance().getReference().child("staff");
        root_pass = FirebaseDatabase.getInstance().getReference().child("pass");

        user = etUser.getText().toString().trim();
        //cek = i.getIntExtra("cekKirim", cek);
        cek =0;
        listPass = new String[100];
        listStaff = new String[100];



        //mengambil semua child di database, dan ditampilkan sebagai listview
        root.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while (i.hasNext()) {
                    set.add(((com.google.firebase.database.DataSnapshot) i.next()).getKey());
                }

                /*if (set.contains(user)){

                    //LoginBerhasil();

                }*/
                list_dari_pegawai.clear();
                list_dari_pegawai.addAll(set);


                //arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        root_pass.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while (i.hasNext()) {
                    set.add(((com.google.firebase.database.DataSnapshot) i.next()).getKey());
                }

                /*if (set.contains(user)){

                    //LoginBerhasil();

                }*/
                list_dari_pass.clear();
                list_dari_pass.addAll(set);


                //arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        for (int a=0;a<list_dari_pegawai.size();a++){

            listStaff[a] = list_dari_pegawai.get(a).toString();
        }

        for (int a=0;a<list_dari_pegawai.size();a++){

            listPass[a] = list_dari_pass.get(a).toString();
        }





    }

    public void klikLogin(View view) {

        for (int i = 0;i<list_dari_pegawai.size();i++){

            if (etUser.getText().toString().trim().compareTo(list_dari_pegawai.get(i)) == 0 ) {

                cek =  cek + 1;

            }

        }


        /////////
        for (int i = 0;i<list_dari_pass.size();i++){

            if (etPassword.getText().toString().trim().compareTo(list_dari_pass.get(i)) == 0 ) {

                cek =  cek + 1;

            }

        }


        if (cek == 2){
            Toast.makeText(this,"berhasil ",Toast.LENGTH_SHORT).show();
            LoginBerhasil();
        }


        /*if ( cek == 1){
            LoginBerhasil();

        }*///else
        //
        if (cek !=2){

            new AlertDialog.Builder(this).setMessage("Login Gagal, Cek username / password")
                    .setNegativeButton("OK", null).show();
        }

    }

    public void LoginBerhasil(){
        profil.setNamaProfil(etUser.getText().toString());
        i = new Intent(this,MainActivity.class);

        i.putExtra("nama_pegawaimain", etUser.getText().toString());
        startActivity(i);

    }

    public void klikBatal(View view) {
        new AlertDialog.Builder(this).setMessage("Apa anda yakin ingin keluar ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //LoginActivity.this.finish();
                        //finish();
                        LoginActivity.this.finishAffinity();
                    }
                })
                .setNegativeButton("Tidak",null).show();


    }

    public void klikDaftar(View view) {
        i = new Intent(this,InputPegawaiActivity.class);
        startActivity(i);
    }



}
