package ict376.murdoch.edu.au.seasonalfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Steven on 7/01/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "SeasonalFood.db";
    public static final String DB_ASSET_PATH = "/src/main/assets/SeasonalFood.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TAG = "DBHelper";

    //table attributes
    public static final String NAME_COLUMN = "name";
    public static final String SPRING = "spring";
    public static final String SUMMER = "summer";
    public static final String AUTUMN = "autumn";
    public static final String WINTER = "winter";


    //table names
    public static final String WA_FRUIT = "wa_fruit";
    public static final String WA_VEG = "wa_veg";
    public static final String NSW_FRUIT = "nsw_fruit";
    public static final String NSW_VEG = "nsw_veg";
    public static final String QLD_FRUIT = "qld_fruit";
    public static final String QLD_VEG = "qld_veg";
    public static final String VIC_FRUIT = "vic_fruit";
    public static final String VIC_VEG = "vic_veg";
    public static final String TAS_FRUIT = "tas_fruit";
    public static final String TAS_VEG = "tas_veg";
    public static final String SA_FRUIT = "sa_fruit";
    public static final String SA_VEG = "sa_veg";

    private final Context context;
    private boolean createDB = false, upgradeDB = false;

    public DBHelper(Context context){
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.i(TAG, "onCreate db");
        createDB = true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.i(TAG, "onUpgrade DB");
        upgradeDB = true;
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        Log.i(TAG, "onOpen DB");

        //if DB exists, copy the contents from db in assets folder
        if(createDB){
            createDB = false;
            copyDBFromAssets(db);
        }
        if(upgradeDB){
            upgradeDB = false;
        }
    }

    private void copyDBFromAssets(SQLiteDatabase db){
        Log.i(TAG, "Databse copying");
        InputStream myInput = null;
        OutputStream myOutput = null;

        try{
            //open shipped DB
            myInput = context.getAssets().open(DB_ASSET_PATH);
            //open current db
            myOutput = new FileOutputStream(db.getPath());

            //transfer contents
            byte[] buffer = new byte[1024];
            int length;
            while((length = myInput.read(buffer)) > 0){
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();

            SQLiteDatabase copiedDb = context.openOrCreateDatabase(
                    DB_NAME, 0 ,null);
            copiedDb.execSQL("PRAGMA user_version ="+DATABASE_VERSION);
            copiedDb.close();

        }catch(IOException e){
            e.printStackTrace();
            throw new Error(TAG + "Error copying database");
        } finally {
            //close I/O streams
            try{
                if(myOutput != null){
                    myOutput.close();
                }
                if(myInput != null){
                    myInput.close();
                }
            }catch (IOException e){
                e.printStackTrace();
                throw new Error(TAG + "error closing I/O streams");
            }
        }
    }
}
