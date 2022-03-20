package ma.emsi.second_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

import ma.emsi.second_app.classes.Doctor;
import ma.emsi.second_app.service.DoctorService;

public class MainActivity extends AppCompatActivity {
    private static String TAG="MainActivity";

    private List<Doctor> doctors;
    private RecyclerView recycleView;
    private DoctorAdapter doctorAdapter ;
    private DoctorService service ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        doctors = new ArrayList<Doctor>();
        service = DoctorService.getInstance();
        init();
        doctors.addAll(service.findAll());
        recycleView =findViewById(R.id.recycle);
        doctorAdapter = new DoctorAdapter(this, doctors);
        recycleView.setAdapter(doctorAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recycleView);


    }
    public void init(){
        service.create(new Doctor("Meriem ghazi", "https://bit.ly/3wBxlZh", 5f));
        service.create(new Doctor("Asma dirar", "https://bit.ly/34TEyrW", 3));
        service.create(new Doctor("Mohamed zitouni",
                "https://bit.ly/3imgmBO", 5));
        service.create(new Doctor("Sara kabir", "https://cdn.pixabay.com/photo/2017/01/29/21/16/nurse-2019420_960_720.jpg", 1));
        service.create(new Doctor("Yousef amin", "https://cdn.pixabay.com/photo/2020/11/03/15/31/doctor-5710150_960_720.jpg", 5));
        service.create(new Doctor("Khalid daoudi", "https://img.freepik.com/free-photo/surgeon-with-stethoscope-neck-arms-crossed_1291-8.jpg?1&w=740", 1));
        service.create(new Doctor("Imane sadiki", "https://cdn.pixabay.com/photo/2020/03/14/17/05/virus-4931227_960_720.jpg", 4));
        service.create(new Doctor("Sara moumni", "https://images.pexels.com/photos/4225880/pexels-photo-4225880.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", 1));
        service.create(new Doctor("Hamid laroussi", "https://img.freepik.com/free-photo/portrait-successful-mid-adult-doctor-with-crossed-arms_1262-12865.jpg?t=st=1647728797~exp=1647729397~hmac=f2e8eaf027bd5f8455614cbceedf9483daf898332179e4f5fc24b88d5a87816f&w=740", 5));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (doctorAdapter != null){
                    doctorAdapter.getFilter().filter(newText);
                }
                return true; }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Doctors";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Doctors")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            doctors.remove(position);
            doctorAdapter.notifyItemRemoved(position);
        }
    };




}