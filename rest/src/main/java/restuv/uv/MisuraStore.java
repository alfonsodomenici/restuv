/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restuv.uv;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author alfonso
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class MisuraStore {

    @PersistenceContext
    private EntityManager em;

    public Misura create(Misura m) {
        return em.merge(m);
    }

    public List<Misura> search(Integer minQuota) {
        Stream<Misura> s = em.createNamedQuery(Misura.FIND_ALL, Misura.class)
                .getResultStream();
        return minQuota == null ? s.collect(Collectors.toList()) : s.filter(m -> m.getQuota() > minQuota).collect(Collectors.toList());
    }

    public Optional<Misura> find(long id) {
        Misura found = em.find(Misura.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }

    public void delete(long id) {
        em.remove(em.find(Misura.class, id));
    }
}
