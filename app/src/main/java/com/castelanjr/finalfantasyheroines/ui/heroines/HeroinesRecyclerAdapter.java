package com.castelanjr.finalfantasyheroines.ui.heroines;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.castelanjr.finalfantasyheroines.R;
import com.castelanjr.finalfantasyheroines.data.api.model.Heroine;
import com.castelanjr.finalfantasyheroines.data.api.model.HeroinesResponse;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.functions.Action1;

/**
 * Created by angelocastelanjr on 5/30/15.
 */
public class HeroinesRecyclerAdapter extends RecyclerView.Adapter<HeroinesRecyclerAdapter.ViewHolder>
        implements Action1<HeroinesResponse> {

    private List<Heroine> heroines = Collections.emptyList();
    private final Picasso picasso;

    public HeroinesRecyclerAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void call(HeroinesResponse response) {
        if (response != null || response.heroines == null) {
            this.heroines = response.heroines;
        } else {
            this.heroines = Collections.emptyList();
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_heroines,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bindTo(heroines.get(i));
    }

    @Override
    public int getItemCount() {
        return heroines.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.layout_heroine)
        LinearLayout layoutHeroine;

        @InjectView(R.id.image_heroine)
        ImageView imageHeroine;

        @InjectView(R.id.text_name)
        TextView textName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void bindTo(Heroine heroine) {
            layoutHeroine.setBackgroundColor(heroine.getColor());
            textName.setText(heroine.name());
            picasso.load(heroine.avatar()).into(imageHeroine);
        }
    }
}
