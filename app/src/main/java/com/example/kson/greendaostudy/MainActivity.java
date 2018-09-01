package com.example.kson.greendaostudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.kson.greendaostudy.api.UserApi;
import com.example.kson.greendaostudy.common.Constants;
import com.example.kson.greendaostudy.gen.UserBeanDao;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * 增删改查
     *
     * @param view
     */
    public void click(View view) {

        UserBeanDao userBeanDao = App.getInstances().getDaoSession().getUserBeanDao();

        //增加一条数据
//        UserBean userBean = new UserBean();
//        userBean.setName("kson");
//        userBean.setAge(100);
//        userBeanDao.save(userBean);//update和insert，
//
//        userBeanDao.insert(userBean);

        //根据主键删除
        userBeanDao.deleteByKey(1l);

        //查询
        List<UserBean> list = userBeanDao.loadAll();//查询所有记录
        System.out.println("size:" + list.size());

        //查询2
        List<UserBean> list1 = userBeanDao.queryBuilder().list();

        //根据查询条件返回list
        List<UserBean> list2 = userBeanDao.queryBuilder().where(UserBeanDao.Properties.Name.eq("kson"), UserBeanDao.Properties.Age.eq(100)).list();


        for (UserBean userBean : list) {
            //通过bean类删除
//            userBeanDao.delete(userBean);//所有的都删除
            if (userBean.getAge() == 1000) {
//                userBeanDao.delete(userBean);//删除单个对象
                //更新数据
                userBean.setAge(100);
                userBeanDao.update(userBean);//更新


            }


        }

    }

    /**
     * retrofit请求
     *
     * @param view
     */
    public void login(View view) {

        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
//                .addInterceptor()
                .build();

//外观模式，第一步创建retrofit管理类对象，和OK一样
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)//配置主机名
                .build();

        //创建api接口实现
        UserApi userApi = retrofit.create(UserApi.class);


        //请求接口
        userApi.login("18612990123", "222222").enqueue(new Callback<com.example.kson.greendaostudy.entity.UserBean>() {
            @Override
            public void onResponse(Call<com.example.kson.greendaostudy.entity.UserBean> call, Response<com.example.kson.greendaostudy.entity.UserBean> response) {

                com.example.kson.greendaostudy.entity.UserBean userBean = response.body();
                System.out.println("userbean:"+userBean.msg);
            }

            @Override
            public void onFailure(Call<com.example.kson.greendaostudy.entity.UserBean> call, Throwable t) {

            }
        });


    }
}
