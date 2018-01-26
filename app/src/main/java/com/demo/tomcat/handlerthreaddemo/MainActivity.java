package com.demo.tomcat.handlerthreaddemo;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private TextView    show;
    private Handler     showHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initControl();

    }


    //-------- User define function. --------------------------------------//
    private void initView()
    {
        show = (TextView)findViewById(R.id.show);
        StringBuffer text = new StringBuffer();
        text.append("main UI -> " + SystemClock.currentThreadTimeMillis());
        show.setText(text);
    }

    private void initControl()
    {
        showHandler = new Handler();

        new Thread_1().start();
        new Thread_2().start();

    }

    class FHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            StringBuffer text = new StringBuffer();

            switch (msg.what)
            {
                case 1:
                    text.append("I like android Handler message.");
                    //show.setText(text);
                    break;

                case 2:
                    text.append("I hate Handler thread.");
                    //show.setText(text);
                    break;

            }
            show.setText(text);
            //super.handleMessage(msg);
        }
    }

    class Thread_1 extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            //Message msg = Message.obtain();
            //msg.what = 1;
            //msg.obj = "AAA ";
            //showHandler.sendMessage(msg);

            showHandler.post(new Runnable() {
                @Override
                public void run() {
                    String line = "\n";
                    StringBuffer text = new StringBuffer(show.getText());
                    text.append(line).append("Thread 1 -> " + SystemClock.currentThreadTimeMillis());
                    show.setText(text);
                }
            });
            //super.run();
        }
    }

    class Thread_2 extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                Thread.sleep(8000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            //Message msg = Message.obtain();
            //msg.what = 2;
            //msg.obj = "BBB ";
            //showHandler.sendMessage(msg);
            //super.run();

            showHandler.post(new Runnable() {
                @Override
                public void run() {
                    String line = "\n";
                    StringBuffer text = new StringBuffer(show.getText());
                    text.append(line).append(" Thread 2 -> " + SystemClock.currentThreadTimeMillis());
                    show.setText(text);
                }
            });
        }

    }



}

