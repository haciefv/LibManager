package domain.core.exceptions;

public class GeneralExceptions extends RuntimeException{

    public GeneralExceptions(Exceptions exceptions){
        super(exceptions.toString());
    }


}
