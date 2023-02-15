package ru.arvalon.mytraining.gymdbactivities.avaliableequipmentsactivities;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ru.arvalon.mytraining.model.Equipment;
import ru.arvalon.mytraining.R;
import ru.arvalon.mytraining.db.DatabaseAccess;

public class EquipmentsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.equipments);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<Equipment> equipments=databaseAccess.getFullEquipmentForFragment();
        for (Equipment equipment:equipments){
            adapter.addFragment(
                    EquipmentFragment.getInstance(
                            viewPager,
                            equipment.getId(),
                            equipment.getName(),
                            equipment.getImage(),
                            equipment.getAvaliable(),
                            equipment.getMeasure()),
                    String.valueOf(equipment.getId()));
        }
        databaseAccess.setActivationDB();
        databaseAccess.close();

        viewPager.setAdapter(adapter);

    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> tabs = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title)
        {
            fragmentList.add(fragment);
            tabs.add(title);
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
            return tabs.get(position);
        }
    }

}
