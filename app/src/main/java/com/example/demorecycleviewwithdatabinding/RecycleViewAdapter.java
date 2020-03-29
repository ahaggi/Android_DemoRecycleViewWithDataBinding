package com.example.demorecycleviewwithdatabinding;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demorecycleviewwithdatabinding.databinding.ViewCatBinding;
import com.example.demorecycleviewwithdatabinding.model.Cat;

import java.util.ArrayList;

// 1- create the adapterClass "with out extending anything"
// 2- create the viewHolderClass "MyViewHolder" and extend RecyclerView.ViewHolder:
//     This class will hold the  viewItem.xml in memory for each individual entry
//     BUT IT WILL CONTAIN AN INSTANCE OF THE GENERATED-BINDING-CALSS, INSTEAD OF SOME WIDGETS OF THE XML-LAYOUT FILE.

// 3- extend the adapterClass with RecyclerView.Adapter<T> where T is the newly created viewHolderClass "MyViewHolder"
// 4- Add the req members and init them in the default constructor
// 5- implement onCreateViewHolder and create & return an instance of "MyViewHolder"
// 6- implement onBindViewHolder
// TLDR; bunch of viewholder will be inflated in "onCreateViewHolder"; and then the adapter will recycle and fill them with data in "onBindViewHolder"

// IMPORTANT take a look at https://medium.com/androiddevelopers/android-data-binding-recyclerview-db7c40d9f0e4
//                          https://medium.com/@sanjeevy133/an-idiots-guide-to-android-recyclerview-and-databinding-4ebf8db0daff
//                          https://guides.codepath.com/android/using-the-recyclerview#binding-the-adapter-to-the-recyclerview
//                          https://www.youtube.com/watch?v=Vyqz_-sJGFk


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    private static final String TAG = "RecycleViewAdapter";

    private ArrayList<Cat> cats;
    private Context context;

    public RecycleViewAdapter(Context context, ArrayList<Cat> cats) {
        this.cats = cats;
        this.context = context;
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is the place where the RecyclerView comes when it needs a new ViewHolder for a particular type of view.
        // Initialisation specific things like setting onClickListeners should be done here.
        // IMPORTANT TO NOTICE THAT THE VIEW "HOLDER" WILL BE INFLATED BUT WITHOUT ANY DATA HERE

        // "ViewGroup parent" is the recycleView

//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cat, parent, false);
//        MyViewHolder viewHolder = new MyViewHolder(view);
//        1:
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

//        2
        ViewCatBinding mBinding = ViewCatBinding.inflate(layoutInflater, parent, false);
        // OR
//      ViewCatBinding mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_cat, parent, false);


        MyViewHolder viewHolder = new MyViewHolder(mBinding);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        // NOW HERE WILL THE ADAPTER FILL THE VIEW "HOLDER" WITH GIVEN DATA
        // this method will be called every time an item is add to the list
        // Data is attached to the View in this function. Keep in mind that ViewHolders are recycled so the same ViewHolder will be used with some other Data as well,
        // so better update data each time this function is called.



/*    instead of this
        Glide.with(context).asBitmap().load(cats.get(position).getImage())    .apply(new RequestOptions().override(64, 64)).into(holder.itemImageView);
        holder.itemTextView.setText(cats.get(position).getId());
*/
        Cat cat = cats.get(position);
        holder.bind(cat);



/*    instead of this
        // if we want some action to happen when the user clicks an an item
        holder.catLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + cats.get(position));
                Toast.makeText(context, cats.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });

*/
//      Do this
//      we can access the (view/xml-layout's widgets) through:
//          1-   ((MyViewHolder) holder).itemView  ==> Notice that we don't have an instance variable "itemView" inside "MyViewHolder" but it is an instance variable of the superClass "ViewHolder"
//          2-   ((MyViewHolder) holder).mBinding.catLinearLayout  ==> if the root widget in the xml-layout file has an id
//          3-   ((MyViewHolder) holder).mBinding.getRoot()  ==> which is the same as "holder.mBinding.catLinearLayout"

        if (holder.itemView.getId() == holder.mBinding.getRoot().getId() && holder.itemView.getId() == holder.mBinding.catLinearLayout.getId())
            Log.d(TAG, "It's the same View..   It's the same View..   It's the same View..   It's the same View..   ");

        //---------------------------------The flwg listner are for the same widget----------------------------------------------
        holder.itemView.setOnClickListener(v -> {
            Log.d(TAG, "onClick: clicked on catLinearLayout " + cat.getId());
            Toast.makeText(context, cat.getId()+ " catLinearLayout", Toast.LENGTH_SHORT).show();

        });
        holder.mBinding.catLinearLayout.setOnClickListener(v -> {
            Log.d(TAG, "onClick: clicked on catLinearLayout " + cat.getId());
            Toast.makeText(context, cat.getId()+ " catLinearLayout", Toast.LENGTH_SHORT).show();

        });
        holder.mBinding.getRoot().setOnClickListener(v -> {
            Log.d(TAG, "onClick: clicked on catLinearLayout " + cat.getId());
            Toast.makeText(context, cat.getId() + " catLinearLayout", Toast.LENGTH_SHORT).show();

        });
        //-------------------------------------------------------------------------------
        holder.mBinding.catImageView.setOnClickListener(v -> {
            Log.d(TAG, "onClick: clicked on catImageView " + cat.getId());
            Toast.makeText(context, cat.getId() + " catImageView", Toast.LENGTH_SHORT).show();

        });

        holder.mBinding.catTextView.setOnClickListener(v -> {
            Log.d(TAG, "onClick: clicked on catTextView " + cat.getId());
            Toast.makeText(context, cat.getId() + " catTextView", Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        //Whenever a ViewHolder occurs on the Screen, this callback is fired, User oriented events like Playing Videos or Audios when Views come onto screen should be done inside this.
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewRecycled(@NonNull MyViewHolder holder) {
        //Once a ViewHolder is successfully recycled, onViewRecycled gets called. This is when we should release resources held by the ViewHolder
        super.onViewRecycled(holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        //When the ViewHolder goes off the screen, this gets called. Perfect place to pause videos and audios, or other Memory intensive events.
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    // return the size of the data
    public int getItemCount() {
        return cats.size();
    }


    // This class will hold the widget/viewItem.xml in memory for each individual entry/data
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "MyViewHolder";
        /*    instead of this
                ImageView itemImageView;
                TextView itemTextView;
                LinearLayout catLinearLayout;
        */
        private ViewCatBinding mBinding;


        public MyViewHolder(@NonNull ViewCatBinding mBinding) {
/*    instead of this
            super(v);
            itemImageView = v.findViewById(R.id.itemImageView);
            itemTextView = v.findViewById(R.id.itemTextView);
            catLinearLayout = v.findViewById(R.id.catLinearLayout);
*/
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }

        /**
         * We will use this function to bind instance of Cat to a view
         */
        public void bind(Cat cat) {
            mBinding.setCat(cat);
//            The executePendingBindings() is important!
//            This forces the bindings to run immediately instead of delaying them until the next frame. RecyclerView will measure the view immediately
//            after onBindViewHolder. If the wrong data is in the views because the binding is waiting until the next frame, it will be measured improperly.
            mBinding.executePendingBindings();
        }
    }
}
