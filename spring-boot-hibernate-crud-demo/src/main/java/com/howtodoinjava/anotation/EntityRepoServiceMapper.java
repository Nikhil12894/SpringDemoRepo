/**
 * 
 */
package com.howtodoinjava.anotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
/**
 * @author Nalin
 *
 */
public @interface EntityRepoServiceMapper {
	public Class<?> repoClass();
	public Class<?> serviceClass();
}
