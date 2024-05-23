package com.example.asg02.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asg02.R;
import com.example.asg02.model.Booking;
import com.example.asg02.model.Payment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ManagerActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private BaseExpandableListAdapter adapter;
    private HashMap<String, List<String>> expandableListDetail;
    Dialog incomeReportDialog;
    TextView finishTextView, dayIncomeTextView, monthIncomeTextView, yearIncomeTextView, totalIncomeTextView;
    double dayIncome, monthIncome, yearIncome, totalIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        expandableListView = findViewById(R.id.expandableListView);
        expandableListDetail = getData();
        incomeReportDialog = new Dialog(ManagerActivity.this);
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
                TextView textView = new TextView(ManagerActivity.this);
                textView.setText((String) getGroup(groupPosition));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = new TextView(ManagerActivity.this);
                textView.setText((String) getChild(groupPosition, childPosition));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };


        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedItem = (String) adapter.getChild(groupPosition, childPosition);
                if (selectedItem.equals("Thêm rạp")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerAddCinemaActivity.class);
                    startActivity(intent);
                }
                else if (selectedItem.equals("Thêm phòng chiếu")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerAddHallActivity.class);
                    startActivity(intent);
                }
                else if (selectedItem.equals("Thêm suất chiếu")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerAddShowActivity.class);
                    startActivity(intent);
                }
                else if (selectedItem.equals("Đổi tên doanh nghiệp")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, InfoActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Đổi mật khẩu")) {
                    // Gọi intent để mở AddCinemaActivity
                    Intent intent = new Intent(ManagerActivity.this, ManagerChangePassword.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Đăng xuất")) {
                    // Gọi intent để mở AddCinemaActivity
                    SharedPreferences sharedPreferences = getSharedPreferences("account_info", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    Intent intent = new Intent(ManagerActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String selectedItem = (String) adapter.getGroup(groupPosition);
                if (selectedItem.equals("Chỉnh sửa thông tin/Xóa")) {
                    Intent intent = new Intent(ManagerActivity.this, ManagerEditAndDeleteActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Báo cáo doanh thu")) {
                    incomeReportDialog.setContentView(R.layout.dialog_income_report);
                    incomeReportDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    incomeReportDialog.setCancelable(false);
                    incomeReportDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                    dayIncomeTextView = incomeReportDialog.findViewById(R.id.dayIncome);
                    monthIncomeTextView = incomeReportDialog.findViewById(R.id.monthIncome);
                    yearIncomeTextView = incomeReportDialog.findViewById(R.id.yearIncome);
                    totalIncomeTextView = incomeReportDialog.findViewById(R.id.totalIncome);
                    finishTextView = incomeReportDialog.findViewById(R.id.finishWatching);
                    LocalDate currentDate = LocalDate.now();
                    Integer year = currentDate.getYear();
                    Integer month = currentDate.getMonthValue(); // Trả về giá trị tháng từ 1 đến 12
                    Integer day = currentDate.getDayOfMonth();
                    dayIncome = 0.0;
                    monthIncome = 0.0;
                    yearIncome = 0.0;
                    totalIncome = 0.0;
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Bookings");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot bookingSnapshot : dataSnapshot.getChildren()) {
                                Booking booking = bookingSnapshot.getValue(Booking.class);
                                if (booking != null) {
                                    Payment payment = booking.getPayment();
                                    String[] dayYearMonth = payment.getCreateOn().split("/");
                                    totalIncome = totalIncome + payment.getTotalAmount();
                                    if (year.toString().equals(dayYearMonth[2])) {
                                        yearIncome = yearIncome + payment.getTotalAmount();
                                        if (month.toString().equals(dayYearMonth[1]) || ("0" + month.toString()).equals(dayYearMonth[1])) {
                                            monthIncome = monthIncome + payment.getTotalAmount();
                                            if (day.toString().equals(dayYearMonth[0]) || (("0" + day.toString()).equals(dayYearMonth[0]))) {
                                                dayIncome = dayIncome + payment.getTotalAmount();
                                            }
                                        }
                                    }
                                    // Bạn có thể xử lý hoặc sử dụng danh sách payments ở đây
                                    Log.d("Firebase", "Payment Token: " + payment.getToken());
                                    Log.d("Firebase", "Total Amount: " + payment.getTotalAmount());
                                }
                            }
                            dayIncomeTextView.setText("Doanh thu trong ngày: " + dayIncome + " VND");
                            monthIncomeTextView.setText("Doanh thu trong tháng: " + monthIncome + " VND");
                            yearIncomeTextView.setText("Doanh thu trong năm: " + yearIncome + " VND");
                            totalIncomeTextView.setText("Tổng doanh thu: " + totalIncome + " VND");
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Xử lý lỗi
                            Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
                        }
                    });

                    finishTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            incomeReportDialog.dismiss();
                            Toast.makeText(ManagerActivity.this, "Finish", Toast.LENGTH_SHORT).show();

                        }
                    });
                    incomeReportDialog.show();


                } else if (selectedItem.equals("Thêm")) {
                    // Nếu là nhóm "Thêm", kiểm tra xem nó có đang được mở rộng hay không
                    if (expandableListView.isGroupExpanded(groupPosition)) {
                        // Nếu đang mở rộng, collapse nó lại
                        expandableListView.collapseGroup(groupPosition);
                    } else {
                        // Nếu không, mở rộng nó ra
                        expandableListView.expandGroup(groupPosition);
                    }
                }  if (selectedItem.equals("Thông tin chung")) {
                    // Nếu là nhóm "Thêm", kiểm tra xem nó có đang được mở rộng hay không
                    if (expandableListView.isGroupExpanded(groupPosition)) {
                        // Nếu đang mở rộng, collapse nó lại
                        expandableListView.collapseGroup(groupPosition);
                    } else {
                        // Nếu không, mở rộng nó ra
                        expandableListView.expandGroup(groupPosition);
                    }
                }
                return true;
            }
        });
    }

    // Updated sample data
    private LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<>();

        List<String> generalInformationOptions = new ArrayList<>();
        generalInformationOptions.add("Đổi tên doanh nghiệp");
        generalInformationOptions.add("Đổi mật khẩu");
        generalInformationOptions.add("Đăng xuất");
        expandableListDetail.put("Thông tin chung", generalInformationOptions);

        List<String> addOptions = new ArrayList<>();
        addOptions.add("Thêm rạp");
        addOptions.add("Thêm phòng chiếu");
        addOptions.add("Thêm suất chiếu");
        expandableListDetail.put("Thêm", addOptions);

        List<String> editDeleteOptions = new ArrayList<>();
        expandableListDetail.put("Chỉnh sửa thông tin/Xóa", editDeleteOptions);

        List<String> incomeReport = new ArrayList<>();
        expandableListDetail.put("Báo cáo doanh thu", incomeReport);
        return expandableListDetail;
    }
}

