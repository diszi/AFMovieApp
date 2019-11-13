package d2.hu.afmovieapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> implements Filterable {

    private List<Movie> moviesList = new ArrayList();
    private ArrayList<Movie> moviesFullList ;
    private MainActivity view;

    MainAdapter(){}

//    MainAdapter(List<Movies> movies, MainActivity activity){
//        this.view =activity;
//        this.moviesList.clear();
//        this.moviesList.addAll(movies);
//        moviesFullList = new ArrayList<>(this.moviesList);
//        this.notifyDataSetChanged();
//
//    }


    public void setMovies(List<Movie> movies, MainActivity activity) {

        this.view = activity;
        this.moviesList.clear();
        this.moviesList.addAll(movies);
        moviesFullList = new ArrayList<>(this.moviesList);
        this.notifyDataSetChanged();
    }
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_row,parent,false);
        return new MainAdapter.MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.bind(movie);
        holder.itemView.setOnClickListener(v -> {
            view.openMovieDetails(movie);

        });

    }

    @Override
    public int getItemCount() {
        if (moviesList !=null && moviesList.size() >0){
            return moviesList.size();
        }else{
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return senderFilter;
    }


    private Filter senderFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {


            ArrayList<Movie> filteredList = new ArrayList<>();
            if (constraint==null || constraint.length() == 0){
                filteredList.addAll(moviesFullList);
            }else{
                String filteredPattern  =  constraint.toString().toLowerCase();
                for (Movie movie : moviesFullList){
                    if (movie.getOriginal_title().toLowerCase().contains(filteredPattern)){

                        filteredList.add(movie);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            moviesList.clear();
            moviesList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    static class MainViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_View)
        CardView card;
        @BindView(R.id.title)
        TextView budget;
        @BindView(R.id.image_movie)
        ImageView image_movie;

         MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


        public void bind(Movie movie){
            budget.setText(movie.getOriginal_title());

        }


    }

}
