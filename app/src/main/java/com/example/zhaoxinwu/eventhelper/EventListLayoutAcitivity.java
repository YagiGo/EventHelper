package com.example.zhaoxinwu.eventhelper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhaoxinwu.shoppingListDB.DBHandler;


public class EventListLayoutAcitivity extends BaseActivity {
    private SwipeRefreshLayout mRefreshSrl;
    private RecyclerView mContentRv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //restore instance
        setContentView(R.layout.activity_event_list);

        mContentRv = findViewById(R.id.rv_content);
        mContentRv.setLayoutManager(new LinearLayoutManager(this));
        mContentRv.setAdapter(new ContentAdapter());

        mRefreshSrl = (SwipeRefreshLayout) findViewById(R.id.srl_refresh);
        mRefreshSrl.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
    }

    /*
    @Override
    public void onCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_samples, menu);
        return true;
    }
    */
    private class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder> {
        @Override
        public ContentAdapter.ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ContentHolder(LayoutInflater.from(EventListLayoutAcitivity.this)
            .inflate(R.layout.item_simple_list_1, parent, false));
        }

        @Override
        public void onBindViewHolder(ContentAdapter.ContentHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            DBHandler dbHandler = new DBHandler(getApplicationContext(), null);
            return dbHandler.countHandler();
        } // How many items will it show, by default it will show all the items in the database.

        class ContentHolder extends RecyclerView.ViewHolder {
            public ContentHolder(View itemView) {
                super(itemView);
            }
        }
    }

}
