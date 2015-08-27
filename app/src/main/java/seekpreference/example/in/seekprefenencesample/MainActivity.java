package seekpreference.example.in.seekprefenencesample;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar volumeControl = null;
    private TextView textView;
    SharedPreferences mypreferences;

    private String store = "Text";
    int progressChanged = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialise();
    }

    private void initialise() {

        mypreferences = getApplicationContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        progressChanged = mypreferences.getInt(store, 0);

        textView = (TextView) findViewById(R.id.tv_volume);
        textView.setText(progressChanged + "");
        volumeControl = (SeekBar) findViewById(R.id.volume_bar);

        volumeControl.setProgress(progressChanged);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
                textView.setText(progress + "");
                mypreferences.edit().putInt(store, progressChanged).apply();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "seek bar progress:" + progressChanged,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
