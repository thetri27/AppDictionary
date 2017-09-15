package thetri.com.appdictionary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import thetri.com.appdictionary.adapters.WordAdapters;
import thetri.com.appdictionary.utilse.DictionaryModel;

public class MainActivity extends AppCompatActivity {
    public static final String fileDictionary="tudien.txt";
    private List<DictionaryModel> data;
    private RecyclerView rvWord;
    private WordAdapters wordAdapters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvWord=(RecyclerView) findViewById(R.id.rvWord);
        rvWord.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
         data=new ArrayList<>();
        readFromAssets(getApplicationContext(),fileDictionary);
        wordAdapters= new WordAdapters();
        wordAdapters.setData(data);
        rvWord.setAdapter(wordAdapters);

        SearchView searchView= (SearchView)findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchWord(newText);
                return false;
            }
        });


    }
    private void readFromAssets(Context context, String fileName){
        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String mLine;
            int i=0;
            while ((mLine=reader.readLine())!=null){
                String[] lineData= mLine.split("-");
                data.add(new DictionaryModel(""+i,lineData[0],lineData[1]));
                i++;

            }reader.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void searchWord(String wordSearch){
        data.clear();
        readFromAssets(getApplicationContext(),fileDictionary);
        List<DictionaryModel> temp=new ArrayList<>();
        for (DictionaryModel dictionaryModel : data){
            if (dictionaryModel.getWord().contains(wordSearch)){
                temp.add(dictionaryModel);
            }

        }
        data=temp;
        wordAdapters.setData(data);
    }
}
