package thetri.com.appdictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DefinitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        String word=getIntent().getStringExtra("WORD");
        String definition=getIntent().getStringExtra("DEFINITION");

        TextView wordText=(TextView)findViewById(R.id.wordtext);
        TextView definitionText=(TextView)findViewById(R.id.definitionText);

        wordText.setText(word);
        definitionText.setText(definition);


    }
}
