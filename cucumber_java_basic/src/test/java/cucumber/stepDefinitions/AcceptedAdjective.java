package cucumber.stepDefinitions;

import io.cucumber.java.ParameterType;

public class AcceptedAdjective {

    @ParameterType("great|feisty|nasty")
    public String adj(String adjective){
        return adjective;
    }
}
