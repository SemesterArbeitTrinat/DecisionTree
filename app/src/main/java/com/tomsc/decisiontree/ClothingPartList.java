package com.tomsc.decisiontree;


import android.database.Cursor;


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
        List<Item> lowerPartQueue = new ArrayList<>();
        List<Item> sortedByRandomizedRank_lowerPartQueue = sortMaxMin(lowerPartQueue);              //sort min max by giving a List to ChooseProbability and request a List back
        this.clothingPartList = sortedByRandomizedRank_lowerPartQueue;                           //get rank and randomized order of the clothing
    }


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




    public List<Item> getClothingPartList() {
        return clothingPartList;
    }

    public void setClothingPartList(List<Item> clothingPartList) {
        this.clothingPartList = clothingPartList;
    }
}

