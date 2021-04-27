/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restuv.utenti;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author alfonso
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class UtenteStore {

    @PersistenceContext
    private EntityManager em;
    
    @Inject
    JWTManager jwtm;
    
    public Utente create(Utente u){
        Utente saved = em.merge(u);
        saved.setToken(jwtm.generate(saved));
        return em.merge(saved);
    }
}
