package com.example.kson.greendaostudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.kson.greendaostudy.gen.UserBeanDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * 增删改查
     * @param view
     */
    public void click(View view) {

        UserBeanDao userBeanDao = App.getInstances().getDaoSession().getUserBeanDao();

        //增加一条数据
        UserBean userBean = new UserBean();
        userBean.setName("kson");
        userBean.setAge(100);
        userBeanDao.save(userBean);
    }
}
