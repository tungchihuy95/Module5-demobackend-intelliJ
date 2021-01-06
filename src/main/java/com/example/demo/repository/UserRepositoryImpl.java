package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserRepositoryImpl  implements UserRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public User findById(Long id) {
        TypedQuery<User> query= em.createQuery("select c from User c where c.id=?1",User.class );
        query.setParameter(1, id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("select c from User c ", User.class);
        return query.getResultList();
    }

    @Override
    public void save(User model) {
        if (model.getId() != null){
            em.merge(model);
        }else em.persist(model);
    }

    @Override
    public void remove(Long id) {
        User user= findById(id);
        if(user!=null){
            em.remove(user);
        }
    }
}