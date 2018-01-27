package a67yjh00.com.example.lg.paycalculation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {
    EditText get_name, get_date,get_content,get_money;
    TextView total;
    Button add, delete;

    String n, d, c;
    int m,t;
    static int sum=0;

    MyDBHelper myHelper;
    SQLiteDatabase sqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        get_name=(EditText) findViewById(R.id.get_name);
        get_date=(EditText)findViewById(R.id.get_date);
        get_content=(EditText)findViewById(R.id.get_content);
        get_money=(EditText)findViewById(R.id.get_money);
        total=(TextView) findViewById(R.id.total);

        add=(Button)findViewById(R.id.add);
        delete=(Button)findViewById(R.id.delete);

        Intent i=getIntent();
        n=i.getExtras().getString("n");//n에 다른 액티비티에서 받은 값을 넣어줌
        d=i.getExtras().getString("d");
        c=i.getExtras().getString("c");
        m=i.getExtras().getInt("m",0);

        get_name.setText(n);

        t=Integer.parseInt(total.getText().toString());

        myHelper=new MyDBHelper(this);

        if(n!=""&&d!=""&&c!=""&&m!=0){
            sqldb = myHelper.getWritableDatabase();

            String sql = "insert into LISTtable values('" + n + "','" + d + "','" + c + "'," + m + ","+t+")";
            sqldb.execSQL(sql);
            sqldb.close();
        }
        selectTable();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),AddActivity.class);
                startActivity(i);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//익명클래스
                sqldb=myHelper.getWritableDatabase();
                String sql="delete from LISTtable where name='"+n+"'";
                sqldb.execSQL(sql);
                sqldb.close();
                selectTable();
            }
        });
    }
    public void selectTable(){
        sqldb=myHelper.getReadableDatabase();
        String sql="select * from LISTtable where name='"+n+"'";
        Cursor cursor=sqldb.rawQuery(sql,null);

        String dates="";
        String contents="";
        String moneys="";

        while(cursor.moveToNext()){
            dates+=cursor.getString(1)+"\r\n";
            contents+=cursor.getString(2)+"\r\n";
            moneys+=cursor.getInt(3)+"원"+"\r\n";
        }
        get_date.setText(dates);
        get_content.setText(contents);
        get_money.setText(moneys);

        cursor.close();
        sqldb.close();
    }

    static class MyDBHelper extends SQLiteOpenHelper {
        public MyDBHelper(Context context) {
            super(context, "LIST", null, 1);//idolDB라는 이름의 데이터베이스가 생성된다. 숫자 1은 버전임
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql="create table LISTtable(name text, date text, content text, money integer,sum integer)";
            db.execSQL(sql);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int j) {
            String sql="drop table if exists LISTtable";
            db.execSQL(sql);
            onCreate(db);
        }
    }
}
