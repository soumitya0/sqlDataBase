# sqlDataBase
 student data base



1	create android project

2	create a java file DatabaseHelper it will extends SQLiteOpenHelper

3	implement all unimplemented method (Oncreate and onUpgrade)

4	create a default constructor matching super 

4.1 select first on


5	create db name and Table Name and columns

5.1	
    public static final String DATABASE_NAME="Student.db";

    public static final String TABLE_NAME="Student_table";

5.2 
//4 col in table
    public static final String COL_1="ID";
    public static final String COL_2="FIRST_NAME";
    public static final String COL_3="LAST_NAME";
    public static final String COL_4="MARKS";


    			Student.db
****************************************************************
ID			FIRST_NAME       LAST_NAME        MARKS	
****************************************************************
1              ram				kumar			95 	

****************************************************************



6	Remove a the parameter of constructor matching super
  it will be like 


    public DatabaseHelper( Context context) {
        super(context, name, factory, version);
        // name= database  variable  name  and  my database variable  name is database
        //factory=null
        //version=1


    public DatabaseHelper( Context context)
     {
        super(context, DATABASE_NAME, null, 1);

      }



7	when ever the constructor DatabaseHelper() will call your database will created 



8	now creating the database  in onCreate method
	
	8.1 // to create table (Student Table)is data base it will created in onCreat(SQLiteDatabase db)

        db.execSQL("create table "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , FIRST_NAME TEXT, LAST_NAME TEXT,MARKS INTEGER)");


9   now drop your table in upGrade method

  9.1  @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
    {
       
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }


10   how to know your data base is created or not ?
      to do it we have to go on the  constructor and write

             /* THIS CODE WILL HELP YOU TO SEE THE DATA BASE IS CREATED
      
       SQLiteDatabase db= this.getWritableDatabase();

11	create a instance of DatabaseHelper class in mainactivity.java

		 DatabaseHelper myDb;

         myDb = new DatabaseHelper(this);

12	now run the app and the app will be blank :=;
 
  12.1   but your database is created
  12.2 	 to show database 
  12.3 	 goto DeviceFileExpoler (right bottom of screen) ->data -> data -> search you appliction/project name -> database ->now you can your data base name 
  12.4 	 how to seen inside your data base in pc
  12.5   right click and save it on your desktop
  12.6   DB browser(sqlite) software use it to seen the data base


13	Read the activity.xml


14	 Create a new method databaseHelper
		
		public  boolean insertData(String FIRST_NAME,String LAST_NAME,String MARKS)
		{

		}


14.1 
 public  boolean insertData(String FIRST_NAME,String LAST_NAME,String MARKS)
 {
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();  // use to put the columns  to the value 

        contentValues.put(COL_2,FIRST_NAME);
        contentValues.put(COL_3,LAST_NAME);
        contentValues.put(COL_4,MARKS);

        long result=  db.insert(TABLE_NAME,null,contentValues);  
        
        // db.insert(TABLE_NAME,null,contentValues)
        //long result use that the value are really inserted or not


		if(result== -1)
		{
    		return false;
		}
		else return true;


  }


15	MainActivity.java

 15.1 	create AddData method 

 	15.1.1 	public void ADD_Data()
      
    15.1.2  
    public void ADD_Data()
      {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isInserted= myDb.insertData(
                        et_first_name.getText().toString(),
                        et_last_name.getText().toString(),
                        et_marks.getText().toString() 
                        );


            if(isInserted ==true){
                Toast.makeText(getApplicationContext(),"Data is insereted",Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(),"not insereted",Toast.LENGTH_SHORT).show();
            }
        });
    }

 15.2  call addData() in to your onCreat 


16	Create a new method databaseHelper
		public Cursor getAllData()
		{

    	}
 16.1
  random/read and write
 The basic purpose of a cursor is to point to a single row of the result fetched by the query. We load the row pointed by the cursor object. By using cursor we can save lot of ram and memory.


        public Cursor getAllData()
    {
        SQLiteDatabase db= this.getWritableDatabase();

        Cursor res= db.rawQuery("select * from "  +  TABLE_NAME,null);
		return res;
    }



17 mainactivity.java

17.1
ViewAllData(){

    }

17.2       ViewAllData(){
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

18. now read the update and delete from code



