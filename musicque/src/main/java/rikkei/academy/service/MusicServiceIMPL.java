package rikkei.academy.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import rikkei.academy.model.Music;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
public class MusicServiceIMPL implements IMusicService{
    public static SessionFactory sessionFactory;
    public static EntityManager entityManager;
    static {
        sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
        entityManager = sessionFactory.createEntityManager();
    }
    @Override
    public List<Music> findAll() {
        String qrFindAll = "SELECT m FROM Music AS m";
        TypedQuery<Music> query = entityManager.createQuery(qrFindAll, Music.class);
        return query.getResultList();
    }

    @Override
    public Music findById(Long id) {
        String qrFindById = "SELECT m FROM Music AS m WHERE m.id =:id";
        TypedQuery<Music> query = entityManager.createQuery(qrFindById,Music.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Music music) {
        Session session;
        Transaction transaction;
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        if(music.getId()!=null){
            Music music1 = findById(music.getId());
            music1.setName(music.getName());
            music1.setArtist(music.getArtist());
            music1.setCategory(music.getCategory());
            music1.setLink(music.getLink());
            session.saveOrUpdate(music1);
        } else {
            session.saveOrUpdate(music);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteById(Long id) {

    }
}
