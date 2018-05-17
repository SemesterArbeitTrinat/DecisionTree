package com.tomsc.decisiontree;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.BaseColumns;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.tomsc.decisiontree.ChooseProbability.sortMaxMin;

/**
 * Created by tomsc on 10.05.2018.
 */

public class ClothingPartList {

    private List<Item> clothingPartList;




    /**
     * Get ClothingPart based on a list of attributes
     * @param clothingAttributes
     */
    public ClothingPartList(ClothingAttributes clothingAttributes)
    {
        /*
        example database query
         */
        //Cursor cursor = database.query(DBOpenHelper.TABLE_NAME_SAMMLUNG,
        //        Sammlung, selection: DBOpenHelper._ID + "=" + insertID, selectionArgs: null, groubBy: null,
        //       having: null, orderBy: null);


        // Filter results WHERE "title" = 'My Title'
        String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "" };

        List<String> query_results = new ArrayList<String>();                               //ArrayList to store results from the database query
        Cursor cursor = database.query(DBOpenHelper.TABLE_NAME_SAMMLUNG,
                                        Sammlung,
                                        null,


        List<Item> lowerPartQueue = new ArrayList<>();
        List<Item> sortedByRandomizedRank_lowerPartQueue = sortMaxMin(lowerPartQueue);              //sort min max by giving a List to ChooseProbability and request a List back
        this.clothingPartList = sortedByRandomizedRank_lowerPartQueue;                           //get rank and randomized order of the clothing
    }
/**
    SQLiteDatabase db = mDbHelper.getReadableDatabase();

    // Define a projection that specifies which columns from the database
// you will actually use after this query.
    String[] projection = {
            BaseColumns._ID,
            FeedEntry.COLUMN_NAME_TITLE,
            FeedEntry.COLUMN_NAME_SUBTITLE
    };

    // Filter results WHERE "title" = 'My Title'
    String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
    String[] selectionArgs = { "My Title" };

    // How you want the results sorted in the resulting Cursor
    String sortOrder =
            FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

    Cursor cursor;

    {
        cursor = db.query(
                FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
    }


    */



    /**
     * get ClothingPart based on previous clothingPart, usually UpperPart
     * @param firstPart
     */

    public ClothingPartList(Item firstPart)
    {

        List<Item> secondPartQueue = new ArrayList<>();
        List<Item> sortedByRandomizedRank_secondPartQueue = sortMaxMin(secondPartQueue);            //sort min max by giving a List to ChooseProbability and request a List back
        this.clothingPartList = sortedByRandomizedRank_secondPartQueue;                           //get rank and randomized order of the clothing

    }


    //get all clothing parts with color == blue, style == freizeit, topcategory == lowerPart
    public List<Item> getClothingPart(List<String> color, List<String> sytle, String topcategory)
    {

        List<Item> result = new ArrayList<>();
        //String selection = DBOpenHelper.TABLE_NAME_FARBE.FARBE_FARBE;
        //String[] selectionArgs = color.toArray();
        SQLiteDatabase db = helper.getReadableDatabase();



        String table = TABLE_NAME_SAMMLUNG;
        String[] columns = {"_id"} //"Stil", "Bezeichnung", "Farbe", "Schnitt", "Rank", "Favorit", "Kategorie"};
        String selection = null;
        String[] selectionArgs = null;//{"apple"};
        String groupBy = null;
        String having = null;
        String orderBy = null;//"column3 DESC";
        String limit = null;//"10";

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);


        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String name, cut, topcategory, color, style_values;
            String[] style;
            int rank, id, favourite;
            Uri image_path;
            name = cursor.getString(cursor.getColumnIndex(DBOpenHelper.BEZEICHNUNG_BEZEICHNUNG));
            cut = cursor.getString(cursor.getColumnIndex(DBOpenHelper.SCHNITT_SCHNITT));
            color = cursor.getString(cursor.getColumnIndex(DBOpenHelper.FARBE_FARBE));
            style_values = cursor.getString(cursor.getColumnIndex(DBOpenHelper.STIL_STIL));
            style = style_values.split("\\|");                                                            //split values at |


            //Get image from BLOB String, convert it to BMP and save it
            byte[] byteArray = cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FOTO);
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            File file = new File(Environment.getExternalStorageDirectory().getPath().toString() + "/ImageDB/" + fileName + ".png");
            String path = file.getAbsolutePath();
            image_path = Uri.parse(path);
            FileOutputStream filecon = new FileOutputStream(file);
            try {
                sampleResized.compress(Bitmap.CompressFormat.JPEG, 90, filecon);
            } catch (exception e) {
            }
            filecon.close;

            rank = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_RANK));
            id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper._ID));
            topcategory = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_KATEGORIE));
            favourite = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.SAMMLUNG_FAVORIT));


            Item item = new Item(name, cut, topcategory, color, style, image_path, rank, id, favourite);
            result.add(item);

        }

    }










    public List<Item> getClothingPartList() {
        return clothingPartList;
    }

    public void setClothingPartList(List<Item> clothingPartList) {
        this.clothingPartList = clothingPartList;
    }
}

