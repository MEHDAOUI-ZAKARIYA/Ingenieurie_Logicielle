package Dao;

public class DaoImpl implements IDao{
    @Override
    public double getData(){

        /*
        Se connecter a la bd pour recuperer la tempearture
         */

        System.out.println("Version base de donne");
        return Math.random()*40;
    }
}
