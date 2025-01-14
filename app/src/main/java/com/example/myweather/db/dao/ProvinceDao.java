package com.example.myweather.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myweather.db.bean.Province;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface ProvinceDao {

    /**
     * 查询所有
     */
    @Query("SELECT * FROM Province")
    Flowable<List<Province>> getAll();

    /**
     * 插入所有
     * @param provinces 所有行政区数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(Province... provinces);
}

