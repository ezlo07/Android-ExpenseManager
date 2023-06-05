package com.android.expensemanager;
public class Budget {
    private int budgetAmount;
    private String budgetName;
    public Budget(String a, int b){
        this.budgetName=a;
        this.budgetAmount=b;
    }
    public String getBudgetName(){
        return budgetName;
    }
    public void setBudgetName(String a){
        this.budgetName=a;
    }
    public int getBudgetAmount(){
        return budgetAmount;
    }
    public void setBudgetAmount(int a){
        this.budgetAmount = a;
    }
    public void decreaseAmount(int value) {
        budgetAmount -= value;
    }
}
