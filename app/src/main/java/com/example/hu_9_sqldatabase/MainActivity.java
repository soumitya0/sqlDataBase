package com.example.hu_9_sqldatabase;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    DatabaseHelper myDb;

    EditText et_first_name,et_last_name,et_marks,et_id;
    Button btn_add;
    Button btn_UpDate;
    Button btn_viewAll,btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);


        et_first_name=(EditText)findViewById(R.id.et_frist_name);
        et_id=(EditText)findViewById(R.id.et_id);
        et_last_name=(EditText)findViewById(R.id.et_last_name);
        et_marks=(EditText)findViewById(R.id.et_marks);
        btn_add=(Button)findViewById(R.id.btn_add);
        btn_viewAll=(Button)findViewById(R.id.btn_viewAll);

        btn_UpDate=(Button)findViewById(R.id.btn_UpDate);

        btn_delete=(Button)findViewById(R.id.btn_delete);

        ADD_Data();
        ViewAllData();
        UpdateData();
        delete();
    }

    public void ADD_Data(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isInserted= myDb.insertData(et_first_name.getText().toString(),
                        et_last_name.getText().toString(),et_marks.getText().toString() );


            if(isInserted ==true){
                Toast.makeText(getApplicationContext(),"Data is insereted",Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(),"not insereted",Toast.LENGTH_SHORT).show();
            }
        });
    }


public  void  ViewAllData(){
        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor res= myDb.getAllData();

               if(res.getCount()== 0 ){
                   //  SHOW MESSAGE
                   showMessage("error","nodata");
               }

                 StringBuffer  buffer= new StringBuffer();

                 // geting all the data one by one
                // ASSINING INDEX TO EACH COLUMN ID FIRST_NAME, LAST_NAME, MARKS
                while(res.moveToNext()){


                    // ASSINING INDEX TO EACH COLUMN ID FIRST_NAME, LAST_NAME, MARKS
                    buffer.append("ID :"+ res.getString(0)+"\n");
                    buffer.append("FIRST_NAME :"+ res.getString(1)+"\n");
                    buffer.append("LAST_NAME :"+ res.getString(2)+"\n");
                    buffer.append("MARKS :"+ res.getString(3)+"\n\n");

                    // show all data

                    showMessage("data",buffer.toString());

                }

            }
        });
    }

// we are showing the data by useing ALERTDIALOG  - ViewAllData
    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    //
    public void UpdateData(){
        btn_UpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = myDb.update(et_id.getText().toString(),et_first_name.getText().toString(),
                        et_last_name.getText().toString(),
                        et_marks.getText().toString());


                if(isUpdated == true) {
                    Toast.makeText(getApplicationContext(), "Data is update", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "not  update", Toast.LENGTH_SHORT).show();


            }
        });


    }



    // delete

    void delete(){
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  int deleteRows= myDb.delete(et_id.getText().toString());

                  if ( deleteRows>0)
                  {                    Toast.makeText(getApplicationContext(), "Data is deleted", Toast.LENGTH_SHORT).show();
                  }
                  else
                      Toast.makeText(getApplicationContext(), "not  deleted", Toast.LENGTH_SHORT).show();



            }
        });
    }

}


