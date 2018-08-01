package com.mpersd.spring.datos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDAO<T extends Serializable> {
  private Class<T> clazz;
 
   @PersistenceContext(unitName = "cometaPU")
   EntityManager entityManager;
 
   public final void setClazz( Class< T > clazzToSet ){
      this.clazz = clazzToSet;
   }
 
   public T findOne( int id ){
      return entityManager.find( clazz, id );
   }
   public List< T > findAll(){
      return entityManager.createQuery( "from " + clazz.getName() )
       .getResultList();
   }
 
   public void create( T entity ){
      entityManager.persist( entity );
   }
 
   public T update( T entity ){
      return entityManager.merge( entity );
   }
 
   public void delete( T entity ){
      entityManager.remove( entityManager.contains(entity) ? entity : entityManager.merge(entity) );
   }
   
   public void deleteById( int entityId ){
      T entity = findOne( entityId );
      delete( entity );
   }
}
