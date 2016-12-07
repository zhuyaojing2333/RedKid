package com.bw.hhzmy.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.hhzmy.util.CodeUtils;
import com.bw.hhzmy.util.Validator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.qq_login)
    ImageView qqLogin;
    @Bind(R.id.weixin_login)
    ImageView weixinLogin;
    @Bind(R.id.yifubao_login)
    ImageView yifubaoLogin;
    @Bind(R.id.weibo_login)
    ImageView weiboLogin;
    private ImageView fan;
    private ImageView icon;
    private EditText code;
    private Button login_btn;
    private String codeStr;
    private CodeUtils codeUtils;
    private CheckBox showhide_icon;
    private EditText pwd;
    private Button regist_btn;
    private EditText uname;
    private String unameStr;
    private String pwdStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initView();

        //加载验证码
        codeUtils = CodeUtils.getInstance();
        Bitmap bitmap = codeUtils.createBitmap();
        icon.setImageBitmap(bitmap);


    }

    private void initView() {
        fan = (ImageView) findViewById(R.id.iv_fan);
        code = (EditText) findViewById(R.id.yanzhengcode);
        pwd = (EditText) findViewById(R.id.password);
        icon = (ImageView) findViewById(R.id.yanzheng_icon);
        login_btn = (Button) findViewById(R.id.login_btn);
        showhide_icon = (CheckBox) findViewById(R.id.showhide_icon);
        regist_btn = (Button) findViewById(R.id.regist_btn);
        uname = (EditText) findViewById(R.id.username);

        uname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0)
                    return;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (i != 3 && i != 8 && s.charAt(i) == ' ') {
                        continue;
                    } else {
                        sb.append(s.charAt(i));
                        if ((sb.length() == 4 || sb.length() == 9)
                                && sb.charAt(sb.length() - 1) != ' ') {
                            sb.insert(sb.length() - 1, ' ');
                        }
                    }
                }
                if (!sb.toString().equals(s.toString())) {
                    int index = start + 1;
                    if (sb.charAt(start) == ' ') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    uname.setText(sb.toString());
                    uname.setSelection(index);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        CheckBox.OnCheckedChangeListener listener = new CheckBox.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (showhide_icon.isChecked()) {
                    //文本正常显示
                    pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

//                    //下面两行代码实现: 输入框光标一直在输入文本后面
//                    Editable etable = pwd.getText();
//
//                    Selection.setSelection(etable, etable.length());
                } else {
                    //文本以密码形式显示
                    pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

//                    //下面两行代码实现: 输入框光标一直在输入文本后面
//                    Editable etable = pwd.getText();
//
//                    Selection.setSelection(etable, etable.length());
                }
            }
        };

        showhide_icon.setOnCheckedChangeListener(listener);

        fan.setOnClickListener(this);
        icon.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        regist_btn.setOnClickListener(this);
        qqLogin.setOnClickListener(this);
        weixinLogin.setOnClickListener(this);
        weiboLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regist_btn:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                startActivity(intent);
                break;
            case R.id.iv_fan:
                finish();
                break;
            case R.id.yanzheng_icon:
                codeUtils = CodeUtils.getInstance();
                Bitmap bitmap = codeUtils.createBitmap();
                icon.setImageBitmap(bitmap);
                break;
            case R.id.login_btn:
                unameStr = uname.getText().toString();
                String unameStr2 = unameStr.replace(" ", "");
                pwdStr = pwd.getText().toString();
                codeStr = code.getText().toString().trim();

                if (Validator.isMobile(unameStr2)) {
                    if (Validator.isPassword(pwdStr)) {
                        String code = codeUtils.getCode();
                        if (code.equalsIgnoreCase(codeStr)) {
                            Toast.makeText(this, "验证码正确", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this, "验证码错误", Toast.LENGTH_LONG).show();
                        }
                    } else if (pwdStr.length() < 6 || pwdStr.length() > 20) {
                        Toast.makeText(this, "请输入6-20位密码", Toast.LENGTH_LONG).show();
                        return;
                    } else if (null == pwdStr || TextUtils.isEmpty(pwdStr)) {
                        Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "密码错误，请重新输入", Toast.LENGTH_LONG).show();
                    }
                } else if (null == unameStr2 || TextUtils.isEmpty(unameStr2)) {
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.qq_login:
                Intent intent1 = new Intent(LoginActivity.this, QQActivity.class);
                startActivity(intent1);
                break;
            case R.id.weixin_login:
                Intent intent2 = new Intent(LoginActivity.this, WeiChatActivity.class);
                startActivity(intent2);
                break;
            case R.id.weibo_login:
                Intent intent3 = new Intent(LoginActivity.this, WeiBoActivity.class);
                startActivity(intent3);
                break;
        }
    }

}
