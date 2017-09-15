package thetri.com.appdictionary.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import thetri.com.appdictionary.DefinitionActivity;
import thetri.com.appdictionary.R;
import thetri.com.appdictionary.utilse.DictionaryModel;

/**
 * Created by thetri on 13/09/2017.
 */

public class WordAdapters extends RecyclerView.Adapter<WordAdapters.ViewHoder> {
    public List<DictionaryModel> data;

    public WordAdapters(){

    }
    public void setData(List<DictionaryModel> data){
        this.data=data;
        notifyDataSetChanged();
    }
    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View wordView= inflater.inflate(R.layout.word_item,parent,false);
        ViewHoder viewHoder= new ViewHoder(wordView,context);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, int position) {
        DictionaryModel dictionaryModel=data.get(position);
        holder.wordText.setText(dictionaryModel.getWord());
        //Log.d("word",""+dictionaryModel.getWord()+","+dictionaryModel.getDefinition());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHoder extends RecyclerView.ViewHolder {
        public Context context;
        public TextView wordText;

        public ViewHoder(View itemView, final Context context) {
            super(itemView);
            this.context=context;

            wordText=(TextView) itemView.findViewById(R.id.wordtext);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion=getAdapterPosition();
                    DictionaryModel dictionaryModel=data.get(postion);

                    Intent intent= new Intent(context, DefinitionActivity.class);
                    intent.putExtra("WORD",dictionaryModel.getWord());
                    intent.putExtra("DEFINITION",dictionaryModel.getDefinition());
                    context.startActivity(intent);
                }
            });

        }
    }
}
