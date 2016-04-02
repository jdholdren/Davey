package mindlesscreations.dmbcontext.presentation.Lyrics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mindlesscreations.dmbcontext.R;
import mindlesscreations.dmbcontext.domain.entities.Performance;

public class AlternatePerfAdapter extends ArrayAdapter<Performance> {

    private List<Performance> performances;

    public AlternatePerfAdapter(Context context, int resource, List<Performance> objects) {
        super(context, resource, objects);
        this.performances = objects;
    }

    @Override
    public int getCount() {
        return performances.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final AlternatePerfHolder viewHolder;

        if (convertView != null) {
            viewHolder = (AlternatePerfHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.alternate_lyric_item, parent, false);
            viewHolder = new AlternatePerfHolder(convertView);
            convertView.setTag(viewHolder);
        }

        String text;
        if (this.performances.get(position).getPerformanceDate() == null) {
            text = "Studio Version";
        } else {
            text = this.performances.get(position).getPerformanceDate().toString();
        }

        viewHolder.alternateText.setText(text);
        viewHolder.performance = this.performances.get(position);

        return convertView;
    }

    public class AlternatePerfHolder {
        public TextView alternateText;
        public Performance performance;

        public AlternatePerfHolder(View view) {
            this.alternateText = (TextView) view.findViewById(R.id.alternate_date_text);
        }
    }
}
