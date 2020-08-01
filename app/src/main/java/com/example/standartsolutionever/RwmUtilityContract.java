package com.example.standartsolutionever;

import android.net.Uri;
import android.provider.BaseColumns;


public  final class RwmUtilityContract {
    public final static String DATABASE_NAME = "RawMaterials";
    public static final String CONTENT_AUTHORITY = "com.example.standartsolutionever.provider";
    public static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);





    private RwmUtilityContract() {
    }

    public static final class ProvidersOfRwmEntry implements BaseColumns {
        public final static String TABLE_NAME = "providers";
        public static final String _ID = "_id";
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_LENGTH= "km";
    };
    public static final class KindOfRwmEntry implements BaseColumns {
        public final static String TABLE_NAME = "kind_of_raw_materials";
        public final static String _ID = "_id";
        public final static String COLUMN_NAME = "name";
        public final static String MAY_PROCESSING = "processing";
    };
    public static final class TypeOfRwmEntry implements BaseColumns {
        public final static String TABLE_NAME = "type_of_raw_materials";
        public final static String _ID = "_id";
        public final static String COLUMN_NAME = "name";
    };
    public static final class OrdersEntry implements BaseColumns {
        public final static String TABLE_NAME = "orders";
        public final static String _ID = "_id";
        public final static String COLUMN_PROVIDERS_ID = "provider_id";
        public final static String COLUMN_KINDRWM_ID = "kindOfRawMaterials_id";
        public final static String COLUMN_TYPERWM_ID = "typeOfRawMaterials_id";
        public final static String COLUMN_QUATITY = "quantity";
        public final static String COLUMN_CARRIER = "carrier";
        public final static String COLUMN_COST = "cost";
        public final static String COLUMN_DATE ="created_on";
    }
    public static final class RefinaryEntry implements BaseColumns {
        public final static String TABLE_NAME = "refinery";
        public final static String _ID = "_id";
        public final static String COLUMN_INPUTQUATITY = "input_quantity";
        public final static String COLUMN_KINDRWM = "KindofRawMaterials";
        public final static String COLUMN_TYPERWM = "typeOfRawMaterials";
        public final static String COLUMN_OUTPUTQUANTITY = "output_quantity";
        public final static String COLUMN_SAWTIME = "sawtime";
        public final static String COLUMN_LOGCHOPPERTIME = "logchoppertime";
        public final static String COLUMN_GINDERTIME = "gindertime" ;
    }
}
