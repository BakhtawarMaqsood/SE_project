package uet.se.se_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AllCourses_Dashboard extends Fragment {
    Context mContext;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference reference = db.collection("All_Course");
    private FirestoreRecyclerViewAdapter adapter;

    public AllCourses_Dashboard() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_all_courses__dashboard, container, false);

         /* AllCourses allCourses = new AllCourses();
        allCourses.List_All_Courses();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_allcourses_dashboard);
        RecyclerViewAdapter  adapter = new RecyclerViewAdapter(getContext(), allCourses.List_All_Courses());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);*/
        Query query = reference.orderBy("course_name", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Course_Details_DataBase_Objects> options = new FirestoreRecyclerOptions.Builder<Course_Details_DataBase_Objects>()
                .setQuery(query, Course_Details_DataBase_Objects.class)
                .build();

        adapter = new FirestoreRecyclerViewAdapter(options);
        RecyclerView recyclerView = view.findViewById(R.id.rv_allcourses_dashboard);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(adapter);
        return view;

    }


    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

}
