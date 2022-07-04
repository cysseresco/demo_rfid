package diego.salcedo.readermanager.ui.reader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import diego.salcedo.readermanager.R;

public class MessageViewAdapter extends RecyclerView.Adapter<MessageViewAdapter.MessageViewHolder>
{
    private List<String> mMessages;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class MessageViewHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        TextView mMessageTextView;

        MessageViewHolder(View itemView) {
            super(itemView);

            mMessageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
            mContext = itemView.getContext();
        }

        private Context mContext;

        void bind(String message)
        {
            mMessageTextView.setText(message);
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    MessageViewAdapter(List<String> readers) {
        mMessages = readers;
    }


    // Create new views (invoked by the layout manager)
    @Override
    @NonNull
    public MessageViewAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                   int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // create a new view
        View readerView = inflater.inflate(R.layout.message_line, parent, false);

        return new MessageViewHolder(readerView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String message = mMessages.get(position);
        holder.bind(message);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mMessages.size();
    }
}