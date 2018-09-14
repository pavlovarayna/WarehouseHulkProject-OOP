package Individuals;

public class Individual {
    private String name;
    private PersonalType personalType;
    private String nationality;
    private String personalInfo;

    Individual(String name, PersonalType personalType, String nationality, String personalInfo) {
        this.name = name;
        this.personalType = personalType;
        this.nationality = nationality;
        this.personalInfo = personalInfo;
    }
    Individual(PersonalType personalType){
        this.personalType = personalType;
    }


    public String getName() {
        return name;
    }


    PersonalType getPersonalType() {
        return personalType;
    }

    String getNationality() {
        return nationality;
    }

    String getPersonalInfo() {
        return personalInfo;
    }
}
