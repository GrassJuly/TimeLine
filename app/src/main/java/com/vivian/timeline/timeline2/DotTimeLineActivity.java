package com.vivian.timeline.timeline2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.vivian.timeline.R;
import com.vivian.timeline.itemdecoration.DotItemDecoration;
import com.vivian.timeline.itemdecoration.SpanIndexListener;

import java.util.ArrayList;
import java.util.List;

public class DotTimeLineActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;

    List<Event> mList = new ArrayList<>();
    DotTimeLineAdapter mAdapter;
    DotItemDecoration mItemDecoration;

    long[] times = {
            1497229200,
            1497240000,
            1497243600,
            1497247200,
            1497249000,
            1497252600
    };
    String[] events = new String[]{
            "去小北门拿快递",
            "跟同事一起聚餐",
            "写文档",
            "和产品开会",
            "整理开会内容",
            "提交代码到git上"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dot_timeline_layout);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mItemDecoration = new DotItemDecoration
                .Builder(this)
                .setItemStyle(DotItemDecoration.STYLE_DRAW)
                .setTopDistance(20)//dp
                .setItemInterVal(10)//dp
                .setItemPaddingLeft(10)//default value equals to item interval value
                .setItemPaddingRight(10)//default value equals to item interval value
                .setDotColor(Color.WHITE)
                .setDotRadius(2)//dp
                .setLineColor(Color.RED)
                .setLineWidth(1)//dp
                .setEndText("END")
                .setTextColor(Color.WHITE)
                .setTextSize(10)//sp
                .setDotPaddingText(2)//dp.The distance between the last dot and the end text
                .setBottomDistance(40)//you can add a distance to make bottom line longer
                .create();
        mItemDecoration.setSpanIndexListener(new SpanIndexListener() {
            @Override
            public void onSpanIndexChange(View view, int spanIndex) {
                view.setBackgroundResource(spanIndex == 0 ? R.drawable.pop_left : R.drawable.pop_right);
            }
        });
        mRecyclerView.addItemDecoration(mItemDecoration);

        for (int i = 0; i < times.length; i++) {
            Event event = new Event();
            event.setTime(times[i]);
            event.setEvent(events[i]);
            mList.add(event);
        }

        mAdapter = new DotTimeLineAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
