package com.example.calculator01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
        final TextView change_1 = findViewById(R.id.textview_change_1);
        final TextView change_2 = findViewById(R.id.textview_change_2);
        final TextView change_3 = findViewById(R.id.textview_change_3);

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
        Button btn_op_back = findViewById(R.id.btn_op_back);

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
                    min_text.setText("");
                    max_text.setText("");
                    change_1.setText("");
                    change_2.setText("");
                    change_3.setText("");
                    e.printStackTrace();
                }

            }
        });



        btn_op_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(max_text.getText().toString().length()>0)
                    max_text.setText(max_text.getText().toString().substring(0,max_text.getText().toString().length()-1));
            }
        });


        //下拉列表adapter定义
        final Spinner spinner_hanshu = findViewById(R.id.spinner_hanshu);
        ArrayAdapter<CharSequence> adapter_hanshu = ArrayAdapter.createFromResource(this,R.array.items_hanshu,android.R.layout.simple_spinner_item);
        adapter_hanshu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_hanshu.setAdapter(adapter_hanshu);

        final Spinner spinner_change = findViewById(R.id.spinner_change);
        ArrayAdapter<CharSequence> adapter_change = ArrayAdapter.createFromResource(this,R.array.items_change,android.R.layout.simple_spinner_item);
        adapter_change.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_change.setAdapter(adapter_change);
        //下拉列表事件
        spinner_hanshu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        min_text.setText("sin("+max_text.getText()+"`) = "+Math.sin(Math.toRadians(Double.parseDouble(max_text.getText().toString()))));
                        break;
                    case 2:
                        min_text.setText("cos("+max_text.getText()+"`) = "+Math.cos(Math.toRadians(Double.parseDouble(max_text.getText().toString()))));
                        break;
                    case 3:
                        min_text.setText("tan("+max_text.getText()+"`) = "+Math.tan(Math.toRadians(Double.parseDouble(max_text.getText().toString()))));
                        break;
                    case 4:
                        min_text.setText("asin("+max_text.getText()+"`) = "+Math.asin(Math.toRadians(Double.parseDouble(max_text.getText().toString()))));
                        break;
                    case 5:
                        min_text.setText("acos("+max_text.getText()+"`) = "+Math.acos(Math.toRadians(Double.parseDouble(max_text.getText().toString()))));
                        break;
                    case 6:
                        min_text.setText("atan("+max_text.getText()+"`) = "+Math.atan(Math.toRadians(Double.parseDouble(max_text.getText().toString()))));
                        break;
                    case 7:
                        min_text.setText(max_text.getText()+"^2 = "+Math.pow(Double.parseDouble(max_text.getText().toString()),2));
                        break;
                    case 8:
                        min_text.setText(max_text.getText()+"! = "+recursion(Integer.parseInt(max_text.getText().toString())));
                        break;
                    case 9:
                        min_text.setText(max_text.getText()+"开根号 = "+Math.sqrt(Double.parseDouble(max_text.getText().toString())));
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_change.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        min_text.setText("米转化");
                        try {
                            change_1.setText(Float.parseFloat(max_text.getText().toString()) * 100 + "--->厘米");
                            change_2.setText(Float.parseFloat(max_text.getText().toString()) * 10 + "--->分米");
                            change_3.setText(Float.parseFloat(max_text.getText().toString()) * 3 + "----->尺");
                            break;
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this,"操作错误！",Toast.LENGTH_LONG).show();
                            min_text.setText("");
                            max_text.setText("");
                            change_1.setText("");
                            change_2.setText("");
                            change_3.setText("");
                            e.printStackTrace();
                            break;
                        }

                    case 2:
                        min_text.setText("立方米转化");
                        try {
                            change_1.setText(Float.parseFloat(max_text.getText().toString()) * 1000000 + "-->立方厘米");
                            change_2.setText(Float.parseFloat(max_text.getText().toString()) * 1000 + "-->立方分米");
                            break;
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this,"操作错误！",Toast.LENGTH_LONG).show();
                            min_text.setText("");
                            max_text.setText("");
                            change_1.setText("");
                            change_2.setText("");
                            change_3.setText("");
                            e.printStackTrace();
                            break;
                        }
                    case 3:
                        min_text.setText("人民币转化");
                        try {
                            change_1.setText(Float.parseFloat(max_text.getText().toString()) * 0.1466 + "--->美元");
                            change_2.setText(Float.parseFloat(max_text.getText().toString()) * 15.4793 + "--->日元");
                            break;
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this,"操作错误！",Toast.LENGTH_LONG).show();
                            min_text.setText("");
                            max_text.setText("");
                            change_1.setText("");
                            change_2.setText("");
                            change_3.setText("");
                            e.printStackTrace();
                            break;
                        }
                    case 4:
                        min_text.setText("2进制转化");
                        try{
                            change_1.setText(Integer.toOctalString(Integer.valueOf(max_text.getText().toString(),2)) +"--->8进制 ");
                            change_2.setText(Integer.valueOf(max_text.getText().toString(),2) +                       "--->10进制");
                            change_3.setText(Integer.toHexString(Integer.valueOf(max_text.getText().toString(),2)) +  "--->16进制");
                            break;
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this,"操作错误！",Toast.LENGTH_LONG).show();
                            min_text.setText("");
                            max_text.setText("");
                            change_1.setText("");
                            change_2.setText("");
                            change_3.setText("");
                            e.printStackTrace();
                            break;
                        }
                    case 5:
                        min_text.setText("8进制转化");
                        try{
                            change_1.setText(Integer.toBinaryString(Integer.valueOf(max_text.getText().toString(),8)) +"--->2进制 ");
                            change_2.setText(Integer.valueOf(max_text.getText().toString(),8) +                       "--->10进制");
                            change_3.setText(Integer.toHexString(Integer.valueOf(max_text.getText().toString(),8)) +  "--->16进制");
                            break;
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this,"操作错误！",Toast.LENGTH_LONG).show();
                            min_text.setText("");
                            max_text.setText("");
                            change_1.setText("");
                            change_2.setText("");
                            change_3.setText("");
                            e.printStackTrace();
                            break;
                        }
                    case 6:
                        min_text.setText("10进制转化");
                        try{
                            change_1.setText(Integer.toBinaryString(Integer.valueOf(max_text.getText().toString(),10)) +"--->2进制 ");
                            change_2.setText(Integer.toOctalString(Integer.valueOf(max_text.getText().toString(),10)) + "---->8进制");
                            change_3.setText(Integer.toHexString(Integer.valueOf(max_text.getText().toString(),10)) +  "--->16进制");
                            break;
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this,"操作错误！",Toast.LENGTH_LONG).show();
                            min_text.setText("");
                            max_text.setText("");
                            change_1.setText("");
                            change_2.setText("");
                            change_3.setText("");
                            e.printStackTrace();
                            break;
                        }
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        btn_op_CE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                min_text.setText("");
                max_text.setText("");
                change_1.setText("");
                change_2.setText("");
                change_3.setText("");
                spinner_change.setSelection(0);
                spinner_hanshu.setSelection(0);
            }
        });
    }
    public static int recursion(int num){//利用递归计算阶乘
        int sum=1;
        if(num==1){
            return 1;//根据条件,跳出循环
        }else{
            sum=num * recursion(num-1);//运用递归计算
            return sum;
        }
    }
}