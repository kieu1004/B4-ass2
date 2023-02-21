package com.example.b4ass2;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Spinner levelSelect;
    EditText fullName;
    EditText phoneNumber;
    Switch genderSwitch;
    Spinner levelSpinner;
    SeekBar ageRange;
    CheckBox sportCheckbx;
    RadioGroup rdGroup;
    RadioButton rdButton;
    Button btnRegister;
    Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        levelSelect = findViewById(R.id.level_select);
        fullName = findViewById(R.id.name_et);
        phoneNumber = findViewById(R.id.phone_et);
        genderSwitch = findViewById(R.id.gender_switch);
        levelSpinner = findViewById(R.id.level_select);
        ageRange = findViewById(R.id.age_seekbar);
        sportCheckbx = findViewById(R.id.sport_checkbx);
        rdGroup = findViewById(R.id.music_rdgroup);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancel = findViewById(R.id.btnCancel);
        String[] arraySpinner = new String[] {
                "Trung cấp", "Cao đẳng", "Đại học"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSelect.setAdapter(adapter);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName.setText("");
                phoneNumber.setText("");
                genderSwitch.setChecked(false);
                levelSpinner.setSelection(0);
                ageRange.setProgress(0);
                sportCheckbx.setChecked(false);
                rdGroup.clearCheck();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String fullNameInput = fullName.getText().toString().trim().equals("") ? "" : fullName.getText().toString();
                String phoneNumberInput = phoneNumber.getText().toString().trim().equals("") ? "" : phoneNumber.getText().toString();
                String gender = genderSwitch.isChecked() == true ? "Nam" : "Nữ";
                String levelSelect = levelSpinner.getSelectedItem().toString();
                Integer age = ageRange.getProgress();
                String playSport = sportCheckbx.isChecked() == true ? "Có" : "Không";
                Integer selectedId = rdGroup.getCheckedRadioButtonId();
                rdButton = findViewById(selectedId);
                String musicSelect = rdButton != null ? rdButton.getText().toString() : "";
                int count = 0;
                String[] infoArr = {fullNameInput, phoneNumberInput, gender, levelSelect, age.toString(), playSport, musicSelect};
                for(int i = 0; i < infoArr.length; i++) {
                    if(infoArr[i] == "") {
                        count++;
                    }
                }
                if(count > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Vui lòng nhập đủ thông tin")
                            .setTitle("Thông báo");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else {
                    Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("fullName", fullNameInput);
                    extras.putString("gender", gender);
                    extras.putString("phoneNumber", phoneNumberInput);
                    extras.putString("level", levelSelect);
                    extras.putString("age", age.toString());
                    extras.putString("playSport", playSport);
                    extras.putString("music", musicSelect);
                    intent.putExtras(extras);
                    startActivity(intent);
                }

            }
        });

    }
}