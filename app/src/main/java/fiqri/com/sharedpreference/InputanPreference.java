package fiqri.com.sharedpreference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class InputanPreference extends AppCompatActivity
        implements View.OnClickListener {

    private EditText edtNama, edtEmail,
            edtPhone, edtUmur;
    private RadioGroup rgLove;
    private RadioButton rbYes, rbNo;
    private Button btnSave;

    public static String EXTRA_TYPE = "extra_type";
    public static int REQUEST_CODE = 100;

    public static int TYPE_ADD = 1;
    public static int TYPE_EDIT = 2;

    final String INPUTAN_EMPTY = "Tidak Boleh Kosong";
    final String INPUTAN_DIGIT = "Hanya Boleh Terisi Numerik";
    final String INPUTAN_EMAIL_VALID = "Email Tidak Valid";

    private Orang orangPref;

    int formType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputan_preference);

        edtNama = findViewById(R.id.edt_nama);
        edtUmur = findViewById(R.id.edt_umur);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);
        rgLove = findViewById(R.id.rg_love_mu);
        rbYes = findViewById(R.id.rb_yes);
        rbNo = findViewById(R.id.rb_no);
        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);

        formType = getIntent().getIntExtra(EXTRA_TYPE, 0);

        orangPref = new Orang(this);

        String actionBarTitle = null;
        String btnTitle = null;

        if (formType == 1) {
            actionBarTitle = "New Add";
            btnTitle = "Save";

        } else {
            actionBarTitle = "Ubah";
            btnTitle = "Update";

            showPrefInFrom();
        }

        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSave.setText(btnTitle);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void showPrefInFrom() {
        edtNama.setText(orangPref.getName());
        edtEmail.setText(orangPref.getEmail());
        edtUmur.setText(String.valueOf(orangPref.getUmur()));
        edtPhone.setText(orangPref.getPhone());

        if (orangPref.isLove()) {
            rbYes.setChecked(true);

        } else {
            rbNo.setChecked(false);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {

            String nama = edtNama.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String umur = edtUmur.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();

            boolean love = rgLove.getCheckedRadioButtonId() == R.id.rb_yes;
            boolean isEmpty = false;

            if (TextUtils.isEmpty(nama)) {
                isEmpty = true;
                edtNama.setError(INPUTAN_EMPTY);
            }

            if (TextUtils.isEmpty(email)) {
                isEmpty = true;
                edtEmail.setError(INPUTAN_EMPTY);

            } else if (!isValidEmail(email)) {
                isEmpty = true;
                edtEmail.setError(INPUTAN_EMAIL_VALID);
            }

            if (TextUtils.isEmpty(umur)) {
                isEmpty = true;
                edtUmur.setError(INPUTAN_EMPTY);
            }

            if (TextUtils.isEmpty(phone)) {
                isEmpty = true;
                edtPhone.setError(INPUTAN_EMPTY);

            } else if (!TextUtils.isDigitsOnly(phone)) {
                isEmpty = true;
                edtPhone.setError(INPUTAN_DIGIT);
            }

            // validasi data sebelum disimpan
            if (!isEmpty) {
                orangPref.setName(nama);
                orangPref.setUmur(Integer.parseInt(umur));
                orangPref.setEmail(email);
                orangPref.setPhone(phone);
                orangPref.setLove(love);

                Toast.makeText(this, "Data Terimpan",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        }
    }


    private boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email)
                && android.util.Patterns
                .EMAIL_ADDRESS.matcher(email).matches();
    }
}










