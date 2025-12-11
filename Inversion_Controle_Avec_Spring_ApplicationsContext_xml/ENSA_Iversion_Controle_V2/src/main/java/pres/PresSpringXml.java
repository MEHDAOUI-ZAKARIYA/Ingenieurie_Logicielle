package pres;

import metier.Imetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.PrintStream;

public class PresSpringXml {
    public static void main(String[] args) {
        ApplicationContext contxt = new ClassPathXmlApplicationContext("applicationContext.xml");
        Imetier metier = (Imetier) contxt.getBean("metier");
        System.out.println("Resultat = "+ metier.calcul());
    }
}
