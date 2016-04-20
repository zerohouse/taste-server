package at.begin.web.reply;


import at.begin.web.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Reply {

    @ManyToOne
    @JoinColumn
    User user;

    @Id
    @Column
    @GeneratedValue
    Long id;

    @Column
    Date createAt;

    @Pattern(regexp = ".{10,}", message = "열자 이상 작성해주세요.")
    @Column
    String message;

}
