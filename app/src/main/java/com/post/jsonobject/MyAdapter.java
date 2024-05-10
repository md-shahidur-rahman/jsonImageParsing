    package com.post.jsonobject;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.squareup.picasso.Picasso;

    import java.util.ArrayList;
    import java.util.HashMap;

    public class MyAdapter extends BaseAdapter {

        // Variables to hold context and data

        private Context mContext;
        private ArrayList<HashMap<String, String>> mData;

        // Constructor to initialize the adapter with context and data

        public MyAdapter(Context context, ArrayList<HashMap<String, String>> data) {
            mContext = context;
            mData = data;
        }

        /*** Return the number of items in the data set***/

        @Override
        public int getCount() {
            return mData.size();
        }

        /// Get the data item associated with the specified position in the data set

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        // Get the row id associated with the specified position in the list

        @Override
        public long getItemId(int position) {
            return position;
        }

        // Get a View that displays the data at the specified position in the data set

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // ViewHolder pattern for better performance

            ViewHolder holder;

            // If convertView is null, inflate the layout

            if (convertView == null) {

                // Inflate the layout for each row

                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.item, parent, false);

                // Create ViewHolder object to hold references to the views

                holder = new ViewHolder();
                holder.textView = convertView.findViewById(R.id.textView);
                holder.imageView = convertView.findViewById(R.id.imageView);

                // Set tag to convertView to store ViewHolder object

                convertView.setTag(holder);
            } else {

                // If convertView is not null, reuse the ViewHolder object
                holder = (ViewHolder) convertView.getTag();
            }

            // Get the data item for this position
            HashMap<String, String> item = mData.get(position);

            // Extract the required data from the item
            String title = item.get("title");
            String description = item.get("description");
            String price = item.get("price");
            String thumbnail = item.get("thumbnail");

            // Update the TextView with the extracted data

            holder.textView.setText(title+"\n" + description + "\n" + price + "\n");

            // Load the image into the ImageView using Picasso library

            Picasso.get().load(thumbnail).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);

            // Return the completed view to render on screen

            return convertView;
        }

        // ViewHolder class to hold references to the views

        static class ViewHolder {
            TextView textView;
            ImageView imageView;
        }
    }
