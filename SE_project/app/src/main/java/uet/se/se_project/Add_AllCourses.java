package uet.se.se_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;

public class Add_AllCourses extends AppCompatActivity {
    EditText cn, tn;
    Button add;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("All_Course");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__all_courses);

        cn = (EditText) findViewById(R.id.course_name_admin);
        tn = (EditText) findViewById(R.id.teacher_name_admin);
        add = (Button) findViewById(R.id.add_courses_admin);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String courseName = cn.getText().toString();
                String teacherName = tn.getText().toString();

                final Course_Details_DataBase_Objects obj = new Course_Details_DataBase_Objects(courseName, teacherName);
                collectionReference.document(courseName).set(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Add_AllCourses.this, "success_add", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Add_AllCourses.this, "failure_add", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
