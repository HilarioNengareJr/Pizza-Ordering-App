import java.lang.annotation.*;

// meta data
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Author {
    String name() default "Hilario Junior Nengare";
    int studentNo() default 174682;
}
