package com.kanto.ecommerce.config;

import com.kanto.ecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        //pour ne pas autoriser les PUT, POST, DELETE....Read Only!!
        HttpMethod[] theUnsupportedAction = {HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PATCH};

        //disabled product methods for PUT, POST and DELETE
        disableHttpMethods(Product.class, config, theUnsupportedAction);

        //disabled productCategory methods for PUT, POST and DELETE
        disableHttpMethods(ProductCategory.class, config, theUnsupportedAction);

        disableHttpMethods(Country.class, config, theUnsupportedAction);

        disableHttpMethods(State.class, config, theUnsupportedAction);

        disableHttpMethods(Order.class, config, theUnsupportedAction);

        //call an internal helper to expose the IDs
        exposeIds(config) ;

        //configure cors mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins) ;
    }

    //methode refactored
    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedAction) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedAction))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedAction));
    }

    //Avoir les IDs
    private void exposeIds(RepositoryRestConfiguration config) {

        //expose entity IDs
        //

        // get a list of entity classes from the entityManager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        //create an array of the entity type
        List<Class> entityClasses = new ArrayList<>() ;

        // get the entity type for the entities
        for(EntityType tempEntityType: entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        // expose the entity IDs for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]) ;
        config.exposeIdsFor(domainTypes);

    }




}
