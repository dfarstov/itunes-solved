package org.astelit.itunes.contstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotEmpty
@Pattern(regexp = "^[а-яА-Я0-9- ]*$", message = "содержит недопустимые символы")
@Size(min = 4, max = 255)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface PlaylistName {
    String message() default "Cодержит недопустимые символы";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
