package at.begin.web.user;


import at.begin.infra.exception.handler.UniqueKeys;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(uniqueConstraints = @UniqueConstraint(name = UniqueKeys.RELATION_ALREADY_EXIST, columnNames = {"user", "matched"}))
public class UserMatchedUser {

    @ManyToOne
    @JoinColumn
    User user;

    @ManyToOne
    @JoinColumn
    User matched;

    @Id
    @Column
    @GeneratedValue
    long id;
}
