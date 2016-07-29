package com.example.user.samplehandler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView progressText;
    Button startButton, endButton;
    ProgressBar progressBar;


    int count = 0;


    // worker 생성
    Handler mHandler = new Handler();
    // 작업리스트 생성
    Runnable countRunnable = new Runnable() {

        @Override
        public void run() {

            // 작업

            if(count < 100) {
                count++;

                progressText.setText("로딩중...  " + count + "%");
                progressBar.setProgress(count);

                // 반복적으로 처리하는 roof
                // postDelayed: 1초 기다렸다가 실행
                mHandler.postDelayed(countRunnable, 100);
            }
            else {
                // 종료
                mHandler.removeCallbacks(countRunnable);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View initialize
        progressText = (TextView) findViewById(R.id.progressText);
        startButton = (Button) findViewById(R.id.startButton);
        endButton = (Button) findViewById(R.id.endButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        // shared preference 에서 불러오기
        count = PropertyManager.gerInstance().getLoadingProgress(MainActivity.this);
        progressText.setText("로딩중... " + count + "%");
        progressBar.setProgress(count);

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    mHandler.post(countRunnable);
            }

        });

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 종료
                mHandler.removeCallbacks(countRunnable);

                // end 버튼이 눌렸을 때 count를 SP에 저장
                PropertyManager.gerInstance().setLoadingProgress(MainActivity.this, count);
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();

        PropertyManager.gerInstance().setLoadingProgress(MainActivity.this, count);
    }
}
