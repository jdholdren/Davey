package mindlesscreations.dmbcontext.presentation.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mindlesscreations.dmbcontext.R;
import mindlesscreations.dmbcontext.domain.entities.Performance;

public class SearchAdapter extends ArrayAdapter<Performance> {
    private List<Performance> performances;

    public SearchAdapter(Context context, int resource, List<Performance> objects) {
        super(context, resource, objects);
        this.performances = objects;
    }

    @Override
    public int getCount() {
        return performances.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SearchViewHolder viewHolder;

        if (convertView != null) {
            viewHolder = (SearchViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_result, parent, false);
            viewHolder = new SearchViewHolder(convertView, this.performances.get(position));
            convertView.setTag(viewHolder);
        }

        String text;
        if (this.performances.get(position).getPerformanceDate() == null) {
            text = "Studio Version";
        } else {
            text = this.performances.get(position).getFormattedDate();
        }

        viewHolder.resultText.setText(text);

        return convertView;
    }

    public class SearchViewHolder {
        public TextView resultText;
        public Performance perf;

        public SearchViewHolder(View v, Performance perf) {
            this.resultText = (TextView) v.findViewById(R.id.result_text);
            this.perf = perf;
        }
    }
}
