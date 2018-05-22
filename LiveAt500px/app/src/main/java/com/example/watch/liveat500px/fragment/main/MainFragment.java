package com.example.watch.liveat500px.fragment.main;

import android.os.Bundle;
import android.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.watch.liveat500px.R;
import com.example.watch.liveat500px.adater.PhotoListAdapter;
import com.example.watch.liveat500px.dao.PhotoItemCollectionDao;
import com.example.watch.liveat500px.manager.HttpManager;
import com.example.watch.liveat500px.manager.PhotoListManager;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainFragment extends Fragment {
    private ListView listView;
    private PhotoListAdapter listAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PhotoListManager photoListManager;

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
         photoListManager = new PhotoListManager();
         listView = rootView.findViewById(R.id.listView);
         listAdapter = new PhotoListAdapter();
         listView.setAdapter(listAdapter);
         swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout);
         swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 refreshData();
             }
         });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view,
                                 int firstVisibleItem,
                                 int visibleItemCount,
                                 int totalItemCount) {
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0);

            }
        });
        refreshData();
    }
    private void refreshData(){
        if(photoListManager.getCount() == 0)
            reloadData();
        else
            reloadDataNewer();
    }

    class PhotoListLoadCallbck implements  Callback<PhotoItemCollectionDao>{
        public static final int MODE_RELOAD = 1;
        public static final int MODE_RELOAD_NEWER = 2;
        int mode;
        public PhotoListLoadCallbck(int mode){
            this.mode = mode;
        }

        @Override
        public void onResponse(Call<PhotoItemCollectionDao> call, Response<PhotoItemCollectionDao> response) {
            swipeRefreshLayout.setRefreshing(false);
            if(response.isSuccessful()){
                PhotoItemCollectionDao dao = response.body();

                int fristVisiblePosition = listView.getFirstVisiblePosition();
                View c = listView.getChildAt(0);
                int top = c == null ? 0:c.getTop();

                if(mode == MODE_RELOAD_NEWER)
                    photoListManager.insertDaoAtTopPosition(dao);
                else
                    photoListManager.setDao(dao);
                photoListManager.insertDaoAtTopPosition(dao);
                listAdapter.setDao(photoListManager.getDao());
                listAdapter.notifyDataSetChanged();

                if(mode == MODE_RELOAD_NEWER){
                    int additionalSize =
                            (dao != null && dao.getData() != null)?dao.getData().size():0;
                    listAdapter.increateLastPosition(additionalSize);
                    listView.setSelectionFromTop(fristVisiblePosition+ additionalSize,
                            top);
                }
                Toast.makeText(Contextor.getInstance().getContext(),
                        "Load Compleated",
                        Toast.LENGTH_SHORT).show();
            }else{
                try {
                    Toast.makeText(Contextor.getInstance().getContext(),
                            response.errorBody().string(),
                            Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onFailure(Call<PhotoItemCollectionDao> call, Throwable t) {
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(Contextor.getInstance().getContext(),
                    t.toString(),
                    Toast.LENGTH_SHORT).show();


        }
    }




    private void reloadDataNewer() {
         int maxId = photoListManager.getMaximumId();
        Call<PhotoItemCollectionDao> call = HttpManager.getInstance()
                .getService()
                .loadPhotoListAfterID(maxId);

        call.enqueue(new PhotoListLoadCallbck(PhotoListLoadCallbck.MODE_RELOAD_NEWER));

    }

    private void reloadData() {
        Call<PhotoItemCollectionDao> call = HttpManager.getInstance()
                .getService()
                .loadPhotoList();
        call.enqueue(new PhotoListLoadCallbck(PhotoListLoadCallbck.MODE_RELOAD));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
}
