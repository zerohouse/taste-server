package at.begin.web.chat.message;

import at.begin.infra.util.JpaConverterJson;
import at.begin.web.chat.Chat;
import at.begin.web.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.Persistent;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.BooleanSupplier;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table
public class Message {

    @ManyToOne
    @JoinColumn
    Chat chat;

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
    Boolean reading;

    @Column
    Date createAt;
}
