package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;

import com.example.flashlight.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    int count=0;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                System.out.println("Running");
                if(count%2==0){
                    binding.button.setText(R.string.button_of_on);
                    binding.flashImageOff.setImageResource(R.drawable.flashlight);
                    count++;
                    System.out.println(count);
                    changeLightState(true);
                }
                else{

                    binding.button.setText(R.string.button_on_off);
                    binding.flashImageOff.setImageResource(R.drawable.turnedoff);
                    count++;
                    System.out.println(count);
                    changeLightState(false);
                }

            }

            private void changeLightState(boolean state) {


                    CameraManager cameraManager= null;
                    cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
                    String camId=null;
                    try{
                        camId=cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(camId,state);
                    }
                    catch (CameraAccessException e){
                        e.printStackTrace();
                    }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.button.setText(R.string.button_on_off);
    }

}