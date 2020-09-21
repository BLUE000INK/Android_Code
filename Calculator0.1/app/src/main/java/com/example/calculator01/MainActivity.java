package com.example.calculator01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_esc,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_help:
                Toast.makeText(this, "这是帮助！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_exit:
                //获取当前进程，并关闭
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText max_text = findViewById(R.id.edittext_max);
        final TextView min_text = findViewById(R.id.textview_min);

        Button btn_num_0 = findViewById(R.id.btn_num_0);
        Button btn_num_1 = findViewById(R.id.btn_num_1);
        Button btn_num_2 = findViewById(R.id.btn_num_2);
        Button btn_num_3 = findViewById(R.id.btn_num_3);
        Button btn_num_4 = findViewById(R.id.btn_num_4);
        Button btn_num_5 = findViewById(R.id.btn_num_5);
        Button btn_num_6 = findViewById(R.id.btn_num_6);
        Button btn_num_7 = findViewById(R.id.btn_num_7);
        Button btn_num_8 = findViewById(R.id.btn_num_8);
        Button btn_num_9 = findViewById(R.id.btn_num_9);

        Button btn_op_add = findViewById(R.id.btn_op_add);
        Button btn_op_jian = findViewById(R.id.btn_op_jian);
        Button btn_op_x = findViewById(R.id.btn_op_x);
        Button btn_op_chu = findViewById(R.id.btn_op_chu);

        Button btn_op_left_k = findViewById(R.id.btn_op_left_k);
        Button btn_op_righ_k = findViewById(R.id.btn_op_right_k);
        Button btn_num_point = findViewById(R.id.btn_num_point);

        Button btn_op_result = findViewById(R.id.btn_op_result);
        Button btn_op_CE = findViewById(R.id.btn_op_CE);

        btn_num_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_text.setText(max_text.getText()+"0");
            }
        });

        btn_num_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_text.setText(max_text.getText()+"1");
            }
        });

        btn_num_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_text.setText(max_text.getText()+"2");
            }
        });

        btn_num_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_text.setText(max_text.getText()+"3");
            }
        });

        btn_num_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_text.setText(max_text.getText()+"4");
            }
        });

        btn_num_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_text.setText(max_text.getText()+"5");
            }
        });

        btn_num_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_text.setText(max_text.getText()+"6");
            }
        });

        btn_num_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_text.setText(max_text.getText()+"7");
            }
        });

        btn_num_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_text.setText(max_text.getText()+"8");
            }
        });

        btn_num_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_text.setText(max_text.getText()+"9");
            }
        });

        btn_num_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                max_text.setText(max_text.getText()+".");
            }
        });
        btn_op_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                min_text.setText(min_text.getText().toString()+max_text.getText().toString()+"+");
                max_text.setText("");
            }
        });

        btn_op_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                min_text.setText(min_text.getText().toString()+max_text.getText().toString()+"-");
                max_text.setText("");
            }
        });

        btn_op_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                min_text.setText(min_text.getText().toString()+max_text.getText().toString()+"X");
                max_text.setText("");
            }
        });

        btn_op_chu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                min_text.setText(min_text.getText().toString()+max_text.getText().toString()+"/");
                max_text.setText("");
            }
        });

        btn_op_left_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                min_text.setText(min_text.getText().toString()+"(");
                max_text.setText("");
            }
        });

        btn_op_righ_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                min_text.setText(min_text.getText().toString()+max_text.getText().toString()+")");
                max_text.setText("");
            }
        });

        btn_op_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                min_text.setText(min_text.getText().toString()+max_text.getText().toString()+"=");
                Calculator_base str = new Calculator_base();
                try {
                    float result = str.caculate(min_text.getText().toString());
                    max_text.setText(Float.toString(result));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,"算式错误！",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

        btn_op_CE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                min_text.setText("");
                max_text.setText("");
            }
        });
    }
}