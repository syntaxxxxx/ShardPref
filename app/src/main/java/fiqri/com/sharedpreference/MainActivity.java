package fiqri.com.sharedpreference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private TextView tvNama, tvUmur, tvPhone,
            tvEmail, tvLove;
    private Button btnSave;
    private Orang orangPref;
    private boolean isPrefEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNama = findViewById(R.id.tv_nama);
        tvUmur = findViewById(R.id.tv_umur);
        tvPhone = findViewById(R.id.tv_phone);
        tvEmail = findViewById(R.id.tv_email);
        tvLove = findViewById(R.id.tv_love);
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);

        orangPref = new Orang(this);

        getSupportActionBar().setTitle("User Pref");

        showPref();
    }


    private void showPref() {
        if (!TextUtils.isEmpty(orangPref.getName())) {
            tvNama.setText(orangPref.getName());
            tvUmur.setText(String.valueOf(orangPref.getUmur()));
            tvLove.setText(orangPref.isLove() ? "Yes" : "No");
            tvEmail.setText(orangPref.getEmail());
            tvPhone.setText(orangPref.getPhone());

            btnSave.setText("Ubah");

        } else {
            final String TEXT_EMPTY = "Tidak Ada";
            tvNama.setText(TEXT_EMPTY);
            tvUmur.setText(TEXT_EMPTY);
            tvLove.setText(TEXT_EMPTY);
            tvEmail.setText(TEXT_EMPTY);
            tvPhone.setText(TEXT_EMPTY);

            btnSave.setText("Simpan");

            isPrefEmpty = true;
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            Intent i = new Intent(MainActivity.this, InputanPreference.class);
            if (isPrefEmpty) {
                i.putExtra(InputanPreference.EXTRA_TYPE,
                        InputanPreference.TYPE_ADD);

            } else {
                i.putExtra(InputanPreference.EXTRA_TYPE,
                        InputanPreference.TYPE_EDIT);
            }

            startActivityForResult(i, InputanPreference.REQUEST_CODE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == InputanPreference.REQUEST_CODE) {
            showPref();
        }
    }
}
