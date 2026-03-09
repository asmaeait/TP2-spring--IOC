package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("metier")  // Déclare cette classe comme un bean Spring avec l'identifiant "metier"
public class MetierImpl implements IMetier {

    // Façon 1 → injection par champ (utilisée par Spring)    @Qualifier("dao2")
    @Autowired
    @Qualifier("dao2") //@Qualifier nécessaire car 2 beans de type IDao
    private IDao dao;  // Par défaut, Spring injectera le premier bean compatible trouvé

    @Override
    public double calcul() {
        // Utilise la méthode getValue() de l'implémentation injectée de IDao
        return dao.getValue() * 2;
    }

    // Façon 2 → injection par setter (juste pour montrer que ça existe)
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}