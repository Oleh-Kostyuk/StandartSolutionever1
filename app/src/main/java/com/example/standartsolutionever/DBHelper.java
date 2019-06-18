package com.example.standartsolutionever;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import static com.example.standartsolutionever.RwmContract.DATABASE_NAME;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;

    private static DBHelper instance = null;
    private DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    static DBHelper getInstance(Context context){
        if (instance == null) {
            instance = new DBHelper(context);}
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Создаем и заполняем таблицу Providers
        String sql1 = "CREATE TABLE " + RwmContract.ProvidersOfRwmEntry.TABLE_NAME + "(" +
                RwmContract.ProvidersOfRwmEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                RwmContract.ProvidersOfRwmEntry.COLUMN_NAME + " TEXT NOT NULL)";
        db.execSQL(sql1);

        db.execSQL("INSERT INTO " + RwmContract.ProvidersOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.ProvidersOfRwmEntry.COLUMN_NAME + ") VALUES ('Дьяков' );");
        db.execSQL("INSERT INTO " + RwmContract.ProvidersOfRwmEntry.TABLE_NAME + " (" +
                RwmContract.ProvidersOfRwmEntry.COLUMN_NAME + ") VALUES ('Маяк');");
        db.execSQL("INSERT INTO " + RwmContract.ProvidersOfRwmEntry.TABLE_NAME + " (" +
                RwmContract.ProvidersOfRwmEntry.COLUMN_NAME + ") VALUES ('Рубежное');");
        db.execSQL("INSERT INTO " + RwmContract.ProvidersOfRwmEntry.TABLE_NAME + " (" +
                RwmContract.ProvidersOfRwmEntry.COLUMN_NAME + ") VALUES ('УМ_Салтов');");
        db.execSQL("INSERT INTO " + RwmContract.ProvidersOfRwmEntry.TABLE_NAME + " (" +
                RwmContract.ProvidersOfRwmEntry.COLUMN_NAME + ") VALUES ('Форов');");
        // Создаем и заполняем таблицу KindOfRwm
        String sql2 = "CREATE TABLE " + RwmContract.KindOfRwmEntry.TABLE_NAME + "(" +
                RwmContract.KindOfRwmEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                RwmContract.KindOfRwmEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                RwmContract.KindOfRwmEntry.MAY_PROCESSING + " INTEGER NOT NULL)";
        db.execSQL(sql2);

        db.execSQL("INSERT INTO " + RwmContract.KindOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.KindOfRwmEntry.COLUMN_NAME + "," +
                RwmContract.KindOfRwmEntry.MAY_PROCESSING +") VALUES ('Тырса', 0 );");
        db.execSQL("INSERT INTO " + RwmContract.KindOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.KindOfRwmEntry.COLUMN_NAME + ","  +
                RwmContract.KindOfRwmEntry.MAY_PROCESSING + ") VALUES ('Дрова',1 );");
        db.execSQL("INSERT INTO " + RwmContract.KindOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.KindOfRwmEntry.COLUMN_NAME + "," +
                  RwmContract.KindOfRwmEntry.MAY_PROCESSING + ") VALUES ('Горбыль',1 );");
        db.execSQL("INSERT INTO " + RwmContract.KindOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.KindOfRwmEntry.COLUMN_NAME + "," +
                RwmContract.KindOfRwmEntry.MAY_PROCESSING +") VALUES ('Опилки',0 );");
        db.execSQL("INSERT INTO " + RwmContract.KindOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.KindOfRwmEntry.COLUMN_NAME + "," +
                RwmContract.KindOfRwmEntry.MAY_PROCESSING +") VALUES ('Солома',1 );");

        // Создаем и заполняем таблицу TypeOfRwm
        String sql3 = "CREATE TABLE " + RwmContract.TypeOfRwmEntry.TABLE_NAME + "(" +
                RwmContract.TypeOfRwmEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                RwmContract.TypeOfRwmEntry.COLUMN_NAME + " TEXT NOT NULL)";
        db.execSQL(sql3);

        db.execSQL("INSERT INTO " + RwmContract.TypeOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.TypeOfRwmEntry.COLUMN_NAME + ") VALUES ('Дуб' );");
        db.execSQL("INSERT INTO " + RwmContract.TypeOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.TypeOfRwmEntry.COLUMN_NAME + ") VALUES ('Сосна' );");
        db.execSQL("INSERT INTO " + RwmContract.TypeOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.TypeOfRwmEntry.COLUMN_NAME + ") VALUES ('Липа' );");
        db.execSQL("INSERT INTO " + RwmContract.TypeOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.TypeOfRwmEntry.COLUMN_NAME + ") VALUES ('Клен' );");
        db.execSQL("INSERT INTO " + RwmContract.TypeOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.TypeOfRwmEntry.COLUMN_NAME + ") VALUES ('Тополь' );");
        db.execSQL("INSERT INTO " + RwmContract.TypeOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.TypeOfRwmEntry.COLUMN_NAME + ") VALUES ('Фруктовые' );");
        db.execSQL("INSERT INTO " + RwmContract.TypeOfRwmEntry.TABLE_NAME + " ("
                + RwmContract.TypeOfRwmEntry.COLUMN_NAME + ") VALUES ('Солома' );");
        //Создаем  Orders
        String sql4 = "CREATE TABLE " + RwmContract.OrdersEntry.TABLE_NAME + "(" +
                RwmContract.OrdersEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                RwmContract.OrdersEntry.COLUMN_PROVIDERS + " TEXT NOT NULL," +
                RwmContract.OrdersEntry.COLUMN_KINDRWM + " TEXT NOT NULL," +
                RwmContract.OrdersEntry.COLUMN_TYPERWM + " TEXT NOT NULL," +
               RwmContract.OrdersEntry.COLUMN_QUATITY + " TEXT NOT NULL," +
                RwmContract.OrdersEntry.COLUMN_CARRIER + " TEXT NOT NULL," +
               RwmContract.OrdersEntry.COLUMN_COST + " LONG );" ;
        db.execSQL(sql4);
        //Создаем  Refinary
        String sql5 = "CREATE TABLE " + RwmContract.RefinaryEntry.TABLE_NAME + "(" +
                RwmContract.RefinaryEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                RwmContract.RefinaryEntry.COLUMN_INPUTQUATITY + " LONG NOT NULL," +
                RwmContract.RefinaryEntry.COLUMN_KINDRWM + " TEXT NOT NULL," +
                RwmContract.RefinaryEntry.COLUMN_TYPERWM + " TEXT NOT NULL," +
                RwmContract.RefinaryEntry.COLUMN_OUTPUTQUANTITY + " LONG NOT NULL," +
                RwmContract.RefinaryEntry.COLUMN_SAWTIME + " LONG NOT NULL," +
                RwmContract.RefinaryEntry.COLUMN_LOGCHOPPERTIME + " LONG NOT NULL," +
                RwmContract.RefinaryEntry.COLUMN_GINDERTIME + " LONG NOT NULL)";
        db.execSQL(sql5);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

