package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ManagerActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private BaseExpandableListAdapter adapter;
    private HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        expandableListView = findViewById(R.id.expandableListView);
        expandableListDetail = getData();

        adapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return expandableListDetail.size();
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return expandableListDetail.get(getGroup(groupPosition)).size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                return expandableListDetail.keySet().toArray()[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return expandableListDetail.get(getGroup(groupPosition)).get(childPosition);
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                // Create or inflate the view for the group
                TextView textView = new TextView(ManagerActivity.this);
                textView.setText((String) getGroup(groupPosition));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18); // Set text size to 18sp
                textView.setGravity(Gravity.CENTER); // Center text horizontally
                return textView;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                // Create or inflate the view for the child
                TextView textView = new TextView(ManagerActivity.this);
                textView.setText((String) getChild(groupPosition, childPosition));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16); // Set text size to 16sp
                textView.setGravity(Gravity.CENTER); // Center text horizontally
                return textView;
            }


            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };

        expandableListView.setAdapter(adapter);

        // Set listener for child items click
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedItem = (String) adapter.getChild(groupPosition, childPosition);
                if (selectedItem.equals("Thêm rạp")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerAddCinemaActivity.class);
                    startActivity(intent);
                }
                else if (selectedItem.equals("Sửa thông tin rạp")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerEditCinemaActivity.class);
                    startActivity(intent);
                }
                else if (selectedItem.equals("Xóa rạp")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerDeleteCinemaActivity.class);
                    startActivity(intent);
                }
                else if (selectedItem.equals("Thêm phòng chiếu")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerAddHallActivity.class);
                    startActivity(intent);
                }
                else if (selectedItem.equals("Sửa thông tin phòng chiếu")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerEditHallActivity.class);
                    startActivity(intent);
                }
                else if (selectedItem.equals("Xóa phòng chiếu")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerDeleteHallActivity.class);
                    startActivity(intent);
                }
                else if (selectedItem.equals("Thêm suất chiếu")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerAddShowActivity.class);
                    startActivity(intent);
                }
                else if (selectedItem.equals("Sửa thông tin suất chiếu")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerEditShowActivity.class);
                    startActivity(intent);
                }else if (selectedItem.equals("Xóa suất chiếu")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerDeleteShowActivity.class);
                    startActivity(intent);
                }
                // Thêm các điều kiện xử lý cho các tùy chọn khác nếu cần
                return true;
            }
        });
    }

    // Sample data
    // Sample data
    // Sample data
    private LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<>();

        // Add "Thông tin chung" group
        List<String> generalInformationOptions = new ArrayList<>();
        generalInformationOptions.add("Đổi tên doanh nghiệp");
        generalInformationOptions.add("Đổi mật khẩu");
        generalInformationOptions.add("Đăng xuất");
        expandableListDetail.put("Thông tin chung", generalInformationOptions);

        // Add "Quản lý rạp" group
        List<String> theaterManagementOptions = new ArrayList<>();
        theaterManagementOptions.add("Thêm rạp");
        theaterManagementOptions.add("Sửa thông tin rạp");
        theaterManagementOptions.add("Xóa rạp");
        expandableListDetail.put("Quản lý rạp", theaterManagementOptions);

        // Add "Quản lý phòng chiếu" group
        List<String> cinemaRoomManagementOptions = new ArrayList<>();
        cinemaRoomManagementOptions.add("Thêm phòng chiếu");
        cinemaRoomManagementOptions.add("Sửa thông tin phòng chiếu");
        cinemaRoomManagementOptions.add("Xóa phòng chiếu");
        expandableListDetail.put("Quản lý phòng chiếu", cinemaRoomManagementOptions);

        // Add "Quản lý suất chiếu" group
        List<String> showtimeManagementOptions = new ArrayList<>();
        showtimeManagementOptions.add("Thêm suất chiếu");
        showtimeManagementOptions.add("Sửa thông tin suất chiếu");
        showtimeManagementOptions.add("Xóa suất chiếu");
        expandableListDetail.put("Quản lý suất chiếu", showtimeManagementOptions);

        return expandableListDetail;
    }


}
