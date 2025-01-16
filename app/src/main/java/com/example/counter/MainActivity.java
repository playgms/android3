package com.example.counter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView counterTextView;
    private Button startButton, stopButton;

    private int count = 0;
    private Handler handler = new Handler();
    private Runnable runnable;
    private boolean isCounting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counterTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        // Define the runnable to increment the counter
        runnable = new Runnable() {
            @Override
            public void run() {
                count++;
                counterTextView.setText(String.valueOf(count));
                handler.postDelayed(this, 1000); // update every 1 second
            }
        };

        // Start button listener
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCounting) {
                    isCounting = true;
                    handler.post(runnable); // start counting
                }
            }
        });

        // Stop button listener
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCounting) {
                    isCounting = false;
                    handler.removeCallbacks(runnable); // stop counting
                }
            }
        });
    }
}
