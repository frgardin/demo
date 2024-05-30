# Application Context

## Objectives

- Explain how the Spring Inversion of Control (IoC) Container Manages your Spring Beans
- Explain what is an ApplicationContext and how to we create one? 
- Get a bean out of the ApplicationContext

## Dependencies

- In a Java Application, different objects work together i.e., objects collaborate to accomplish a goal and subsequently have dependencies
- Such direct coded dependencies couple objects to each other directly. We will attempt to explain how Spring can help us to avoid such brittle
dependencies.
- Our simplistic example will take us through this and the next lesson. It consists of a Service delegating to a Data Access Object (DAO). We will start with two interfaces to define our domain context.

```java
public interface EmployeeService {
    EmployeeDao getDao();
}
```
```java
public interface EmployeeDao {

}
```
```java
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public EmployeeDao getDao() {
        return new EmployeeDaoImpl();
    }
}
```
```java
public class EmployeeDaoImpl implements EmployeeDao {

}
```

Our problem is the tight coupling in the getDao() method. Our Service implementation is tied directly to an implementation class.

The dependency is in the code and will require a code change if another EmployeeDao implementation class is required later to accommodate a requirement change.

## Trying decoupling

```java
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public EmployeeDao getDao() {
        return EmployeeDaoFactory.getInstance();
    }
}
```

This is better. The decision as to what DAO will be used by our Service is given and encapsulated by another class.

However, the client class STILL has to look up the dependency object itself from the Factory

## Reduce Coupling Further

Now, let's have another class create the DAO and give it to our Service
class instance via a setter.

```java
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao dao;
    public void setDao(EmployeeDao dao) {
        this.dao = dao;
    }
    public EmployeeDao getDao() {
        return dao;
    }
}
```
```java
public static void main(String args[]) {
    EmployeeService service = new EmployeeServiceImpl();
    service.setDao(new EmployeeDaoImpl());
    service.getDao();
}
```
Our code in our Service class above is very clean. However, our client class appears to be working heavily. It needs to know the intimate details of the service such that it has a EmployeeDaoImpl.

## Spring makes it easier

That is the basis of Spring's IoC in the form of dependency injection

 - It operates as a huge factory that looks up dependencies of a target class that it is instatiating
 - Via reflection, it creates instances of the dependencies themselves, then uses the target class's setters or constructors to inject them into the target class. There is NO code lookup in the target class, nor in some master client class
 - It has the dependencies between objects but NOT in code in configuration
 - Let's take a look at Spring to see how it works

