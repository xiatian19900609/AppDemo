package com.xinfu.demo16;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.xinfu.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/09/20
 *     desc   :
 * </pre>
 */
public class Demo16Activity extends AppCompatActivity {

    @InjectView(R.id.sidebar)
    SideBar sidebar;
    @InjectView(R.id.listview)
    ListView listview;
    private SortAdapter sortadapter;
    private List<PersonBean> data;
    TreeSet<String> set = new TreeSet<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo16);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        data = getData(getResources().getStringArray(R.array.listpersons));
        for (PersonBean bean : data) {
            set.add(bean.getFirstPinYin());
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s : set) {
            arrayList.add(s);
        }
        sidebar.setA_Z(arrayList);

        // 数据在放在adapter之前需要排序
        Collections.sort(data, new PinyinComparator());
        sortadapter = new SortAdapter(this, data);
        listview.setAdapter(sortadapter);


        // 设置字母导航触摸监听
        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // TODO Auto-generated method stub
                // 该字母首次出现的位置
                int position = sortadapter.getPositionForSelection(s.charAt(0));

                if (position != -1) {
                    listview.setSelection(position);
                }
            }
        });
    }

    private List<PersonBean> getData(String[] data) {
        List<PersonBean> listarray = new ArrayList<PersonBean>();
        for (int i = 0; i < data.length; i++) {
            String pinyin = PinyinUtils.getPingYin(data[i]);
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            PersonBean person = new PersonBean();
            person.setName(data[i]);
            person.setPinYin(pinyin);
            // // 正则表达式，判断首字母是否是英文字母
            if (Fpinyin.matches("[A-Z]")) {
                person.setFirstPinYin(Fpinyin);
            } else {
                person.setFirstPinYin("#");
            }

            listarray.add(person);
        }
        return listarray;

    }
}
