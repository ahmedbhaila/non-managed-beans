non-managed-beans
=================

A simple example to demonstrate the injection of a Spring bean from a non-managed object

If you want to use a Spring injected bean from a non-managed object, Spring can help. A non-managed object is any object whose lifecyle is not controlled by Spring DI. Some examples of non-managed objects:

1. An object created by simply using the new operator or newInstance method
2. Created by some 3rd party API

Objects created by JPA Provider (hibernate) doesnt seem to benefit from this, so you cannot use the method described to inject beans into a JPA entity.

How?
- Mark the non-managed class with @Configurable annotation. This tells spring that the class object will use spring managed beans. Spring uses AspectJ magic to acheive this. 

- Mark JavaConfig class with these annotations:<br/>
   @EnableLoadTimeWeaving(aspectjWeaving=AspectJWeaving.ENABLED)<br/>
   @EnableSpringConfigured

- Since spring uses runtime AspectJ weaving for this to work, use -javaagent:/path/to/spring-instrument.jar when running the application
