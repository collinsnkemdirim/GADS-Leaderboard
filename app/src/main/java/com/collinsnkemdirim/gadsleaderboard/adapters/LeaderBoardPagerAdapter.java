package com.collinsnkemdirim.gadsleaderboard.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gadsleaderboard.R;
import com.collinsnkemdirim.gadsleaderboard.fragments.SkillLeadersFragment;
import com.collinsnkemdirim.gadsleaderboard.fragments.TopLearnersFragment;

public class LeaderBoardPagerAdapter extends FragmentPagerAdapter {

    public Context mContext;


    public LeaderBoardPagerAdapter(@NonNull Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

   @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
           return new TopLearnersFragment();
        }else{
           return new SkillLeadersFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 2;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.top_learners);
        } else {
            return mContext.getString(R.string.skill_iq_leaders);
        }
    }
}
