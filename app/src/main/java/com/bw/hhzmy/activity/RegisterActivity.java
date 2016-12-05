package com.bw.hhzmy.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bw.hhzmy.util.CodeUtils;
import com.bw.hhzmy.view.ClearEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.phone_number)
    ClearEditText phoneNumber;
    @Bind(R.id.yanzheng_code)
    ClearEditText yanzhengCode;
    @Bind(R.id.yanzheng_img)
    ImageView yanzhengImg;
    @Bind(R.id.cb_choose)
    CheckBox cbChoose;
    @Bind(R.id.next_bu)
    Button nextBu;
    private CodeUtils codeUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        codeUtils = CodeUtils.getInstance();
        Bitmap bitmap = codeUtils.createBitmap();
        yanzhengImg.setImageBitmap(bitmap);

        ivBack.setOnClickListener(this);
        yanzhengImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.yanzheng_img:
                codeUtils = CodeUtils.getInstance();
                Bitmap bitmap = codeUtils.createBitmap();
                yanzhengImg.setImageBitmap(bitmap);
                break;
        }
    }
}
