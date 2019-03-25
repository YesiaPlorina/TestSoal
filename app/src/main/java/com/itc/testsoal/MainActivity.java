package com.itc.testsoal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_soal)
    ImageView ivSoal;
    @BindView(R.id.tv_soal)
    TextView tvSoal;
    @BindView(R.id.rb_pilihan_a)
    RadioButton rbPilihanA;
    @BindView(R.id.rb_pilihan_b)
    RadioButton rbPilihanB;
    @BindView(R.id.rb_pilihan_c)
    RadioButton rbPilihanC;
    @BindView(R.id.rb_pilihan_d)
    RadioButton rbPilihanD;
    @BindView(R.id.rg_pilihan)
    RadioGroup rgPilihan;
    @BindView(R.id.tv_penjelasan)
    TextView tvPenjelasan;
    @BindView(R.id.bt_next)
    Button btNext;

    int nomor = 0;
    public static int hasil, benar, salah;
    public static MediaPlayer mediaPlayer;
    ProgressBar progress;
    ListView list;

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
            "YG","JYP","SM","Starship",
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
            R.raw.q_empat
    };
    int[] suaraSoalSatu = new int[]{
            R.raw.q_satu
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        tvSoal.setText(pertanyaan_kuis[nomor]);
        rbPilihanA.setText(jawaban_kuis[0]);
        rbPilihanB.setText(jawaban_kuis[1]);
        rbPilihanC.setText(jawaban_kuis[2]);
        rbPilihanD.setText(jawaban_kuis[3]);

        //menyembunyikan progresbar

        progress.setVisibility(View.INVISIBLE);
        progress.setIndeterminate(true);
        playVoiceSatu();

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

//        playSound(nomor);


        RadioButton jawabanUser = (RadioButton) findViewById(rgPilihan.getCheckedRadioButtonId());
        String ambilJawabanUser = jawabanUser.getText().toString();
        if (ambilJawabanUser.equalsIgnoreCase(jawaban_benar[nomor])) benar++;
        else salah++;
        nomor++;
        if (nomor < pertanyaan_kuis.length) {
            tvSoal.setText(pertanyaan_kuis[nomor]);
            rbPilihanA.setText(jawaban_kuis[(nomor * 4) + 0]);
            rbPilihanB.setText(jawaban_kuis[(nomor * 4) + 1]);
            rbPilihanC.setText(jawaban_kuis[(nomor * 4) + 2]);
            rbPilihanD.setText(jawaban_kuis[(nomor * 4) + 3]);

        } else {
            hasil = benar * 25;
            Intent hasil = new Intent(getApplicationContext(), HasilKuis.class);
            startActivity(hasil);
        }


    }

    @OnClick({R.id.rb_pilihan_a, R.id.rb_pilihan_b, R.id.rb_pilihan_c, R.id.rb_pilihan_d, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_pilihan_a:

                break;
            case R.id.rb_pilihan_b:
                break;
            case R.id.rb_pilihan_c:
                break;
            case R.id.rb_pilihan_d:
                break;
            case R.id.bt_next:
                next();
                break;
        }
    }


}
