package com.mobileappdocs.firstmobile;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.mobileappdocs.firstmobile.dummy.DummyContent;

/**
 * A fragment representing a single Golfcourse detail screen.
 * This fragment is either contained in a {@link GolfcourseListActivity}
 * in two-pane mode (on tablets) or a {@link GolfcourseDetailActivity}
 * on handsets.
 */
public class GolfcourseDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GolfcourseDetailFragment() {
    }

    private Golfcourse course;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If intent arguments have a course object, get it
        if (getArguments().containsKey("course")) {
            course = getArguments().getParcelable("course");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_golfcourse_detail, container, false);

        // Display the selected golfcourse, or just a welcome message
        if (course != null) {
            ((TextView) rootView.findViewById(R.id.golfcourse_detail)).setText(course.name);
        }
        else {
            ((TextView) rootView.findViewById(R.id.golfcourse_detail)).setText("Welcome to First Master/Detail");
        }

        return rootView;
    }
}
