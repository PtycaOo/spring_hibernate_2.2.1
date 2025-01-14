package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car bmv = new Car("BMV", 999);
      Car mercedes = new Car("Mercedes", 6);
      Car lada = new Car("Lada", 2117);
      Car vaz = new Car("VAZ", 16);

      userService.add(user1.setCar(bmv).setUser(user1));
      userService.add(user2.setCar(mercedes).setUser(user2));
      userService.add(user3.setCar(lada).setUser(user3));
      userService.add(user4.setCar(vaz).setUser(user4));


      for (User user : userService.listUsers()) {
         System.out.printf("%s %s\n",user, user.getCar());
      }

      try {
         User userWithCar = userService.getUserByCar("Mercedes", 6);
         System.out.println(userWithCar);
      } catch (Exception e){
         System.out.println("Пользоватьель не найден");
      }

      context.close();
   }
}
