package com.example.modelapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

public class DataBaseHelper extends SQLiteOpenHelper {
//    public static final String COLUMN_DISEASE = "DISEASE";
//    public static final String DISEASE_TABLE = "DISEASE_TABLE";
//    public static final String COLUMN_SYMPTOMS = "SYMPTOMS";
//    public static final String COLUMN_REMEDIES = "REMEDIES";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "disease.db", null, 1);
    }

//    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    //this is called the first time a database is accessed.There should be code in here to generate a database.
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE DISEASETABLE ( ID INTEGER PRIMARY KEY AUTOINCREMENT,SYMPTOMS VARCHAR,DISEASE VARCHAR ,REMEDIES VARCHAR);";
        db.execSQL(createTableStatement);

        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Pepper__bell___Bacterial_spot','STEM PHYLIUM SOLANI','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx1');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Pepper__bell___healthy','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx2');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Potato___Early_blight','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx3');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Potato___Late_blight','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx4');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Potato___healthy','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx5');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Tomato_Bacterial_spot','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx6');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Tomato_Early_blight','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx7');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Tomato_Late_blight','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx8');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Tomato_Leaf_Mold','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx9');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Tomato_Septoria_leaf_spot','Alternariasolani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx10');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Tomato_Spider_mites_Two_spotted_spider_mite','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx11');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Tomato__Target_Spot','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx12');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Tomato__Tomato_YellowLeaf__Curl_Virus','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx13');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Tomato__Tomato_mosaic_virus','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx14');");
        db.execSQL("INSERT INTO DISEASETABLE VALUES ('Tomato_healthy','Alternaria solani','https://www.apsnet.org/edcenter/disandpath/fungalasco/pdlessons/Pages/PotatoTomato.aspx15');");


    }

    //this is called when database version number changes.It prevents previous user apps
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS DISEASETABLE");
        onCreate(db);

    }


    public void fetchDataD(String st) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("SELECT * FROM DISEASETABLE WHERE SYMPTOMS=st;",null,null);
//        result.moveToFirst();
//        String disease = result.getString(2);

        while (result.moveToNext()) {
            Log.e( "TAG","SYMTOMS:" + result.getString(2));
        }

        result.close();


    }

    public String fetchDataR(String st) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("SELECT * FROM DISEASETABLE WHERE SYMPTOMS=st;",null,null);
        result.moveToFirst();
        String disease = result.getString(3);


        result.close();

        return  disease;


    }
}
