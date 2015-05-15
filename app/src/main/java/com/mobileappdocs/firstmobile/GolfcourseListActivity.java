package com.mobileappdocs.firstmobile;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import java.util.ArrayList;

public class GolfcourseListActivity extends Activity
        implements GolfcourseListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private static  String TAG = "GolfcourseListActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get golf courses from a file named courses.txt
        DataModel dataModel = new DataModel(this, "courses");
        ArrayList<Golfcourse> courses = dataModel.getCourses();

        FragmentManager fm = getFragmentManager();
        setContentView(R.layout.activity_golfcourse_list);

        if (findViewById(R.id.golfcourse_detail_container) != null) {
            mTwoPane = true;
            // Its a tablet, so create a new detail fragment if one does not already exist
            GolfcourseDetailFragment df = (GolfcourseDetailFragment) fm.findFragmentByTag("Detail");
            if (df == null) {
                // Initialize new detail fragment
                df = new GolfcourseDetailFragment();
                Bundle args = new Bundle();
                args.putParcelable("course", new Golfcourse("Welcome to First Master/Detail"));
                df.setArguments(args);
                fm.beginTransaction().replace(R.id.golfcourse_detail_container, df, "Detail").commit();
            }
        }

        // Initialize a new golfcourse list fragment, if one does not already exist
        GolfcourseListFragment cf = (GolfcourseListFragment) fm.findFragmentByTag("List");
        if ( cf == null) {
            cf = new GolfcourseListFragment();
            Bundle arguments = new Bundle();
            arguments.putParcelableArrayList("courses", dataModel.getCourses());
            cf.setArguments(arguments);
            fm.beginTransaction().replace(R.id.golfcourse_list, cf, "List").commit();
        }
    }

    /**
     * Callback method from {@link GolfcourseListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(Golfcourse c) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            // Pass the selected Golfcourse object to the DetailFragment
            arguments.putParcelable("course", c);
            GolfcourseDetailFragment fragment = new GolfcourseDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.golfcourse_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, GolfcourseDetailActivity.class);
            // Pass the selected Golfcourse object to the DetailActivity
            detailIntent.putExtra("course", c);
            startActivity(detailIntent);
        }
    }
}
