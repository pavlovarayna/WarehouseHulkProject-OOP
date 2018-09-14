package Individuals;

public class Supplier extends Individual implements Sellable {

    public Supplier(String name, PersonalType personalType, String nationality, String personalInfo) {
        super(name, personalType, nationality, personalInfo);
    }
    public Supplier(PersonalType personalType){
        super(personalType);
    }


    @Override // when supplier add needed product ++ the warehouse costs
    public void sell(Case casse, double contractAmount) {
        System.out.printf("Supplier delivered the order! Total amount: %.2f.\n", contractAmount);
        casse.setOutcome(casse.getOutcome()+contractAmount);
    }
}
