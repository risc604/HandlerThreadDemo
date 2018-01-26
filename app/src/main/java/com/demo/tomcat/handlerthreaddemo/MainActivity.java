package com.demo.tomcat.handlerthreaddemo;

import android.os.Handler;
import android.os.Message;
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
    }

    private void initControl()
    {
        showHandler = new FHandler();

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
            super.handleMessage(msg);
        }
    }

    class Thread_1 extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            Message msg = Message.obtain();
            msg.what = 1;
            msg.obj = "AAA ";
            showHandler.sendMessage(msg);

            super.run();
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

            Message msg = Message.obtain();
            msg.what = 2;
            msg.obj = "BBB ";
            showHandler.sendMessage(msg);

            super.run();
        }

    }



}

