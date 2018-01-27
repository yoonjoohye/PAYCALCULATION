package a67yjh00.com.example.lg.paycalculation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    DatePicker date;
    TextView date_check;
    EditText name, content, money;
    Button date_ok, ok, cancel;
    RadioButton borrow, lend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name=(EditText)findViewById(R.id.name);
        content=(EditText)findViewById(R.id.content);
        money=(EditText)findViewById(R.id.money);

        date=(DatePicker)findViewById(R.id.date);
        date_ok=(Button)findViewById(R.id.date_ok);//날짜확인버튼
        date_check=(TextView)findViewById(R.id.date_check);//날짜확인 텍스트뷰

        borrow=(RadioButton)findViewById(R.id.borrow);//빌림버튼
        lend=(RadioButton)findViewById(R.id.lend);//빌려줌버튼

        ok=(Button)findViewById(R.id.ok);//확인버튼
        cancel=(Button)findViewById(R.id.cancel);//취소버튼

        /*버튼*/
        date_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date_check.setText(date.getYear()+"년 "+(date.getMonth()+1)+"월 "+date.getDayOfMonth()+"일");
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(borrow.isChecked()){//빌림을 선택했을때
                    Toast.makeText(getApplicationContext(),"빌림",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),ListActivity.class);
                    i.putExtra("n",name.getText().toString());//n에 입력 받은 name 값을 넣어줌
                    i.putExtra("d",date_check.getText().toString());
                    i.putExtra("c",content.getText().toString());
                    i.putExtra("m",Integer.parseInt(money.getText().toString())*-1);
                    startActivity(i);
                    finish();
                }
                else{//빌려줌을 선택했을때
                    Toast.makeText(getApplicationContext(),"빌려줌",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),ListActivity.class);
                    i.putExtra("n",name.getText().toString());//n에 입력 받은 name 값을 넣어줌
                    i.putExtra("d",date_check.getText().toString());
                    i.putExtra("c",content.getText().toString());
                    i.putExtra("m",Integer.parseInt(money.getText().toString()));
                    startActivity(i);
                    finish();

                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
