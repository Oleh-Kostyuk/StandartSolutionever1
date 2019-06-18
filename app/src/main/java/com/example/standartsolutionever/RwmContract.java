package com.example.standartsolutionever;

import android.net.Uri;
import android.provider.BaseColumns;


public  final class RwmContract {
    public final static String DATABASE_NAME = "RawMaterials";
    static final String CONTENT_AUTHORITY = "com.example.standartsolutionever.provider";
    static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);





    private RwmContract() {
    }

    public static final class ProvidersOfRwmEntry implements BaseColumns {
        public final static String TABLE_NAME = "providers";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
    };
    public static final class KindOfRwmEntry implements BaseColumns {
        public final static String TABLE_NAME = "KindofRawMaterials";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String MAY_PROCESSING = "processing";
    };
    public static final class TypeOfRwmEntry implements BaseColumns {
        public final static String TABLE_NAME = "TypeOfRawMaterials";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
    };
    public static final class OrdersEntry implements BaseColumns {
        public final static String TABLE_NAME = "Orders";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PROVIDERS = "providers";
        public final static String COLUMN_KINDRWM = "kindofRawMaterials";
        public final static String COLUMN_TYPERWM = "typeOfRawMaterials";
        public final static String COLUMN_QUATITY = "quantity";
        public final static String COLUMN_CARRIER = "carrier";
        public final static String COLUMN_COST = "cost";
    }
    public static final class RefinaryEntry implements BaseColumns {
        public final static String TABLE_NAME = "Refinery";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_INPUTQUATITY = "input_quantity";
        public final static String COLUMN_KINDRWM = "KindofRawMaterials";
        public final static String COLUMN_TYPERWM = "typeOfRawMaterials";
        public final static String COLUMN_OUTPUTQUANTITY = "output_quantity";
        public final static String COLUMN_SAWTIME = "sawtime";
        public final static String COLUMN_LOGCHOPPERTIME = "logchoppertime";
        public final static String COLUMN_GINDERTIME = "gindertime" ;
    }
}
