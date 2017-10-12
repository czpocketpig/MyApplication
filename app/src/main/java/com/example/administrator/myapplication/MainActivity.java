package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.backup.FullBackupDataOutput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bean.ChildBean;
import bean.GroupBean;

public class MainActivity extends Activity {
    private ExpandableListView mListview;
    private List<ChildBean> childBeanList;
    private List<GroupBean> groupBeanList;
    private ChildBean childBean;
    private GroupBean groupBean;
//    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();


    }

    private void initView() {
        mListview = findViewById(R.id.list);
        initData();

    }

    private void initData() {
//        mInflater = LayoutInflater.from(this);
        groupBeanList = new ArrayList<GroupBean>();
        for (int i = 0; i < 5; i++) {
            groupBean = new GroupBean();
            groupBean.setSpacename("深圳航港" + i);
            groupBean.setChildsize(8);
            childBeanList=new ArrayList<ChildBean>();
            for (int j = 0; j < 8; j++) {
                childBean = new ChildBean();
                childBean.setPrice(1 + j);
                childBean.setSceondprice(1.6);
                childBean.setTitle("商务卡");
                childBean.setFlag(j);
                childBeanList.add(childBean);
            }
            groupBean.setChildBeanList(childBeanList);
            groupBeanList.add(groupBean);

        }
        MyAdapter myAdapter = new MyAdapter();
        mListview.setGroupIndicator(null);
        /**
         * ExpandableListView的组监听事件
         */
        mListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Toast.makeText(MainActivity.this, "第"+groupPosition+"组被点击了", Toast.LENGTH_SHORT).show();
//                if(groupBeanList.get(groupPosition).getIsexpand()){
//                    mListview.collapseGroup(groupPosition);
//                }else {
//                    mListview.expandGroup(groupPosition);
//                }

                return false;
            }
        });
        /**
         * ExpandableListView的组展开监听
         */
        mListview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                Toast.makeText(MainActivity.this, "第"+i+"组展开", Toast.LENGTH_SHORT).show();
                groupBeanList.get(i).setIsexpand(false);
            }
        });
        /**
         * ExpandableListView的组合拢监听
         */
        mListview.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                Toast.makeText(MainActivity.this, "第"+i+"组合拢", Toast.LENGTH_SHORT).show();
                groupBeanList.get(i).setIsexpand(true);
            }
        });
        /**
         * ExpandableListView的子元素点击监听
         */
        mListview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(MainActivity.this, "第"+i+"组的第"+i1+"被点击了", Toast.LENGTH_SHORT).show();
                return true;             }
        });
        mListview.setAdapter(myAdapter);

//        int groupCount =mListview.getCount();
//        for (int i=0;i<groupCount;i++){
//            mListview.expandGroup(i);
//        }


    }


    class MyAdapter extends BaseExpandableListAdapter {
        @Override
        public int getGroupCount() {
            return groupBeanList.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return i;
        }

        @Override
        public Object getGroup(int i) {
            return groupBeanList.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view=null;
            GroupHolder groupHolder=null;
            if (convertView!=null){
                view=convertView;
                groupHolder=(GroupHolder) view.getTag();

            }else {
                view=View.inflate(MainActivity.this,R.layout.group,null);
                groupHolder=new GroupHolder();
                groupHolder.mSpaceText=view.findViewById(R.id.group_text);
                view.setTag(groupHolder);
            }
            groupHolder.mSpaceText.setText(groupBeanList.get(groupPosition).getSpacename());
            return view;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            View view = null;
            ChildHolder childHolder;
            if (convertView != null) {
                view = convertView;
                childHolder = (ChildHolder) view.getTag();
            } else {
                view = View.inflate(MainActivity.this, R.layout.child, null);
                childHolder = new ChildHolder();
                childHolder.mPrice = view.findViewById(R.id.textTwo);
                childHolder.mSecondPrice = view.findViewById(R.id.textOne);
                childHolder.mStateText = view.findViewById(R.id.textThree);
                childHolder.mImage = view.findViewById(R.id.pic);
                view.setTag(childHolder);
            }
            childHolder.mImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "第" + groupPosition + "组的第" + childPosition + "个图标", Toast.LENGTH_SHORT).show();
                }
            });
            childHolder.mPrice.setText(groupBeanList.get(groupPosition).getChildBeanList().get(childPosition).getPrice() + "");
            int len = groupBeanList.get(groupPosition).getChildBeanList().size();
            System.out.println(len + "--------------");
            childHolder.mStateText.setText(groupBeanList.get(groupPosition).getChildBeanList().get(childPosition).getTitle());
            childHolder.mSecondPrice.setText(groupBeanList.get(groupPosition).getChildBeanList().get(childPosition).getSceondprice() + "");

            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }

    }

    static class GroupHolder {
        TextView mSpaceText;
    }

    static class ChildHolder {
        ImageView mImage;
        TextView mStateText;
        TextView mPrice;
        TextView mSecondPrice;
    }
}
