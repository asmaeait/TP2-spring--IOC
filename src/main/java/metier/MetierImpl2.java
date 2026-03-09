package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("metier2") // declarer cette classe comme un bean Spring id= metier2
public class MetierImpl2 implements IMetier{
    private IDao dao;

    @Override
    public double calcul(){
        return dao.getValue() + 100;
    }
    // Setter pour l'injection par setter
    @Autowired

    @Qualifier("dao2")
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
