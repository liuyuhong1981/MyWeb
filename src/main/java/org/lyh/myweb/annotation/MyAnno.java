/**
 * Created By Liu Yuhong - 2017年9月28日
 */
package org.lyh.myweb.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**<pre>
 * 
 * </pre>
 * @author Liu, Yuhong
 * @version 1.0
 * @since 2017年9月28日
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface MyAnno {
    String name() default "";
}
