package com.android.expensemanager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    BudgetStalker adpt;
    RecyclerView rcview;
    TextInputLayout bdgt,amnt;
    ArrayList<Budget> lists;
    Button adding;
    TextView sum,totalincome,result;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcview = findViewById(R.id.rcview);
        lists = new ArrayList<>();
        bdgt = findViewById(R.id.budget);
        amnt = findViewById(R.id.price);
        adding = findViewById(R.id.addbutton);
        sum = findViewById(R.id.totalamount);
        adpt = new BudgetStalker(this,lists);
        LinearLayoutManager llm = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcview.setLayoutManager(llm);
        rcview.setAdapter(adpt);
        result= findViewById(R.id.result);
        totalincome = findViewById(R.id.totalincome);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Data");
        View view = getLayoutInflater().inflate(R.layout.custom_dialog,null);
        EditText eincome;
        eincome = view.findViewById(R.id.income);
        Button echeckincome = view.findViewById(R.id.checkincome);
        echeckincome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                totalincome.setText("Total Income: "+eincome.getText().toString() + " TL");
                int totalAmount = 0;
                for (int j = 0; j < lists.size(); j++) {
                    totalAmount += lists.get(j).getBudgetAmount();
                    sum.setText("Total Amount " +totalAmount+" TL");
                    String [] parts = totalincome.getText().toString().split(" ");
                    String sa = parts[2];
                    int as = Integer.parseInt(sa);
                    int ba = Integer.parseInt(String.valueOf(totalAmount));
                    int res = as-ba;
                    result.setText("Result: "+ res + " TL");
                }
                dialog.dismiss();
            }
        });
        builder.setView(view);
        dialog = builder.create();
        totalincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalincome = findViewById(R.id.totalincome);

                if ((bdgt.getEditText().getText().toString().trim().isEmpty()) || (amnt.getEditText().getText().toString().trim().isEmpty())){
                    // The budget name text input is empty
                }
                else {
                    int totalAmount = 0;
                    String ex = bdgt.getEditText().getText().toString();
                    int a = Integer.parseInt(amnt.getEditText().getText().toString());
                    lists.add(new Budget(ex, a));

                    adpt.notifyDataSetChanged();
                    bdgt.getEditText().setText("");
                    amnt.getEditText().setText("");

                    for (int j = 0; j < lists.size(); j++) {

                        totalAmount += lists.get(j).getBudgetAmount();
                        sum.setText("Total Amount " +totalAmount+" TL");

                            String [] parts = totalincome.getText().toString().split(" ");
                            String sa = parts[2];
                            try {
                                int as = Integer.parseInt(sa);
                                int ba = Integer.parseInt(String.valueOf(totalAmount));
                                int res = as-ba;
                                result.setText("Result: "+ res + " TL");

                            } catch (NumberFormatException e){
                            }


                    }
                }
            }
        });
    }
}