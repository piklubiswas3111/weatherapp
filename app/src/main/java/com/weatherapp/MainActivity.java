package com.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private TextView textView,text,GetCity;
    String apikey = "YOUR_API_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.loacton);
        button = (Button) findViewById(R.id.whterget);
        textView = (TextView) findViewById(R.id.datafact);
        text = (TextView) findViewById(R.id.collyface);
        GetCity=(TextView)findViewById(R.id.getcity);
        GetCity.setText("");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetCity.setText(editText.getText().toString());
                getwheterreport();
            }
        });


    }

    public void getwheterreport() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiii apiii=retrofit.create(apiii.class);
        Call<Example> callmethod=apiii.getwheater(editText.getText().toString().trim(),apikey);
        callmethod.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.code()==404){
                    Toast.makeText(getApplicationContext(),"Please Enter a Vaild City",Toast.LENGTH_SHORT).show();
                }else if(!(response.isSuccessful())){
                    Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Example mydata=response.body();



                    Main main =mydata.getMain();
                    Double temp=main.getTemp();
                    Double htemp=main.getTemp_max();
                    Double ltemp=main.getTemp_min();
                    Integer temptuare= (int)(temp-273.15);
                    Integer htemptuare= (int)(htemp-273.15);
                    Integer ltemptuare= (int)(ltemp-273.15);



                    textView.setText(String.valueOf(temptuare)+"C");


                    text.setText(String.valueOf("H"+htemptuare+"C"+" "+"L"+ltemptuare+"C"));

                }





            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }
}