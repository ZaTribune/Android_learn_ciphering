package warhammer.security;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import warhammer.security.adapters.AllAdapter;
import warhammer.security.drawer_fragments.AboutFragment;
import warhammer.security.drawer_fragments.LearningFragment;
import warhammer.security.drawer_fragments.TestingFragment;
import warhammer.security.testing_inner_fragments.CipheringFragment;

public class MainActivity extends AppCompatActivity implements TestingFragment.TestingInterface {
    private String[] titles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private Button btn_drawer;
    private TextView action_bar_header;
    private int images[]=new int[]{R.drawable.test,R.drawable.learn,R.drawable.about};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        prepareActionBar();
        btn_drawer= findViewById(R.id.btn_drawer);
        mDrawerLayout= findViewById(R.id.main_activity);
        mDrawerList = findViewById(R.id.left_drawer);
        titles = getResources().getStringArray(R.array.drawer_titles);
        mDrawerList.setAdapter(new AllAdapter(this,
                images,titles));
        mDrawerList.setOnItemClickListener((AdapterView<?> parent,View view,int position,long id) -> {
             addFragment(position);
        });
        addFragment(2);

    }

    public void addFragment(int position){
        FragmentManager fragmentManager = getSupportFragmentManager();
        //this to clear the back stack from any added fragments
        for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
        Fragment fragment;
        switch (position){
            case 0:
                fragment=new TestingFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).addToBackStack("testing").commit();
                action_bar_header.setText(getString(R.string.testing));
                break;
            case 1:
                fragment=new LearningFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).addToBackStack("learning").commit();
                action_bar_header.setText(getString(R.string.learning));
                break;
            case 2:
                fragment=new AboutFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();
                action_bar_header.setText(getString(R.string.about));
                break;
        }
        mDrawerLayout.closeDrawers();
    }

    public void prepareActionBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        action_bar_header= findViewById(R.id.action_bar_header);
        setSupportActionBar(toolbar);

    }
    public void controlDrawer(View view){
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawers();
        }else {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }


    @Override
    public void itemClicked(int position) {
        FragmentManager manager=getSupportFragmentManager();
        Bundle bundle=new Bundle();
        bundle.putInt("cipher",position);
                Fragment fragment=new CipheringFragment();
                fragment.setArguments(bundle);
                manager.beginTransaction().replace(R.id.content_frame,fragment,"Ciphering").addToBackStack(""+position).commit();
    }

}
