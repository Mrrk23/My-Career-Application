package com.example.mycareer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<CollegeViewHolder> implements Filterable {

    private Context context;
    private List<CollegeData> myCollegeList;
    List<CollegeData> listall;

    public MyAdapter(Context context, List<CollegeData> myCollegeList) {
        this.context = context;
        this.myCollegeList = myCollegeList;
        this.listall = new ArrayList<>(myCollegeList);
    }

    @Override
    public CollegeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_item,parent,false);
        return new CollegeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CollegeViewHolder holder, int position) {
        holder.imageView.setImageResource(myCollegeList.get(position).getCollegeImage());
        holder.mName.setText(myCollegeList.get(position).getCollegeName());
        holder.mDetails.setText(myCollegeList.get(position).getCollegeDetails());
        //holder.mFee.setText(myCollegeList.get(position).getCollegeFee());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,CollegeDetails.class);
                intent.putExtra("Name",myCollegeList.get(holder.getAdapterPosition()).getCollegeName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myCollegeList.size();
    }

    Filter filter = new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<CollegeData> filteredList = new ArrayList<>();
            String key = charSequence.toString();
            if(key.isEmpty()){
                filteredList.addAll(listall);
            }
            else {
                for(CollegeData row : myCollegeList)
                {
                    if(row.getCollegeName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(row);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }
        //run on a UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            myCollegeList.clear();
            myCollegeList.addAll((Collection<? extends CollegeData>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return filter;
    }
}

class CollegeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView imageView;
    TextView mName,mDetails;
    //TextView mFee;
    CardView cardView;

    public CollegeViewHolder( View itemView) {
        super(itemView);

        imageView=itemView.findViewById(R.id.ivImage);
        mName=itemView.findViewById(R.id.tvName);
        mDetails=itemView.findViewById(R.id.tvDetails);
        //mFee=itemView.findViewById(R.id.tvFee);
        cardView=itemView.findViewById(R.id.myCardView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
