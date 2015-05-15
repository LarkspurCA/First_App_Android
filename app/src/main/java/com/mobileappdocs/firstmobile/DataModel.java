package com.mobileappdocs.firstmobile;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by michaelHahn on 5/10/15.
 */
public class DataModel {
    private ArrayList<Golfcourse> coursesArray = new ArrayList<Golfcourse>();
    private Context ctx;

    private static  String TAG = "DataModel";

    // Initializer to read a text file into an array of golfcourse objects
    public DataModel(Context ctx, String coursesFilename) {
        String line;
        BufferedReader br;

        try {
            br = new BufferedReader(new InputStreamReader(ctx.getAssets().open(coursesFilename)));

            while ((line = br.readLine()) != null) {
                StringTokenizer sTok = new StringTokenizer(line, ":");
                Golfcourse gc = new Golfcourse(sTok.nextToken());
                gc.address = sTok.nextToken();
                coursesArray.add(gc);
            }
        } catch (IOException e) {
            return;
        }
    }

        // Method to retrieve courses
        public ArrayList<Golfcourse> getCourses() {
            return coursesArray;
        }
}
