package br.com.topin.topin.adapters.recycler;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.fragments.LineListFragment;
import br.com.topin.topin.models.Line;

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.ViewHolder> {
    private Fragment mFragment;
    private List<Line> mLines;

    public LineAdapter(Fragment fragment, List<Line> lines) {
        this.mFragment = fragment;
        this.mLines = lines;
    }

    @Override
    public LineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_line, parent, false);
        return new LineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LineAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.getTxtName().setText(mLines.get(position).getName());

        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LineListFragment) mFragment).onItemClicked(position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mLines != null ? mLines.size() : 0;
    }

    public void setLines(List<Line> lines) {
        this.mLines = lines;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private TextView txtName;


        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.txtName = itemView.findViewById(R.id.txt_line_name);

        }

        public TextView getTxtName() {
            return txtName;
        }

        public View getItemView() {
            return itemView;
        }

    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
        void onLongItemClicked(int position);
    }


}
