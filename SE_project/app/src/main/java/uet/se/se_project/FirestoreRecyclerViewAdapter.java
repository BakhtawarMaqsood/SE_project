package uet.se.se_project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class FirestoreRecyclerViewAdapter extends FirestoreRecyclerAdapter<Course_Details_DataBase_Objects, FirestoreRecyclerViewAdapter.FirestoreViewHolder>{

    private static Context context;
    public FirestoreRecyclerViewAdapter(@NonNull FirestoreRecyclerOptions<Course_Details_DataBase_Objects> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FirestoreViewHolder firestoreViewHolder, final int i, @NonNull final Course_Details_DataBase_Objects allCourses) {
        firestoreViewHolder.courseName.setText(allCourses.getCourse_name());
        firestoreViewHolder.teacherName.setText(allCourses.getTeacher_name());
//        firestoreViewHolder.courseImage.setImageResource(Integer.parseInt(allCourses.getThumbnail()));

       firestoreViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CurrentUser.currentCourse= allCourses.getCourse_name();
                Intent intent= new Intent(context, CourseDetails.class);
                context.startActivity(intent);
            }
        });
        /*firestoreViewHolder.enroll_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(CurrentUser.loginStatus)){
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mContext);
                    builder.setMessage("you are not logged in");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else
                {
                    Toast.makeText(mContext, "ye tow hona e naee hyyyy... wese dekhayn gy.. copy paste e karna ay", Toast.LENGTH_SHORT).show();
                }

            }
        });*/
    }

    @NonNull
    @Override
    public FirestoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);
        return new FirestoreViewHolder(view);
    }

    class FirestoreViewHolder extends RecyclerView.ViewHolder {
        TextView teacherName, courseName, session, enroll_status;
        CardView cardView;

        public FirestoreViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            //courseImage =(ImageView) itemView.findViewById(R.id.course_image);
            teacherName =(TextView) itemView.findViewById(R.id.teacher_name);
            courseName = (TextView) itemView.findViewById(R.id.course_name);
            cardView= (CardView)itemView.findViewById(R.id.cardview_id);

        }
    }
}


