/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restuv.uv;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 *
 * @author alfonso
 */
@NamedQueries({
    @NamedQuery(name = Misura.FIND_ALL, query = "select e from Misura e order by e.quota"),
    @NamedQuery(name = Misura.FIND_BY_QUOTA_MIN, query = "select e from Misura e where e.quota>= :quota order by quota")
})

@Schema(name = "Indice UV", description = "indice uv ad una certa quota")
@Entity
@Table(name = "uvquota")
public class Misura {

    public static final String FIND_ALL = "Misura.findAll";
    public static final String FIND_BY_QUOTA_MIN = "Misura.findByQuotaMin";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(hidden = true)
    @Version
    protected Long version;

    private int quota;

    private double rdiretta;

    private double rdiffusa;

    public Misura() {
    }

    public Misura(int quota, double rdiretta, double rdiffusa) {
        this.quota = quota;
        this.rdiretta = rdiretta;
        this.rdiffusa = rdiffusa;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("quota", this.quota)
                .add("uv", this.rdiretta + this.rdiffusa)
                .build();
    }

    /*
    getter / setter
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public double getRdiretta() {
        return rdiretta;
    }

    public void setRdiretta(double rdiretta) {
        this.rdiretta = rdiretta;
    }

    public double getRdiffusa() {
        return rdiffusa;
    }

    public void setRdiffusa(double rdiffusa) {
        this.rdiffusa = rdiffusa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Misura other = (Misura) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Misura{" + "id=" + id + ", version=" + version + ", quota=" + quota + ", rdiretta=" + rdiretta + ", rdiffusa=" + rdiffusa + '}';
    }

}
