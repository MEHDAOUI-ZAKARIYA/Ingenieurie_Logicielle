package pres;

import Dao.IDao;
import metier.Imetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Pres2 {
    static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("/home/mehdaouizakariya/IdeaProjects/ENSA_Inversion_controle/src/pres/config.txt"));

        String daoClassName = scan.nextLine();
        Class cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.newInstance();

        String metierClassName = scan.nextLine();
        Class cMetier = Class.forName(metierClassName);
        Imetier metier = (Imetier) cMetier.newInstance();

        Method method = cMetier.getMethod("setDao", IDao.class);
        //metier.setDao(dao);
        method.invoke(metier,dao);

        System.out.println("Resultat = "+ metier.calcul());


    }



}
