package ru.arvalon.mytraining.GymDbActivities.AvaliableEquipmentsActivities;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import ru.arvalon.mytraining.R;
import ru.arvalon.mytraining.db.DatabaseAccess;

/**
 * Created by arvalon on 16.06.2016.
 */
public class EquipmentFragment extends Fragment {

    private static final String FRAGMENT_NAME = "FRAGMENT_NAME";
    private static final String FRAGMENT_IMAGE=null;
    private static final String AVALIABLE="AVALIABLE";
    private static final String MEASURE="MEASURE";
    private static final String ID="ID";

    private String name;
    private byte[] image;
    private int avaliable;
    private int id;
    private int measure;

    private static ViewPager viewPager;
    private Button buttonNext;
    private Button buttonPrev;

    public static Fragment getInstance(ViewPager viewPager,int id,String name, byte[] image, int avaliable,int measure)
    {
        Fragment fragment = new EquipmentFragment();
        EquipmentFragment.viewPager=viewPager;
        Bundle bundle = new Bundle();
        bundle.putString(FRAGMENT_NAME, name);
        bundle.putByteArray(FRAGMENT_IMAGE, image);
        bundle.putInt(AVALIABLE, avaliable);
        bundle.putInt(ID, id);
        bundle.putInt(MEASURE,measure);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null)
        {
            name  = bundle.getString(FRAGMENT_NAME);
            image = bundle.getByteArray(FRAGMENT_IMAGE);
            avaliable=bundle.getInt(AVALIABLE);
            id=bundle.getInt(ID);
            measure=bundle.getInt(MEASURE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipments, container, false);

        TextView tv = (TextView) view.findViewById(R.id.equipment_name);
        tv.setText(name);

        ImageView image = (ImageView) view.findViewById(R.id.equipment_image);

        if (this.image!=null){
            image.setImageBitmap(BitmapFactory.decodeByteArray(this.image,0,this.image.length));
        }else {
            image.setImageResource(R.drawable.no);
        }

        Switch sw=(Switch)view.findViewById(R.id.equipment_switch);
        if (avaliable==1) sw.setChecked(true);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getContext());
                    databaseAccess.open();
                    databaseAccess.ChangeEqupmentsAvaliable(id,1);
                    databaseAccess.close();
                    //viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
                else {
                    DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getContext());
                    databaseAccess.open();
                    databaseAccess.ChangeEqupmentsAvaliable(id,0);
                    databaseAccess.close();
                }
            }
        });
        Switch swMeasure=(Switch)view.findViewById(R.id.unit_of_measure_switch);
        if(measure==0){
            swMeasure.setChecked(false);
        }else if(measure==1){
            swMeasure.setChecked(true);
        }
        swMeasure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getContext());
                    databaseAccess.open();
                    databaseAccess.ChangeEqupmentsMeasure(id,1);
                    databaseAccess.close();
                }else {
                    DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getContext());
                    databaseAccess.open();
                    databaseAccess.ChangeEqupmentsMeasure(id,0);
                    databaseAccess.close();
                }
            }
        });

        buttonNext=(Button)view.findViewById(R.id.equipment_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 17.06.2016
                Log.d("happy","NEXT");
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });

        buttonNext=(Button)view.findViewById(R.id.equipment_back);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 17.06.2016
                Log.d("happy","PREV");
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });

        return view;
    }
}
