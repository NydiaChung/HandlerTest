package com.example.handlertest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;


/*功能描述
* 1.点击GET请求获取，显示提示正在加载的进度条，分线程请求网络
* 2.得到数据后，将数据显示到输入框中，同时隐藏进度条*/
public class HandlerTestActivity extends AppCompatActivity {

    private ProgressBar pb_handler1_loading;
    private EditText et_handler1_result;

    //1.创建Handler成员变量对象，并重写其handlerMessage()
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
      public void handleMessage(android.os.Message msg){

      }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);

        pb_handler1_loading=findViewById(R.id.pb_handler1_loading);
        et_handler1_result=findViewById(R.id.et_handler1_result);
    }





    /*
    //不使用Handler实现异步工作
    * 1.主线程，显示提示视图
    * 2.分线程，联通请求，并得到响应数据
    * 3.主线程，显示数据*/
    public void getSubmit1(View view){
        //1.主线程，显示提示视图
        pb_handler1_loading.setVisibility(View.VISIBLE);
        //2.分线程，联通请求，并得到响应数据
        new Thread(){
            @Override
            public void run() {
                String path="https://www.baidu.com/";
                try {
                    final String result=requestToString(path);

                    //3.主线程，显示数据
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            et_handler1_result.setText(result);
                            pb_handler1_loading.setVisibility(View.GONE);
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public String requestToString(String path){

        return "0";
    }






    //使用Handler实现异步功能
    /*
    *1.创建Handler成员变量对象，并重写其handlerMessage()
    *2.在分/主线程创建Message对象
    *3. 使用Handler对象发送Message
    * 4.在handleMessage()中处理消息
      * */

    public void getSubmit2(View view){
        //2.在分/主线程创建Message对象


        //1.主线程，显示提示视图
        pb_handler1_loading.setVisibility(View.VISIBLE);
        //2.分线程，联通请求，并得到响应数据
        new Thread(){
            @Override
            public void run() {
                String path="https://www.baidu.com/";
                try {
                    final String result=requestToString(path);

                    //3.主线程，显示数据
                   
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
