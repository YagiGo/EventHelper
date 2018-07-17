package com.example.zhaoxinwu.eventhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

public class EventListLayoutAcitivity extends BaseActivity {
    private SwipeRefreshLayout mRefreshSrl;
    private RecyclerView mContentRv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //restore instance
        setContentView(R.layout.activity_event_list);
    }
}
