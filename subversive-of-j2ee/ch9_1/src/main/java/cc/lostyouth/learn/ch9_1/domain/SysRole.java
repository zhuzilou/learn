package cc.lostyouth.learn.ch9_1.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by endless on 12/19/2017.
 */
@Entity
public class SysRole {
    @Id
    @GenericGenerator(
            name = "SEQ_SYS_ROLE",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "SEQ_SYS_ROLE")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SYS_ROLE")
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
