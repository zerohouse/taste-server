package at.begin.infra.inject.findbyid.findbyid;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface FindById {
    Class<? extends JpaRepository> from();

    String parameterName() default "id";

    boolean makeNotFoundException() default true;

    Caster caster() default Caster.LongID;
}
