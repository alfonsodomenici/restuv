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
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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

        List<Misura> result;

        if (minQuota == null) {
            result = em.createNamedQuery(Misura.FIND_ALL).getResultList();
        } else {
            result = em.createNamedQuery(Misura.FIND_BY_QUOTA_MIN)
                    .setParameter("quota", minQuota)
                    .getResultList();
        }
        return result;
    }

    public JsonArray searchJson(Integer minQuota) {
        List<Misura> result = search(minQuota);
        JsonArrayBuilder b = Json.createArrayBuilder();
        result.forEach(m -> b.add(m.toJson()));
        return b.build();
    }

    public List<MisuraCalcolata> searchMisuraCalcolata(Integer minQuota) {
        System.out.println("misura calcolata....");
        return em.createQuery("select new restuv.uv.MisuraCalcolata(e.quota,e.rdiretta,e.rdiffusa) from Misura e order by e.quota", MisuraCalcolata.class)
                .getResultList();
    }

    public Optional<Misura> find(long id) {
        Misura found = em.find(Misura.class, id);
        return found == null ? Optional.empty() : Optional.of(found);
    }

    public void delete(long id) {
        em.remove(em.find(Misura.class, id));
    }
}
