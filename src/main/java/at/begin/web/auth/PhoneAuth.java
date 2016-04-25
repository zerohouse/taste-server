package at.begin.web.auth;


import at.begin.infra.exception.handler.UniqueKeys;
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
public class PhoneAuth {

    @Id
    @Column
    @GeneratedValue
    Long id;

    @Column
    Date createAt;

    @Column
    String number;

    @Column
    Long userId;

    @Column
    String phone;

}
