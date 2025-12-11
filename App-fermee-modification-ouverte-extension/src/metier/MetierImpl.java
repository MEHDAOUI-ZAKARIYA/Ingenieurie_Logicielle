package metier;

import Dao.IDao;

public class MetierImpl implements Imetier{

//Couplage Faible : depandence aux Interface
        private IDao dao ;
        @Override
                public double calcul(){
            double tmp = dao.getData();
            return tmp*540/Math.cos(tmp*Math.PI);
        }

        /*
        Injection dans la variable dao un objet d'une classe qui implemete L'iterface IDao
         */

    public void setDao(IDao dao){
        this.dao =  dao;
    }
}
