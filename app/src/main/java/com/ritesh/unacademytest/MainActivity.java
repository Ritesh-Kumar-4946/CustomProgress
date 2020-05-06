package com.ritesh.unacademytest;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ritesh.unacademytest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        binding.btnAnimate.setOnClickListener((View view) -> {
            // validation for blank field & not Zero
            progress = 0;
            if (!TextUtils.isEmpty(binding.edtUserName.getText().toString())
                    && !binding.edtUserName.getText().toString().equalsIgnoreCase("0")) {

                // remove leading zeros from entered text
                int pro = Integer.parseInt(binding.edtUserName.getText().toString().replaceFirst("^0+(?!$)", ""));
                if (pro < 101) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            binding.unacademyLoader.setProgress(progress++);
                            if (progress >= pro) {
                                handler.removeCallbacks(this);

                                // set Zero after completed
                                binding.edtUserName.setText("0");
                                return;
                            }
                            // maintain speed by setting value here
                            handler.postDelayed(this, 20);
                        }

                        // start animation after delayed 'set delayed value here'
                    }, 50);
                } else {
                    showMessage();
                }

            } else {
                // if validation fail show toast message to the user
                showMessage();
            }
        });
    }

    private void showMessage() {
        Toast.makeText(this, "Please Enter Valid Progress between 0 - 100", Toast.LENGTH_LONG).show();
    }

}
