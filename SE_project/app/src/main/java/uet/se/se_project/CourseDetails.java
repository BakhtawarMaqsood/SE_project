package uet.se.se_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class CourseDetails extends AppCompatActivity {
    Button enrollCourse;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "CourseDetails";
    public static String CourseName, TeacherName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        enrollCourse = (Button) findViewById(R.id.enroll_course);
        enrollCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("All_Course").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(QueryDocumentSnapshot snapshot:queryDocumentSnapshots){
                                Course_Details_DataBase_Objects objects = snapshot.toObject(Course_Details_DataBase_Objects.class);
                                if(snapshot.getId().equals(CurrentUser.currentCourse)){
                                    CourseName = objects.getCourse_name();
                                    TeacherName = objects.getTeacher_name();
                                    Log.d(TAG,CourseName);
                                    Log.d(TAG, TeacherName);
                                    Toast.makeText(CourseDetails.this,CurrentUser.currentCourse, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(CourseDetails.this,CourseName, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(CourseDetails.this,TeacherName, Toast.LENGTH_SHORT).show();
                                    Course_Details_DataBase_Objects mycourse = new Course_Details_DataBase_Objects(CourseName, TeacherName);
                                    db.collection("Sign_up_Data").document(CurrentUser.email).collection("MyCourses").document(CurrentUser.currentCourse).set(mycourse);

                                }
                            }
                    }
                });

            }
        });
    }
}
