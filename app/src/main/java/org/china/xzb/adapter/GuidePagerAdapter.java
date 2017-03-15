package org.china.xzb.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import org.china.xzb.R;
import org.china.xzb.activity.HomeActivity;
import org.china.xzb.utils.PreferenceUtil;

/**
 * Created by hch on 2017/3/13.
 */

public class GuidePagerAdapter extends PagerAdapter {

    private Activity context;
    int[] images = new int[]{R.drawable.bg_guide1, R.drawable.bg_guide2, R.drawable.bg_guide3};
    LayoutInflater mInflater;

    public GuidePagerAdapter(Activity context) {
        this.context = context;
//        mInflater=LayoutInflater.from(context);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.item_welcome_viewpager, null);
        ImageView iView = (ImageView) view.findViewById(R.id.iv_guide);
        iView.setBackgroundResource(images[position]);
        if (position == 2) {
            Button btn = (Button) view.findViewById(R.id.bt_guide);
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, HomeActivity.class));
                    PreferenceUtil.save(context, "isGuideShow", true);
                    context.finish();
                }
            });
        }
        container.addView(view);
        return view;
    }
}
