package com.bw.hhzmy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.hhzmy.fragment.FragmentFour;
import com.bw.hhzmy.fragment.FragmentOne;
import com.bw.hhzmy.fragment.FragmentThree;
import com.bw.hhzmy.fragment.FragmentTwo;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.rg)
    RadioGroup rg;
    @Bind(R.id.fl)
    FrameLayout fl;
    @Bind(R.id.home_btn)
    RadioButton homeBtn;
    @Bind(R.id.class_btn)
    RadioButton classBtn;
    @Bind(R.id.shopping_btn)
    RadioButton shoppingBtn;
    @Bind(R.id.mybuy_btn)
    RadioButton mybuyBtn;

    private FragmentManager supportFragmentManager;
    private FragmentTransaction beginTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);


        initFragment();
        inittiaozhuan();
    }

    private void inittiaozhuan() {
        Intent intert = getIntent();
        int id = intert.getIntExtra("id", -1);
        if (id > 0) {
            System.out.println("aaa" + id);
            if (id == 1) {
//                beginTransaction.replace(R.id.fl, new FragmentThree()); //这里是指定跳转到指定的fragment
                shoppingBtn.setChecked(true);
            }
        }
        Intent intert1 = getIntent();
        int id1 = intert1.getIntExtra("id1", -1);
        if (id1 > 0) {
            System.out.println("aaa" + id1);
            if (id1 == 1) {
                shoppingBtn.setChecked(true);
//                beginTransaction.replace(R.id.fl, new FragmentThree()); //这里是指定跳转到指定的fragment
            }
        }
    }

    private void initFragment() {


        supportFragmentManager = getSupportFragmentManager();

        beginTransaction = supportFragmentManager.beginTransaction();

        beginTransaction.replace(R.id.fl, new FragmentOne());

        beginTransaction.commit();

        rg.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (homeBtn.isChecked()) {
            transaction.replace(R.id.fl, new FragmentOne()).commit();
        } else if (classBtn.isChecked()) {
            transaction.replace(R.id.fl, new FragmentTwo()).commit();
        } else if (shoppingBtn.isChecked()) {
            transaction.replace(R.id.fl, new FragmentThree()).commit();
        } else if (mybuyBtn.isChecked()) {
            transaction.replace(R.id.fl, new FragmentFour()).commit();
        }
    }
}





