package warhammer.security.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raid_2209ee on 14/02/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment>fragmentList=new ArrayList<>();
    private final List<String>fragmentTitlesList=new ArrayList<>();


    public ViewPagerAdapter(FragmentManager manager){
        super(manager);


    }


    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitlesList.get(position);
    }

    public  void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        fragmentTitlesList.add(title);

    }

}
