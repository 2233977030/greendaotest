package com.example.greendaotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;
import com.anye.greendao.gen.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "student.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();

        UserDao userDao = daoSession.getUserDao();
        User user = new User();
        User user1 = new User();

        user.setName("zone");
        user1.setName("zone333");

        userDao.insert(user);
        userDao.insert(user1);

        List<User> list=userDao.queryBuilder()
                .build()
                .list();
        for (int i = 0; i <list.size() ; i++) {
            Log.i("***list", "onCreate: "+list.get(i).getName());
        }

    }

    private void initView() {
        mLv = (ListView) findViewById(R.id.lv);

    }
}
