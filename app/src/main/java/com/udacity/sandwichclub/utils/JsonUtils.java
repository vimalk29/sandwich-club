package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        String mainName;
        String placeOfOrigin;
        String description;
        String image;

        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            Sandwich sandwich = new Sandwich();

            JSONObject sandwichJsonObject = new JSONObject(json);

            //parse the "name" JSON object first
            JSONObject nameJsonObject = sandwichJsonObject.getJSONObject("name");

            mainName = nameJsonObject.getString("mainName");
            placeOfOrigin = sandwichJsonObject.getString("placeOfOrigin");
            description = sandwichJsonObject.getString("description");
            image = sandwichJsonObject.getString("image");

            JSONArray alsoKnownAsJsonArray = nameJsonObject.getJSONArray("alsoKnownAs");
            if (alsoKnownAsJsonArray != null) {
                List<String> alsoKnownAsList = new ArrayList<>();
                for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                    alsoKnownAsList.add(alsoKnownAsJsonArray.getString(i));
                }
                sandwich.setAlsoKnownAs(alsoKnownAsList);
            }
            JSONArray ingredientsJsonArray = sandwichJsonObject.getJSONArray("ingredients");
            if (ingredientsJsonArray != null) {
                List<String> ingredientsList = new ArrayList<>();
                for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                    ingredientsList.add(ingredientsJsonArray.getString(i));
                }
                sandwich.setIngredients(ingredientsList);
            }


            sandwich.setMainName(mainName);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setDescription(description);
            sandwich.setImage(image);

            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
