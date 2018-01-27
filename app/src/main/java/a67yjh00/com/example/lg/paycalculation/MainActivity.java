package a67yjh00.com.example.lg.paycalculation;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button name_list, add;

    ListActivity.MyDBHelper myHelper;
    SQLiteDatabase sqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.add);
        name_list = (Button) findViewById(R.id.name_list);

        myHelper=new ListActivity.MyDBHelper(this);

        sqldb=myHelper.getReadableDatabase();
        String sql="select * from LISTtable";
        Cursor cursor=sqldb.rawQuery(sql,null);

        String names="";
        while(cursor.moveToNext()){
            names=cursor.getString(0);
        }
        name_list.setText(names);


        name_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ListActivity.class);

                i.putExtra("n",name_list.getText().toString());
                Toast.makeText(getApplicationContext(),name_list.getText().toString(),Toast.LENGTH_SHORT).show();

                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(i);
            }
        });
    }
}
