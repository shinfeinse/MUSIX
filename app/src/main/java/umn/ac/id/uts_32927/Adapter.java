package umn.ac.id.uts_32927;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<PlayListModel> mainModels;
    Context context;

    public Adapter(Context context,ArrayList<PlayListModel> mainModels) {
        this.context = context;
        this.mainModels = mainModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create View
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vh_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //set song title
        holder.textSongTitle.setText(mainModels.get(position).getSongTitle());

        //set OnClickListener
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra("Judul", mainModels.get(position).getSongTitle());
                intent.putExtra("Artist", mainModels.get(position).getSongArtist());
                intent.putExtra("Pos", mainModels.get(position).getSongPosition());
                intent.putExtra("Path", mainModels.get(position).getSongPath());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return mainModels.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageAlbumCover;
        TextView textSongTitle;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textSongTitle = itemView.findViewById(R.id.text_title);
            linearLayout = itemView.findViewById(R.id.linear_layout);

            textSongTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textSongTitle.setSelected(true);

        }
    }
}
