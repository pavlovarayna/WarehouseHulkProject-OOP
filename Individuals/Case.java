package Individuals;

public class Case {

    private double income;
    private double outcome;

    public Case(double income, double outcome) {
        this.income = income;
        this.outcome = outcome;
    }


    double getIncome() {
        return income;
    }

    void setIncome(double income) {
        this.income = income;
    }

    double getOutcome() {
        return outcome;
    }

    void setOutcome(double outcome) {
        this.outcome = outcome;
    }


    // check case balance and warn
    public void checkBalance(){
        System.out.println();
        if(outcome > income){
            System.out.printf("[DANGER!] Warehouse BALANCE:[%.2f]! \n"
                    , income - outcome);
        }else if(outcome==income){
            System.out.println("[DANGER!] Warehouse is nearly to bankrupt!");
        }else{
            System.out.printf("Warehouse BALANCE:[%.2f]. We are on a right way!\n", income-outcome);
        }
        System.out.println();
    }

}
