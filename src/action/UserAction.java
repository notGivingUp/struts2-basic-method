package action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.User;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;  
	private static SessionFactory factory=new Configuration().configure().buildSessionFactory();
	User user = new User();
	List<User> userList = new ArrayList<User>();
	public String event;
	public String iduser;

	public String getIduser() {
	return iduser;
}

public void setIduser(String iduser) {
	this.iduser = iduser;
}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	//registeration
	public String register() throws Exception{
		Session session = factory.openSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return SUCCESS;
	}
	
	public boolean find(String name, String pwd) {
		Session session = factory.openSession();
        //session.beginTransaction();
        String sql = " from User as u where u.username=:name and u.password=:pwd";
        Query query = session.createQuery(sql);
        query.setParameter("name", name);
        query.setParameter("pwd", pwd);
        List<User> list = query.list();
        if (list.size() > 0) {
            session.close();
            return true;
        }
        session.close();
        return false;
    }
	public String login() throws Exception{		
		if (find(user.getUsername(), user.getPassword())) {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
					.get(ServletActionContext.HTTP_REQUEST);
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			return SUCCESS;
		} else {
			this.addActionError("Invalid username and password");
			return INPUT;
		}
		
	}
	
	public String logout() throws Exception{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession(false);
		session.removeAttribute("username");
		session.invalidate();
		return SUCCESS;
	}
	//show all users
	public String listUser() throws Exception{
		Session session = factory.openSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			userList=session.createQuery("FROM User").list();
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return SUCCESS;
	}
	
	public void editUser(User user){
		Session session = factory.openSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public String edit() throws Exception{
		editUser(user);
		
		try {
			listUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void deleteUser(int id){
		Session session= factory.openSession();
		org.hibernate.Transaction transaction=null;
		try {	
			transaction = session.beginTransaction();
			User user = (User) session.get(User.class, id);
			session.delete(user);
			transaction.commit();
		} catch (Exception e) {

			transaction.rollback();
			e.printStackTrace();
		}finally {
	         session.close(); 
	      }
	}
	public User getUserByID(int iduser){
		Session session = factory.openSession();
		User user=null;
		try {
			user=(User) (User) session.get(User.class, iduser);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}
	
	public String eventAction() throws Exception{
		User selectedUser;
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		int userID = Integer.parseInt(iduser);
		if (event.equals("delete")) {
			deleteUser(userID);
			listUser();
			return "delete";
		} else if (event.equals("edit")) {
			selectedUser = getUserByID(userID);
			request.setAttribute("selectedUser", selectedUser);
			try {
				listUser();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "edit";
		} else
			return SUCCESS;
	}

}
