package pres;

import Dao.IDao;
import ext.DaoImpl2;
import metier.MetierImpl;

public class Presentation {
    static void main(String[] args) {
        /*
        Injection des dependances par instanciation statique ===>  new
         */

        DaoImpl2 dao = new DaoImpl2();
        MetierImpl metier = new MetierImpl();
        metier.setDao((IDao) dao);
        System.out.println("Resultat = " + metier.calcul());


    }
}
