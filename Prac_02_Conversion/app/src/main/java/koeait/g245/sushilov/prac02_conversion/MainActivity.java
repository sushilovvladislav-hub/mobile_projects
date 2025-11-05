package koeait.g245.sushilov.prac02_conversion;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Spinner spFrom;
    Spinner spTo;
    EditText txtFrom;
    TextView txtTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtFrom = findViewById(R.id.txtFrom);
        txtTo = findViewById(R.id.txtTo);
        spFrom = findViewById(R.id.lstFrom);
        spTo = findViewById(R.id.lstTo);

        setupEditTextValidation(txtFrom);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
   private void setupEditTextValidation(EditText editText){
        editText.addTextChangedListener((new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String curText = s.toString();

                if(curText.equals(".")){
                    s.clear();
                    return;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        }));
   }

   private void showError(@StringRes int stringResId){
       Toast.makeText(this, getString(stringResId), Toast.LENGTH_LONG).show();
   }

   private double validateInput(){

        String stringFrom = txtFrom.getText().toString();

        if (stringFrom.isEmpty()){
            showError(R.string.empty_field_error);
            return 0;
        }

       return Double.parseDouble(stringFrom);

    }

    public void convertClick(View v){
        double numFrom = validateInput();

        if (numFrom == 0) return;



    }

}