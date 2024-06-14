package forohub.Challenge_Four.infra.error;

public class ValidacionIntegridad extends RuntimeException{
    public ValidacionIntegridad(String mensaje){
        super(mensaje);
    }
}
