package com.bw.hhzmy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MessageActivity extends AppCompatActivity {
    private CheckBox sh_icon;
    private EditText pwd;
    String num;
    private String phoneNumBer;
    private TextView send_txt;
    private TextView time;
    private Timer timer = null;
    private TimerTask timerTask = null;
    private int i =180;
    private EditText ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        sh_icon = (CheckBox) findViewById(R.id.sh_icon);
        pwd = (EditText) findViewById(R.id.shezhi_password);
        send_txt = (TextView) findViewById(R.id.send_to);
        time = (TextView) findViewById(R.id.timer_time);
        ma = (EditText) findViewById(R.id.yanzheng_ma);
        findViewById(R.id.iv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.commit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maStr = MessageActivity.this.ma.getText().toString().trim();
                if(maStr.equals(num)){
                    Intent intent = new Intent(MessageActivity.this, LoginActivity.class);

                    startActivity(intent);
                }else{
                    Toast.makeText(MessageActivity.this, "您的验证码有误", Toast.LENGTH_LONG).show();
                }

            }
        });

        Intent intent = getIntent();

        phoneNumBer = intent.getStringExtra("phone_num");

        send_txt.setText("短信验证码已发送至"+phoneNumBer);

        startTime();

        CheckBox.OnCheckedChangeListener listener = new CheckBox.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (sh_icon.isChecked()) {
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

        sh_icon.setOnCheckedChangeListener(listener);

        new Thread(new Runnable() {
            @Override
            public void run() {
                getMessageNum();
            }
        }).start();
    }


    private void startTime(){
        if(timer == null){
            timer = new Timer();
        }

        timerTask = new TimerTask() {
            @Override
            public void run() {
                i--;

                Message msg = Message.obtain();

                msg.arg1 = i;

                handler.sendMessage(msg);
            }
        };

        timer.schedule(timerTask, 1000);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            time.setText(msg.arg1+"秒");

            startTime();

            if(msg.arg1 <= 0){
                time.setText("重新发送");

                timer.cancel();

                time.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getMessageNum();
                            }
                        });
                    }
                });
            }
        }
    };

    /**
     * 获取短信验证方法
     */
    private void getMessageNum() {
        HashMap<String, Object> result = null;

        // 初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

        // ******************************注释*********************************************
        // *初始化服务器地址和端口 *
        // *沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        // *生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883"); *
        // *******************************************************************************
        restAPI.init("app.cloopen.com", "8883");

        // ******************************注释*********************************************
        // *初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN *
        // *ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
        // *参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。 *
        // *******************************************************************************
        restAPI.setAccount("8aaf070858cd982e0158d91092480905", "56bec6bd38b94dae83ec2c6de979de98");
        //8aaf070858cd982e0158d91092480905
        //56bec6bd38b94dae83ec2c6de979de98
        //8aaf070858cd982e0158d925e6260928

        // ******************************注释*********************************************
        // *初始化应用ID *
        // *测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID *
        // *应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
        // *******************************************************************************
        restAPI.setAppId("8aaf070858cd982e0158d925e6260928");

        // ******************************注释****************************************************************
        // *调用发送模板短信的接口发送短信 *
        // *参数顺序说明： *
        // *第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号 *
        // *第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。 *
        // *系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
        // *第三个参数是要替换的内容数组。 *
        // **************************************************************************************************

        // **************************************举例说明***********************************************************************
        // *假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为
        // *
        // *result = restAPI.sendTemplateSMS("13800000000","1" ,new
        // String[]{"6532","5"}); *
        // *则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入 *
        // *********************************************************************************************************************
        // 生成随机数 4位

        num = (new Random().nextInt(8999) + 1000) + "";
        result = restAPI.sendTemplateSMS(phoneNumBer, "1", new String[]{
                num, "3"});

        System.out.println("SDKTestGetSubAccounts result=" + result);
        if ("000000".equals(result.get("statusCode"))) {
            // 正常返回输出data包体信息（map）
            @SuppressWarnings("unchecked")
            HashMap<String, Object> data = (HashMap<String, Object>) result
                    .get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                System.out.println(key + " = " + object);
            }
        } else {
            // 异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") + " 错误信息= "
                    + result.get("statusMsg"));
        }
    }
}
