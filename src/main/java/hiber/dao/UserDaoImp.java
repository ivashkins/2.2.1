package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.
              getCurrentSession().createQuery("from User",User.class);
      return query.getResultList();
   }

   @Override
   public User getUserOnCar(Car car) {
      TypedQuery<User>query=sessionFactory.getCurrentSession()
              .createQuery("From User us LEFT JOIN FETCH us.car where model= :paramModel and series = :paramSeries");
      query.setParameter("paramModel" , car.getModel());
      query.setParameter("paramSeries" , car.getSeries());
      return query.getSingleResult();
   }

}
