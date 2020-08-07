package com.example.plant_e;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

class IntroViewerPageAdapter extends PagerAdapter {

    Context mcontext;
    List<ScreenObject> mListScreen;

    public IntroViewerPageAdapter(Context mcontext, List<ScreenObject> mListScreen) {
        this.mcontext = mcontext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutscreen = inflater.inflate(R.layout.layout_screen, null);

        ImageView imgslide = layoutscreen.findViewById(R.id.intro_img);
        TextView title = layoutscreen.findViewById(R.id.TitleText);
        TextView desc = layoutscreen.findViewById(R.id.IntroText);

        title.setText(mListScreen.get(position).getTitle());
        desc.setText(mListScreen.get(position).getDescription());
        imgslide.setImageResource(mListScreen.get(position).getScreenimg());


        container.addView(layoutscreen);


        return layoutscreen;


    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object ) {
        return view == object;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
