package com.example.bmicalculator;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2;
    Button btn_s;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = findViewById(R.id.edit_text1);
        edt2 = findViewById(R.id.edit_text2);
        btn_s =  findViewById(R.id.btn_submit);
        txt =  findViewById(R.id.txt_status);

        edt1.addTextChangedListener(loginTextWatcher);
        edt2.addTextChangedListener(loginTextWatcher);

        // handle submit button to preview the entered data
        btn_s.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //To hide the Key board
                edt2.onEditorAction(EditorInfo.IME_ACTION_DONE);

                // Do something in response to button click
                String weight = edt1.getText().toString();
                String height = edt2.getText().toString();

                double cal = Calculate_BMI(weight,height);
                String str = String.format("%.1f",cal);
                float r = Float.parseFloat(str);
                String result = getResult(r);
                txt.setText("Status : "+result);
            }
        });

    }

    public static double Calculate_BMI(String x, String y){
        double h = Double.valueOf(y);
        double w = Double.valueOf(x);
        double b = w/(h*h);
        return b*10000;
    }

    public static String getResult(float cal){
        String result="";
        if(cal<18.5){
            result = "Under Weight";
        }
        else if(cal>=18.5 && cal<=24.9){
            result = "Healthy Weight";
        }
        else if(cal>=25.0 && cal<=29.9){
            result = "Overweight";
        }
        else {
            result = "Obesity";
        }
        return result;
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String weightInput = edt1.getText().toString().trim();
            String heightInput = edt2.getText().toString().trim();
            btn_s.setEnabled(!weightInput.isEmpty() && !heightInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


}