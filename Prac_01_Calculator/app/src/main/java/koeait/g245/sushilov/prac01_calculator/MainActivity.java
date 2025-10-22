package koeait.g245.sushilov.prac01_calculator;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.tan;
import static java.lang.Math.toRadians;




import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity
{
    EditText txt_a, txt_b;
    TextView txt_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txt_a = findViewById(R.id.input_num1);
        txt_b = findViewById(R.id.input_num2);
        txt_res = findViewById(R.id.output_num3);

        setupEditTextValidation(txt_a);
        setupEditTextValidation(txt_b);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.base), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void setupEditTextValidation(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                String curText = s.toString();

                if (curText.equals(".")){
                    s.clear();
                    return;
                }

                if (curText.startsWith("-.")){
                    s.replace(0, s.length(), "-");
                    return;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {}
        });

    }
    private void showError(@StringRes int stringResId){
        Toast.makeText(this,getString(stringResId),Toast.LENGTH_LONG).show();
    }
    private double[] validateInputs() {

        fixTrailingDot(txt_a);
        fixTrailingDot(txt_b);

        String string_a = txt_a.getText().toString();
        String string_b = txt_b.getText().toString();

        if (string_a.isEmpty() || string_b.isEmpty())
        {
            showError(R.string.empty_field_error);
            return null;
        }

        double num_a = Double.parseDouble(string_a);
        double num_b = Double.parseDouble(string_b);

        return new double[]{num_a,num_b};
    }

    private void fixTrailingDot(EditText editText){
        String text = editText.getText().toString().trim();
        if (text.endsWith(".") && text.length() > 1){
            editText.setText(text.substring(0, text.length() - 1));
        }
    }
    private String formatResult(double res) {
        if (res == (long)res)
        {
            return String.valueOf((long)res);
        } else
        {
            return String.valueOf(res);
        }
    }
    public void addClick(View v) {
        double[] numbers = validateInputs();

        if (numbers == null) return;

        double val_a = numbers[0];
        double val_b = numbers[1];

        double res = val_a + val_b;

        String string_res = formatResult(res);

        txt_res.setText(string_res);
    }
    public void subClick(View v) {
        double[] numbers = validateInputs();

        if (numbers == null) return;

        double val_a = numbers[0];
        double val_b = numbers[1];

        double res = val_a - val_b;

        String string_res = formatResult(res);

        txt_res.setText(string_res);
    }
    public void mulClick(View v) {
        double[] numbers = validateInputs();

        if (numbers == null) return;

        double val_a = numbers[0];
        double val_b = numbers[1];

        double res = val_a * val_b;

        String string_res = formatResult(res);

        txt_res.setText(string_res);

    }
    public void divClick(View v) {
        double[] numbers = validateInputs();

        if (numbers == null) return;

        double val_a = numbers[0];
        double val_b = numbers[1];

        if(val_b == 0.0) {
            showError(R.string.zero_div_error);
            return;
        }

        double res = val_a / val_b;

        String string_res = formatResult(res);

        txt_res.setText(string_res);


    }
    public void powClick(View v) {
        double[] numbers = validateInputs();

        if (numbers == null) return;

        double val_a = numbers[0];
        double val_b = numbers[1];

        if (val_a == 0 && val_b < 0){
            showError(R.string.zero_negative_power_error);
            return;
        }

        if (val_a < 0 && val_b != (long) val_b){
            showError(R.string.negative_number_under_fractional_power_error);
        }

        double res = pow(val_a,val_b);

        String string_res = formatResult(res);

        txt_res.setText(string_res);
    }

    public void rootClick(View v) {
        double[] numbers = validateInputs();

        if (numbers == null) return;

        double val_a = numbers[0];
        double val_b = numbers[1];

        if (val_b == 0){
            showError(R.string.degree_root_zero_error);
            return;
        }

        double actualPower = 1.0 / val_b;

        if (val_a < 0 && actualPower != (long) actualPower){
            showError(R.string.negative_number_under_fractional_root_error);
            return;
        }

        if (val_a < 0 && ((long) val_b) % 2 == 0){
            showError(R.string.negative_number_under_even_root_error);
            return;
        }

        double res = (val_a < 0)
                ? -pow(-val_a, 1.0 / val_b)
                : pow(val_a, 1.0 / val_b);

        String string_res = formatResult(res);

        txt_res.setText(string_res);
    }

    public void sinClick(View v) {
        double[] numbers = validateInputs();
        if (numbers == null) return;

        double val_a = numbers[0];
        double val_b = numbers[1];

        double sin_val = sin(toRadians(val_a));
        double res = pow(sin_val, val_b);

        String string_res = formatResult(res);

        txt_res.setText(string_res);


    }

    public void cosClick(View v) {
        double[] numbers = validateInputs();
        if (numbers == null) return;

        double val_a = numbers[0];
        double val_b = numbers[1];

        double sin_val = cos(toRadians(val_a));
        double res = pow(sin_val, val_b);

        String string_res = formatResult(res);

        txt_res.setText(string_res);
    }

    public void tgClick(View v) {
        double[] numbers = validateInputs();
        if (numbers == null) return;

        double val_a = numbers[0];
        double val_b = numbers[1];

        if ((val_a % 180) == 90){
            showError(R.string.tg_90_plus_180k_angle);
            return;
        }

        double tg_val = tan(toRadians(val_a));
        double res = pow(tg_val,val_b);

        String string_res = formatResult(res);

        txt_res.setText(string_res);
    }
}


