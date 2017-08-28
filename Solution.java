package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Item;
import model.User;
public class Solution1{

	public static void main(String[] args) throws IOException, SQLException {
		String uName;
		String uPassword;
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		SessionFactory sf=new Configuration().configure().buildSessionFactory();
		Session session=sf.openSession();
		session.beginTransaction();
		Item[] item=new Item[50];
		item[0]=new Item();
		item[0].setiName("SAMPOO");
		item[0].setPrice(50);
		item[0].setQuantity(1);
		item[0].seteDate("2-12-2016");
		item[0].setmDate("2-12-2015");
		session.save(item[0]);
		item[1]=new Item();
		item[1].setiName("CREAM");
		item[1].setPrice(200);
		item[1].setQuantity(2);
		item[1].seteDate("12-9-2017");
		item[1].setmDate("12-9-2016");
		session.save(item[1]);		
		User[] user=new User[3];
		user[0]=new User();
		user[0].setUserName("VICKY");
		user[0].setPassword("VICKY");
		session.save(user[0]);
		user[1]=new User();
		user[1].setUserName("RADHAN");
		user[1].setPassword("RADHAN");
		
		session.save(user[1]);
		
		session.getTransaction().commit();
		session.close();
		System.out.println("user name:");
		uName=bf.readLine();
		System.out.println("password:");
		uPassword=bf.readLine();
		
		User users=new User();
		users.setUserName(uName);
		users.setPassword(uPassword);
		try {
			if (Authentication.check(users)) {
				System.out.println("LOGINED");
				boolean flag = false;
				while (true) {
					System.out.print(" 1.Create\n 2.view all\n 3.Update\n 4.Delete\n Choice:");
					int ch=Integer.parseInt(bf.readLine());
					System.out.println(ch);
					switch(ch){
					case 1:
						Operations.insert();
						System.out.println("ADDED");
						break;
					case 2:
						Operations.retrieve();
						break;
					case 3:
						Operations.update();
						System.out.println("UPDATED");
						break;
					case 4:
						Operations.delete();
						System.out.println("DELETED");
						break;
					default:
						flag=true;
						break;
					}
					if(flag){
					break;
				}
			}
				
		}
			else{
				System.out.println("INVALID USERNAME OR PASSWORDS");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		
	}
		
}

}
