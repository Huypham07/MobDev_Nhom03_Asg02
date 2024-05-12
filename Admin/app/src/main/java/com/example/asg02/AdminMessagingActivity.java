package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asg02.adapter.MessageAdapter;
import com.example.asg02.model.AdminMessage;
import com.example.asg02.model.Message;
import com.example.asg02.model.User;

import java.util.ArrayList;
import java.util.List;

public class AdminMessagingActivity extends BaseActivity {

    ImageButton backBtn;
    TextView title;
    ImageButton infoUserBtn;
    ListView messageListView;
    List<Message> messageList;
    MessageAdapter adapter;
    ImageButton addImageBtn;
    EditText messagingEditText;
    ImageButton sendMessageBtn;
    String messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_messaging);

        User user = (User) getIntent().getSerializableExtra("user");

        backBtn = findViewById(R.id.backBtn8);
        title = findViewById(R.id.userNameTitle);
        infoUserBtn = findViewById(R.id.infoUserBtn);
        messageListView = findViewById(R.id.messageListView);
        messageList = new ArrayList<>();
        adapter = new MessageAdapter(this, messageList);
        messageListView.setAdapter(adapter);
        addImageBtn = findViewById(R.id.addImageBtn);
        messagingEditText = findViewById(R.id.messagingEditText);
        sendMessageBtn = findViewById(R.id.sendMessageBtn);
        sendMessageBtn.setEnabled(false);

        backBtn.setOnClickListener(v -> finish());

        title.setText(user.getName());

        infoUserBtn.setOnClickListener(v -> {

        });

        addImageBtn.setOnClickListener(v -> selectImage());


        messagingEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                messageText = messagingEditText.getText().toString();
                if (!messageText.isEmpty()) {
                    sendMessageBtn.setEnabled(true);
                } else {
                    sendMessageBtn.setEnabled(false);
                }
            }
        });

        sendMessageBtn.setOnClickListener(v -> {
            sendMessage(messageText);
            messagingEditText.setText("");
        });
    }

    private void selectImage() {
    }

    private void sendMessage(String messageText) {
        Message message = new AdminMessage(messageText, null, null, null);
        messageList.add(message);
        adapter.notifyDataSetChanged();
        messageListView.smoothScrollToPosition(adapter.getCount());

        //Up message len firebase
    }

    //Them ham de lay cac message tren firebase
}