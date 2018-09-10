package com.accenture.avs.device.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) 
public @interface ManagedApplicationConfiguration {
	
	String propertyName();
	String platform() default "" ;
	String deviceType() default "";
	String retailerId() default "";	
}
