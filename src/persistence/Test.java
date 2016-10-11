package persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author <a href="mailto:sabrinee.ayachi@gmail.com">Sabrine AYACHI</a>
 */
public class Test {
	
	public void ajouter(Client c)
	{
		Session s = Dbsession.getSessionFactory().openSession();
		
		Transaction tx = s.beginTransaction();
		
		s.save(c);
		tx.commit();
		s.close();
	}
	
	public List<Client> findAll()
	{
		Session s = Dbsession.getSessionFactory().openSession();
		 @SuppressWarnings("unchecked")
		List<Client>  l = s.createQuery("from Client").list();
		return l;
	}
	
	public Client findById(int id)
	{
		Session s = Dbsession.getSessionFactory().openSession();
		Client c = (Client) s.get(Client.class, id);
		return c;
		
		
	}
	
	public void supprimer(Client c)
	{
		Session s = Dbsession.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.delete(c);
		tx.commit(); //commit is necessary for hibernate, but not the case for jdbc
		s.close();
		
	}
	
	public void modifier(Client c, String nom)
	{
		Session s = Dbsession.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		c.setNom(nom);
		s.update(c);
		tx.commit(); //commit is necessary for hibernate, but not the case for jdbc
		s.close();
		
	}
	

	public List<Client> findByCriteria(String nom)
	{
		Session s = Dbsession.getSessionFactory().openSession();
		Criteria cr = s.createCriteria(Client.class);
		Criterion c1 = Restrictions.eq("nom", nom); // without this criterion, findbycriteria reacts like findall method
		cr.add(c1);
		List<Client> l = cr.list();
		return l;
		

		
	}
	
	public Users findByuserByid(String username, String pwd)
	{
		Session s = Dbsession.getSessionFactory().openSession();
		UsersId x = new UsersId(username, pwd);
		Users us = (Users) s.get(Users.class, x);
		return us;
		
	}
	
	public List<Users> findUserByCriteria(String username, String pwd)
	{
		Session s = Dbsession.getSessionFactory().openSession();
		Criteria cr = s.createCriteria(Users.class);
		UsersId x = new UsersId(username, pwd);
		//Criterion user = Restrictions.idEq(x); // without this criterion, findbycriteria reacts like findall method
		Criterion user = Restrictions.eq("id", x); // meilleur que la pr�cedente
		cr.add(user);
		List<Users> l = cr.list();
		return l;
		

		
	}
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Test t = new Test();
		/*Client clt = new Client();
		clt.setIdclient(2016);
		clt.setNom("Ali");
		t.ajouter(clt);*/
		List<Client>  ll = t.findAll();
		//System.out.println("size " + l.size() );
		
		/*for(int i=0; i<ll.size(); i++)
		{
			Client c = ll.get(i);
			int id = c.getIdclient();
			String nom = c.getNom();
			 //System.out.println("//////////");
			System.out.println("client " + c.toString() + " id " + id + " nom " + nom);
			
		}*/
		 System.out.println("//////////");
		/*Client clt = t.findById(5);
		 System.out.println("prenom : " + clt.getPrenom() + " nom : " + clt.getNom());
		 
		 System.out.println("//////////");
		 t.modifier(clt, "sabrine");
		 List<Client>  l = t.findAll();*/
		//List<Client> l = t.findByCriteria("sabrine");
		 List<Users> l = t.findUserByCriteria("ali", "ali12");
		 for(int i=0; i<l.size(); i++)
			{
				Users user = l.get(i);
			
				 //System.out.println("//////////");
				 System.out.println("role : " + user.getRole() + " password : " + user.getId().getPasseword() + " username: " + user.getId().getUsername());
				
			}
		
		 //Users user = t.findByuserByid("ali", "ali12");
		// System.out.println("role : " + user.getRole() + " password : " + user.getId().getPasseword() + " username: " + user.getId().getUsername());
		 
		 /*for(int i=0; i<l.size(); i++)
			{
				Client c = l.get(i);
				int id = c.getIdclient();
				String nom = c.getNom();
				 //System.out.println("//////////");
				System.out.println(" id " + id + " nom " + nom);
				
			}*/
		/* t.supprimer(clt);
		 List<Client>  l = t.findAll();
		 for(int i=0; i<l.size(); i++)
			{
				Client c = l.get(i);
				int id = c.getIdclient();
				String nom = c.getNom();
				 //System.out.println("//////////");
				System.out.println(" id " + id + " nom " + nom);
				
			}*/
		
		
		// System.out.println("client " + t.findById(123).toString());
		
		 /*List<Client>  l = t.findAll();
		 System.out.println("la liste est " + l);*/
	}
	
	
	

}
