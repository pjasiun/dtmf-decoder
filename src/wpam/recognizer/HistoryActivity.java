package wpam.recognizer;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;

public class HistoryActivity extends ListActivity 
{
	private ArrayList<HistoryItem> history;
    private HistoryRowAdapter historyRowAdapter;	
	
	@Override
     public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		history = new ArrayList<HistoryItem>();
		history.add(new HistoryItem());
		history.add(new HistoryItem());
		history.add(new HistoryItem());
		
		setContentView(R.layout.history);
		
		this.historyRowAdapter = new HistoryRowAdapter(this, R.layout.history_row, history);
        setListAdapter(this.historyRowAdapter);
		
	}
}
