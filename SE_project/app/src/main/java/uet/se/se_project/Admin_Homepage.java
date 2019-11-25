package uet.se.se_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Homepage extends AppCompatActivity {
    Button EnrolledStudentsList, AllCoursesList, logOut;
    Button AddNewCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__homepage);

        EnrolledStudentsList = (Button) findViewById(R.id.list_enroll_students_admin);
        AllCoursesList = (Button) findViewById(R.id.list_allcourses_admin);
        AddNewCourses = (Button) findViewById(R.id.add_new_course_admin);
        logOut = (Button) findViewById(R.id.logout_admin);

        AddNewCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Homepage.this, Add_AllCourses.class);
                startActivity(intent);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Homepage.this);
                builder.setMessage("Do you really want to Log Out?");
                builder.setCancelable(true);
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //CurrentUser.loginStatus = false;
                        Intent intent = new Intent(Admin_Homepage.this , MainActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
