package com.itc.testsoal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Listening extends AppCompatActivity {


    @BindView(R.id.iv_soal_new)
    ImageView ivSoalNew;
    @BindView(R.id.tv_soal_new)
    TextView tvSoalNew;
    @BindView(R.id.rb_pilihan_a_new)
    RadioButton rbPilihanANew;
    @BindView(R.id.rb_pilihan_b_new)
    RadioButton rbPilihanBNew;
    @BindView(R.id.rb_pilihan_c_new)
    RadioButton rbPilihanCNew;
    @BindView(R.id.rb_pilihan_d_new)
    RadioButton rbPilihanDNew;
    @BindView(R.id.rg_pilihan_new)
    RadioGroup rgPilihanNew;
    @BindView(R.id.tv_penjelasan_new)
    TextView tvPenjelasanNew;
    @BindView(R.id.btn_play_new)
    Button btnPlayNew;
    @BindView(R.id.btn_stop_new)
    Button btnStopNew;

    int nomor = 0;
    public static int hasil, benar, salah;
    public static MediaPlayer mediaPlayer;

    String[] pertanyaan_kuis = new String[]{
            "1. salah satu personil snsd yang keluar dari groupnya pada tahun 2014, dan saya merasa sangat sedih adalah?",
            "2. Siapakah biasku di DBSK?",
            "3. Yang Bukan Anggota Blackpink antara lain?",
            "4. Aku harus nyanyi lagu apa hari minggu?",
            "5. ITZY adalah salah satu girlsband naungan agensi?"
    };

    String[] jawaban_kuis = new String[]{
            "Yuri", "Tifany", "Jessica", "Krystal",
            "Junsu", "Yunho", "Jaejoong", "Changmin",
            "Jennie", "Lisa", "Rose", "Jia",
            "kada tahu", "molla", "terserah", "apa aja deh boleh",
            "YG", "JYP", "SM", "Starship",
    };

    String[] jawaban_benar = new String[]{
            "Jessica",
            "Junsu",
            "Jia",
            "molla",
            "JYP",
    };

    int[] suaraSoal = new int[]{

            R.raw.q_dua,
            R.raw.q_tiga,
            R.raw.q_empat,
            R.raw.q_lima
    };

    int[] suaraSoalSatu = new int[]{
            R.raw.q_satu
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        ButterKnife.bind(this);

        tvSoalNew.setText(pertanyaan_kuis[nomor]);
        rbPilihanANew.setText(jawaban_kuis[0]);
        rbPilihanBNew.setText(jawaban_kuis[1]);
        rbPilihanCNew.setText(jawaban_kuis[2]);
        rbPilihanDNew.setText(jawaban_kuis[3]);


        playVoiceSatu();

//        hide();

    }

    private void hide() {


        if (pertanyaan_kuis[nomor] != null) {
            btnPlayNew.setVisibility(View.GONE);
            btnStopNew.setVisibility(View.INVISIBLE);
        } else {
            btnStopNew.setVisibility(View.GONE);
            btnPlayNew.setVisibility(View.VISIBLE);
        }
    }

    private void playVoiceSatu() {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            {
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Masuk Exception", Toast.LENGTH_SHORT).show();
        }


        mediaPlayer = MediaPlayer.create(this, suaraSoalSatu[0]);

        mediaPlayer.setLooping(false);
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        try {
            super.onPause();
            mediaPlayer.pause();
        } catch (Exception e) {
            Toast.makeText(this, "Balum", Toast.LENGTH_SHORT).show();
        }

    }

    private void playSound(int arg) {

        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            {
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Masuk Exception", Toast.LENGTH_SHORT).show();
        }


        if (nomor < pertanyaan_kuis.length) {
            mediaPlayer = MediaPlayer.create(this, suaraSoal[nomor]);
        }
        mediaPlayer.setLooping(false);
        mediaPlayer.start();

    }

    public void next() {

        playSound(nomor);
        if (rbPilihanANew.isChecked() || rbPilihanBNew.isChecked() || rbPilihanCNew.isChecked() || rbPilihanDNew.isChecked()) {

            RadioButton jawabanUser = (RadioButton) findViewById(rgPilihanNew.getCheckedRadioButtonId());
            String ambilJawabanUser = jawabanUser.getText().toString();
            if (ambilJawabanUser.equalsIgnoreCase(jawaban_benar[nomor])) benar++;
            else salah++;
            nomor++;
            if (nomor < pertanyaan_kuis.length) {
                tvSoalNew.setText(pertanyaan_kuis[nomor]);
                rbPilihanANew.setText(jawaban_kuis[(nomor * 4) + 0]);
                rbPilihanBNew.setText(jawaban_kuis[(nomor * 4) + 1]);
                rbPilihanCNew.setText(jawaban_kuis[(nomor * 4) + 2]);
                rbPilihanDNew.setText(jawaban_kuis[(nomor * 4) + 3]);

            } else {
                hasil = benar * 25;
                Intent hasil = new Intent(getApplicationContext(), HasilKuis.class);
                startActivity(hasil);
            }
        } else {
            Toast.makeText(this, "Kamu Jawab Dulu", Toast.LENGTH_LONG).show();
        }

    }


    @OnClick({R.id.btn_play_new, R.id.btn_stop_new})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.btn_play_new:
                next();
                break;
            case R.id.btn_stop_new:
                if (rbPilihanANew.isChecked() || rbPilihanBNew.isChecked() || rbPilihanCNew.isChecked() || rbPilihanDNew.isChecked()) {

                    RadioButton jawabanUser = (RadioButton) findViewById(rgPilihanNew.getCheckedRadioButtonId());
                    String ambilJawabanUser = jawabanUser.getText().toString();
                    if (ambilJawabanUser.equalsIgnoreCase(jawaban_benar[nomor])) benar++;
                    else salah++;
                    nomor++;
                    if (nomor < pertanyaan_kuis.length) {
                        tvSoalNew.setText(pertanyaan_kuis[nomor]);
                        rbPilihanANew.setText(jawaban_kuis[(nomor * 4) + 0]);
                        rbPilihanBNew.setText(jawaban_kuis[(nomor * 4) + 1]);
                        rbPilihanCNew.setText(jawaban_kuis[(nomor * 4) + 2]);
                        rbPilihanDNew.setText(jawaban_kuis[(nomor * 4) + 3]);

                    } else {
                        hasil = benar * 25;
                        Intent hasil = new Intent(getApplicationContext(), HasilKuis.class);
                        startActivity(hasil);
                    }
                } else {
                    Toast.makeText(this, "Kamu Jawab Dulu", Toast.LENGTH_LONG).show();
                }


                break;
        }
    }


}
