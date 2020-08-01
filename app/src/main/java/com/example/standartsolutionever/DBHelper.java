package com.example.standartsolutionever;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.standartsolutionever.RwmUtilityContract.DATABASE_NAME;



public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 11;
    private static DBHelper instance = null;

    private DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    static DBHelper getInstance(Context context){
        if (instance == null) {
            instance = new DBHelper(context);}
        return instance;
    }

    @Override
    public void onCreate( SQLiteDatabase db) {
        String sql0 = "PRAGMA foreign_keys = ON ";
        db.execSQL(sql0);

        //Создаем и заполняем таблицу Providers
        String sql1 = "CREATE TABLE " + RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME + "(" +
                RwmUtilityContract.ProvidersOfRwmEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_LENGTH +" REAL NOT NULL)";
        db.execSQL(sql1);

//       db.execSQL("INSERT INTO " + RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME + " (" +
//                 RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_NAME +", "+
//                 RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_LENGTH +
//               ") VALUES ('Дьяков',47 );");
//        db.execSQL("INSERT INTO " + RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME + " (" +
//                RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_NAME + ", "+
//                RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_LENGTH +
//                ") VALUES ('Маяк',32);");
//       db.execSQL("INSERT INTO " + RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME + " (" +
//                RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_NAME +", "+
//                RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_LENGTH +
//               ") VALUES ('Рубежное',24);");
//        db.execSQL("INSERT INTO " + RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME + " (" +
//                RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_NAME +", "+
//                RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_LENGTH +
//                ") VALUES ('УМ_Салтов',12);");
//        db.execSQL("INSERT INTO " + RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME +" ("+
//                        RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_NAME +", "+
//                        RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_LENGTH +
//                ") VALUES ('Форов',15);");

        // Создаем и заполняем таблицу KindOfRwm
        String sql2 = "CREATE TABLE " + RwmUtilityContract.KindOfRwmEntry.TABLE_NAME + "(" +
                RwmUtilityContract.KindOfRwmEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                RwmUtilityContract.KindOfRwmEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                RwmUtilityContract.KindOfRwmEntry.MAY_PROCESSING + " INTEGER NOT NULL)";
        db.execSQL(sql2);

        db.execSQL("INSERT INTO " + RwmUtilityContract.KindOfRwmEntry.TABLE_NAME + " ("
                + RwmUtilityContract.KindOfRwmEntry.COLUMN_NAME + "," +
                RwmUtilityContract.KindOfRwmEntry.MAY_PROCESSING +") VALUES ('Обзел', 1 );");
        db.execSQL("INSERT INTO " + RwmUtilityContract.KindOfRwmEntry.TABLE_NAME + " ("
                + RwmUtilityContract.KindOfRwmEntry.COLUMN_NAME + ","  +
                RwmUtilityContract.KindOfRwmEntry.MAY_PROCESSING + ") VALUES ('Тырса',0 );");
        db.execSQL("INSERT INTO " + RwmUtilityContract.KindOfRwmEntry.TABLE_NAME + " ("
                + RwmUtilityContract.KindOfRwmEntry.COLUMN_NAME + "," +
                  RwmUtilityContract.KindOfRwmEntry.MAY_PROCESSING + ") VALUES ('Дрова',1 );");
        db.execSQL("INSERT INTO " + RwmUtilityContract.KindOfRwmEntry.TABLE_NAME + " ("
                        + RwmUtilityContract.KindOfRwmEntry.COLUMN_NAME + "," +
                        RwmUtilityContract.KindOfRwmEntry.MAY_PROCESSING + ") VALUES ('Щепа',1 );");


        // Создаем и заполняем таблицу TypeOfRwm
        String sql3 = "CREATE TABLE " + RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME + "(" +
                RwmUtilityContract.TypeOfRwmEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                RwmUtilityContract.TypeOfRwmEntry.COLUMN_NAME + " TEXT NOT NULL)";
        db.execSQL(sql3);

        db.execSQL("INSERT INTO " + RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME + " ("
                + RwmUtilityContract.TypeOfRwmEntry.COLUMN_NAME + ") VALUES ('Твердая' );");
        db.execSQL("INSERT INTO " + RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME + " ("
                + RwmUtilityContract.TypeOfRwmEntry.COLUMN_NAME + ") VALUES ('Мягкая' );");
        db.execSQL("INSERT INTO " + RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME + " ("
                + RwmUtilityContract.TypeOfRwmEntry.COLUMN_NAME + ") VALUES ('Сосна' );");
        db.execSQL("INSERT INTO " + RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME + " ("
                + RwmUtilityContract.TypeOfRwmEntry.COLUMN_NAME + ") VALUES ('Микс' );");

        //Создаем  Orders
        String sql4 = "CREATE TABLE " + RwmUtilityContract.OrdersEntry.TABLE_NAME + "(" +
                RwmUtilityContract.OrdersEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                RwmUtilityContract.OrdersEntry.COLUMN_PROVIDERS_ID + " INTEGER NOT NULL," +
                RwmUtilityContract.OrdersEntry.COLUMN_KINDRWM_ID + " INTEGER NOT NULL," +
                RwmUtilityContract.OrdersEntry.COLUMN_TYPERWM_ID + " INTEGER NOT NULL," +
                RwmUtilityContract.OrdersEntry.COLUMN_QUATITY + " TEXT NOT NULL," +
                RwmUtilityContract.OrdersEntry.COLUMN_CARRIER + " TEXT NOT NULL," +
                RwmUtilityContract.OrdersEntry.COLUMN_COST + " LONG, " +
                RwmUtilityContract.OrdersEntry.COLUMN_DATE + " TEXT NOT NULL," +
                " FOREIGN  KEY ("+ RwmUtilityContract.OrdersEntry.COLUMN_PROVIDERS_ID +")" +
                " REFERENCES " + RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME +
                "("+ RwmUtilityContract.ProvidersOfRwmEntry._ID +")," +
                " FOREIGN  KEY ("+ RwmUtilityContract.OrdersEntry.COLUMN_KINDRWM_ID +")"+
                " REFERENCES " + RwmUtilityContract.KindOfRwmEntry.TABLE_NAME +
                "("+ RwmUtilityContract.KindOfRwmEntry._ID +")," +
                " FOREIGN  KEY ("+ RwmUtilityContract.OrdersEntry.COLUMN_TYPERWM_ID +")"+
                " REFERENCES " + RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME +
                "("+ RwmUtilityContract.TypeOfRwmEntry._ID +"));";
        db.execSQL(sql4);
        //Создаем  Refinary
//        String sql5 = "CREATE TABLE " + RwmContract.RefinaryEntry.TABLE_NAME + "(" +
//                RwmContract.RefinaryEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
//                RwmContract.RefinaryEntry.COLUMN_INPUTQUATITY + " LONG NOT NULL," +
//                RwmContract.RefinaryEntry.COLUMN_KINDRWM + " TEXT NOT NULL," +
//                RwmContract.RefinaryEntry.COLUMN_TYPERWM + " TEXT NOT NULL," +
//                RwmContract.RefinaryEntry.COLUMN_OUTPUTQUANTITY + " LONG NOT NULL," +
//                RwmContract.RefinaryEntry.COLUMN_SAWTIME + " LONG NOT NULL," +
//                RwmContract.RefinaryEntry.COLUMN_LOGCHOPPERTIME + " LONG NOT NULL," +
//                RwmContract.RefinaryEntry.COLUMN_GINDERTIME + " LONG NOT NULL)";
//        db.execSQL(sql5);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+RwmUtilityContract.KindOfRwmEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+RwmUtilityContract.OrdersEntry.TABLE_NAME);
        onCreate(db);



    }
}

