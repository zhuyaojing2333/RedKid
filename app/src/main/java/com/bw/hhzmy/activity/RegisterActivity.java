package com.bw.hhzmy.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.hhzmy.util.CodeUtils;
import com.bw.hhzmy.util.Validator;
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
    private String phoneStr;
    private String codeStr;

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
        nextBu.setOnClickListener(this);
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
            case R.id.next_bu:
                phoneStr = phoneNumber.getText().toString();
                codeStr = yanzhengCode.getText().toString().trim();

                if(Validator.isMobile(phoneStr)){
                    String code = codeUtils.getCode();
                    if (code.equalsIgnoreCase(codeStr)) {
                        if(cbChoose.isChecked()){
                            Intent intent = new Intent(RegisterActivity.this, MessageActivity.class);

                            startActivity(intent);
                        }else{
                            Toast.makeText(this, "请同意苏宁易购会员章程和易付宝协议后再进行下一步操作", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "验证码错误", Toast.LENGTH_LONG).show();
                    }
                }else if(null == phoneStr || TextUtils.isEmpty(phoneStr)) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}
