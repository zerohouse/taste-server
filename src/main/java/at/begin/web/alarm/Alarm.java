package at.begin.web.alarm;

import at.begin.infra.exception.handler.UniqueKeys;
import at.begin.web.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Alarm {

    @ManyToOne
    @JoinColumn
    User user;

    @Id
    @Column
    @GeneratedValue
    Long id;

    @Column
    String message;

    @Column
    Date createAt;

    @Column
    Boolean check;
}

