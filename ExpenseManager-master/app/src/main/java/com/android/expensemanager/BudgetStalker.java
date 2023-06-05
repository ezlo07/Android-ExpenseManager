package com.android.expensemanager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class BudgetStalker extends RecyclerView.Adapter<BudgetStalker.ViewHolder> {
    private Context ctext;
    private ArrayList<Budget> lists;
    public BudgetStalker(Context a, ArrayList<Budget> b) {
        this.ctext = a;
        this.lists = b;
    }
    @NonNull
    @Override
    public BudgetStalker.ViewHolder onCreateViewHolder(@NonNull ViewGroup prnt, int type) {
        View x = LayoutInflater.from(ctext).inflate(R.layout.budget_design,prnt,false);
        return new ViewHolder(x);
    }
    @Override
    public void onBindViewHolder(@NonNull BudgetStalker.ViewHolder hold, int post) {
        hold.bdgt.setText(lists.get(post).getBudgetName());
        hold.amnt.setText(Integer.toString(lists.get(post).getBudgetAmount()));
    }
    @Override
    public int getItemCount() {
        return lists.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bdgt,amnt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bdgt = itemView.findViewById(R.id.budgetdesign);
            amnt = itemView.findViewById(R.id.amountdesign);
        }
    }

}
