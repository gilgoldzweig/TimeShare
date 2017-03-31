//package cc.time_share.android.adapters;
//
//import android.databinding.DataBindingUtil;
//import android.databinding.ObservableArrayList;
//import android.databinding.ViewDataBinding;
//import android.support.annotation.LayoutRes;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
///**
// * Created by gilgoldzweig on 31/03/2017.
// */
//
//public class UsersRecyclerAdapter<T> extends RecyclerView.Adapter<UsersRecyclerAdapter.GenericViewHolder> {
//    private ObservableArrayList<T> objects;
//    @LayoutRes
//    private Integer layoutResID;
//    private int variableID;
//
//    public UsersRecyclerAdapter (ObservableArrayList<T> objects, @LayoutRes Integer layoutResID,
//                                 int variableID) {
//        this.objects = objects;
//        this.layoutResID = layoutResID;
//        this.variableID = variableID;
//    }
//
//    @Override
//    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
//                layoutResID, parent, false);
//
//        return new GenericViewHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(GenericViewHolder holder, int position) {
//        T obj = objects.get(position);
//        ViewDataBinding viewDataBinding = holder.getViewDataBinding();
//        viewDataBinding.setVariable(variableID, obj);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return objects.size();
//    }
//
//
//    public class GenericViewHolder extends RecyclerView.ViewHolder {
//        private ViewDataBinding mViewDataBinding;
//
//        public GenericViewHolder(ViewDataBinding viewDataBinding) {
//            super(viewDataBinding.getRoot());
//
//            mViewDataBinding = viewDataBinding;
//            mViewDataBinding.executePendingBindings();
//        }
//        public ViewDataBinding getViewDataBinding() {
//            return mViewDataBinding;
//        }
//
//    }
//}
