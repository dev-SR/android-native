package com.example.a01eventhandler_java;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class FruitAdapter extends BaseAdapter {
    private Context context;
    private List<Fruit> fruitList;

    public FruitAdapter(Context context, List<Fruit> fruitList) {
        this.context = context;
        this.fruitList = fruitList;
    }

    @Override
    public int getCount() {
        return fruitList != null ? fruitList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    /**
     `getView` method will return the final view that a Spinner will set to it’s row’s ith (first parameter) position.
     If Spinner has six rows then compiler will this method for six times. In every iteration,
     compiler will fetch or inflate the XML file which will provide UI widgets (Textview, Button, ImageView etc.)
     for the row item. Then it will set the appropriate values to the UI widgets (Textview, Button, ImageView etc.)
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /** When you write an XML layout, it will be inflated by the Android OS which basically
         * means that it will be rendered by creating view object in memory.

         You can also inflate views explicitly by using the LayoutInflater. In that case you have to:
         In that case you have to:
         */
        //1. Get an instance of the LayoutInflater
        //2. Specify the XML to inflate
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.item_fruit, viewGroup, false);
        //3. Use the returned RootView to get it's children
        TextView txtName = rootView.findViewById(R.id.name);
        ImageView image = rootView.findViewById(R.id.image);
        //4. Set the content view with returned view
        txtName.setText(fruitList.get(i).getName());
        image.setImageResource(fruitList.get(i).getImage());

        return rootView;
    }
}