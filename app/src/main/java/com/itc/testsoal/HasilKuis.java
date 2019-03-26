package com.itc.testsoal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HasilKuis extends AppCompatActivity {

    @BindView(R.id.tv_hasil)
    TextView tvHasil;
    @BindView(R.id.tv_nilai)
    TextView tvNilai;
    @BindView(R.id.btn_ulangi)
    Button btnUlangi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_kuis);
        ButterKnife.bind(this);
        tvHasil.setText("Jawaban Benar : " + Listening.benar + "\n Jawaban Salah : " + Listening.salah);
        tvNilai.setText("" + Listening.hasil);
    }

    @OnClick(R.id.btn_ulangi)
    public void onViewClicked() {
        finish();
        Intent i = new Intent(getApplicationContext(), Listening.class);
        startActivity(i);
    }
}

