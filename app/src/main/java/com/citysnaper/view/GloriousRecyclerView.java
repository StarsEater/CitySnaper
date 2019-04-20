package com.citysnaper.view;

/*
 * Copyright (C) 2017 CXP 277371483@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created on 17-2-14
 *
 * @author cxp
 */

public class GloriousRecyclerView extends RecyclerView {

    private View mHeaderView;
    private View mFooterView;
    private View mEmptyView;
    private GloriousAdapter mGloriousAdapter;

    public GloriousRecyclerView(Context context) {
        super(context);
    }

    public GloriousRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GloriousRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View view) {
        mHeaderView = view;
        mGloriousAdapter.notifyItemInserted(0);
    }

    public void addFooterView(View view) {
        mFooterView = view;
        mGloriousAdapter.notifyItemInserted(mGloriousAdapter.getItemCount() - 1);
    }

    public void setEmptyView(View view) {
        mEmptyView = view;
        mGloriousAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter != null) {
            mGloriousAdapter = new GloriousAdapter(adapter);
        }
        super.setAdapter(mGloriousAdapter);
    }

    private class GloriousAdapter extends RecyclerView.Adapter<ViewHolder> {

        private Adapter mOriginalAdapter;
        private int ITEM_TYPE_NORMAL = 0;
        private int ITEM_TYPE_HEADER = 1;
        private int ITEM_TYPE_FOOTER = 2;
        private int ITEM_TYPE_EMPTY = 3;

        //聪明的人会发现我们这里用了一个装饰模式
        public GloriousAdapter(Adapter originalAdapter) {
            mOriginalAdapter = originalAdapter;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == ITEM_TYPE_HEADER) {
                return new GloriousViewHolder(mHeaderView);
            } else if (viewType == ITEM_TYPE_EMPTY) {
                return new GloriousViewHolder(mEmptyView);
            } else if (viewType == ITEM_TYPE_FOOTER) {
                return new GloriousViewHolder(mFooterView);
            } else {
                return mOriginalAdapter.onCreateViewHolder(parent, viewType);
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int type = getItemViewType(position);
            if (type == ITEM_TYPE_HEADER || type == ITEM_TYPE_FOOTER || type == ITEM_TYPE_EMPTY) {
                return;
            }
            int realPosition = getRealItemPosition(position);
            mOriginalAdapter.onBindViewHolder(holder, realPosition);
        }

        @Override
        public int getItemCount() {
            int itemCount = mOriginalAdapter.getItemCount();
            //加上其他各种View
            if (null != mEmptyView && itemCount == 0) itemCount++;
            if (null != mHeaderView) itemCount++;
            if (null != mFooterView) itemCount++;
            return itemCount;
        }

        @Override
        public int getItemViewType(int position) {
            if (null != mHeaderView && position == 0) return ITEM_TYPE_HEADER;
            if (null != mFooterView && position == getItemCount() - 1) return ITEM_TYPE_FOOTER;
            if (null != mEmptyView && mOriginalAdapter.getItemCount() == 0) return ITEM_TYPE_EMPTY;
            return ITEM_TYPE_NORMAL;
        }

        private int getRealItemPosition(int position) {
            if (null != mHeaderView) {
                return position - 1;
            }
            return position;
        }

        /**
         * ViewHolder 是一个抽象类
         */
        class GloriousViewHolder extends ViewHolder {

            GloriousViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}

