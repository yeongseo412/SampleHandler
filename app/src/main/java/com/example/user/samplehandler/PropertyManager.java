package com.example.user.samplehandler;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by user on 2016-07-28.
 */





public class PropertyManager {

    // SingleTone Design Pattern
    // 하나의 static 객체로 언제 어디서든 이 클래스를 사용 가능하도록 구현한 Manager Class

    // KEY_DEFINE
    // 오타로 난 오류를 방지하기 위해서 상수 정의해서 사용할 것을 권장
    public static final String KEY_LOADING = "로딩 진행 상황";


    public static PropertyManager instance;

    // 외부에서 PropertyManager.getInstance() 로 호출
    public static PropertyManager gerInstance() {

        if(instance == null) {
            instance =  new PropertyManager();
        }

        return instance;
    }

    SharedPreferences mPrefs;               // 메모지
    SharedPreferences.Editor mEditor;       // 펜

    Context context;

    private PropertyManager() {

    }

    // 읽고 쓰기 위한 setter(), getter() 메소드 구현

    // 종료할 때 호출
    public void setLoadingProgress(Context context, int progress) {


        // initialize
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mPrefs.edit();

        mEditor.putInt(KEY_LOADING, progress);
        mEditor.commit();
    }

    // 시작할 때 호출
    public int getLoadingProgress(Context context) {

        // initialize
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mPrefs.edit();

        // 두번째 인자: 만약에 이 키를 가진 데이터가 없는 경우 Default 로 불러올 값
        return mPrefs.getInt(KEY_LOADING, 0);
    }
}
