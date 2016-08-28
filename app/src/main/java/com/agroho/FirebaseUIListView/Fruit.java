package com.agroho.FirebaseUIListView;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by USER on 8/26/2016.
 */
@IgnoreExtraProperties
public class Fruit {

    private String fruitId;
    private String fruitName;
    private String fruitSummary;


    public Fruit(){

    }

    public Fruit(String fruitId, String fruitName, String fruitSummary) {
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.fruitSummary = fruitSummary;

    }

    public Fruit(String fruitName, String fruitSummary) {
        this.fruitName = fruitName;
        this.fruitSummary = fruitSummary;
    }

    public String getFruitId() {
        return fruitId;
    }

    public void setFruitId(String fruitId) {
        this.fruitId = fruitId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getFruitSummary() {
        return fruitSummary;
    }

    public void setFruitSummary(String fruitSummary) {
        this.fruitSummary = fruitSummary;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> fruits = new HashMap<>();
        fruits.put("fruitID", fruitId);
        fruits.put("fruitName", fruitName);
        fruits.put("fruitSummary", fruitSummary);

        return fruits;
    }
}
