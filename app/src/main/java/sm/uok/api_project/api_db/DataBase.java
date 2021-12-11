package sm.uok.api_project.api_db;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    public static final String DB_Name="data";
    public static final String TABLE_Name="data1";

    public DataBase(Context context) {
        super(context,DB_Name,null,19);
        SQLiteDatabase database=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_Name+" (Id INTEGER PRIMARY KEY AutoIncrement,NAME TEXT,NewConfirmed TEXT,TotalConfirmed TEXT,NewDeaths TEXT,TotalDeaths TEXT,NewRecovered TEXT,TotalRecovered TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Name);
        onCreate(db);


    }
    public StringBuffer filter(String m){
        SQLiteDatabase dbf=this.getReadableDatabase();
        Cursor filter=dbf.rawQuery("select * from "+TABLE_Name+" where NAME='"+m+"'",null);
        StringBuffer buffer=new StringBuffer();
        if(filter.getCount()==0){
            /*if(insert(m)==true){
                buffer.append("SAVED");
            }else {
                buffer.append("There is a Problem");
            }*/

        }else {
            buffer.append("DATA EXIST");
        }

        return buffer;





    }
    public boolean insert(String NAME,String NewConfirmed,String TotalConfirmed,String NewRecovered,String TotalRecovered,String NewDeaths,String TotalDeaths){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("NAME",NAME);
        cv.put("NewConfirmed",NewConfirmed);
        cv.put("TotalConfirmed",TotalConfirmed);
        cv.put("NewRecovered",NewRecovered);
        cv.put("TotalRecovered",TotalRecovered);
        cv.put("NewDeaths",NewDeaths);
        cv.put("TotalDeaths",TotalDeaths);

        //database.execSQL("select NAME from " + TABLE_Name);
        long result=database.insert(TABLE_Name,null,cv);


        if(result==-1){
            return false;
        }else {
            return true;
        }


    }
    public Cursor show(){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor result= database.rawQuery("select * from "+TABLE_Name,null);
        return result;
    }
    public Cursor showselectitem(String n){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor result=database.rawQuery("select * from "+TABLE_Name+" where NAME='"+n+"'",null);
        return result;
    }
    public boolean delete(String name){
        SQLiteDatabase database=this.getWritableDatabase();
        long dl= database.delete(TABLE_Name,"NAME=?",new String[] {name});
        //  database.execSQL("delete from " + TABLE_Name + " where NAME='" + d + "'");
        if(dl==0){
            return false;
        }else {
            return true;
        }
    }
    public Cursor type(){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor result=database.rawQuery("select TYPEOF(Id) from "+TABLE_Name,null);
        return result;
    }
    public boolean update(String u,int e){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        if(e==0) {
            cv.put("b", "true");
        }else {
            cv.put("b", "false");
        }
      int result= database.update(TABLE_Name,cv,"NAME='"+u+"'",null);
      if(result==-1){
          return false;
      }else {
          return true;
      }
    }
    public boolean updateAll(){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("b","false");
        database.execSQL("update "+TABLE_Name+" set b='false'");
        return true;

    }
    public boolean deleteOFdb(){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL(" delete from "+TABLE_Name);
        return true;

    }
}
