package com.Jsu.custombehavior;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by JSu on 2016/5/23.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final int mBackground;
    private String[] mDataset;

    private static final int VIEW_HEADER = 10086;
    private final TypedValue mTypedValue = new TypedValue();

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView);
            if (mTextView != null) {
                v.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            String text = "I Love " + mTextView.getText() + ".";
            Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
        }
    }

    public class HeaderViewHolder extends ViewHolder {

        public HeaderViewHolder(View v) {
            super(v);
        }
    }

    public MyAdapter(Context context, String[] myDataset) {
        mDataset = myDataset;
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADER;
        }
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        if (viewType == VIEW_HEADER) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_header, parent, false);
            v.setBackgroundResource(mBackground);
            return new HeaderViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_view, parent, false);
            v.setBackgroundResource(mBackground);
            return new ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder)
            return;
        holder.mTextView.setText(mDataset[position - 1]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length + 1;
    }
}
