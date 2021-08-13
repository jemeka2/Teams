package com.example.springboot_security_bookedition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PlayerRepo playerRepo;

    @Autowired
    TeamRepo teamRepo;

    public void run(String...args){
        User user = new User("user", "bart@domain.com", "user", "Bart","Simpson", true);
        Role userRole = new Role("user", "ROLE_USER");
        userRepo.save(user);
        roleRepo.save(userRole);

        User admin = new User("admin", "super@domain.com", "admin", "Super", "Hero", true);
        Role adminRole1 = new Role("admin", "ROLE_ADMIN");
        Role adminRole2 = new Role("admin", "ROLE_USER");

        userRepo.save(admin);
        roleRepo.save(adminRole1);
        roleRepo.save(adminRole2);

        Team team1 = new Team();
        Team team2 = new Team();
        Team team3 = new Team();

        team1.setName("The Cicadas");
        team2.setName("The Blue Crabs");
        team3.setName("The Woodpeckers");

        team1.setCity("Norristown");
        team2.setCity("Willow Grove");
        team3.setCity("Lansdale");

        team1.setSport("Soccer");
        team2.setSport("Football");
        team3.setSport("Basketball");

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        Player player5 = new Player();
        Player player6 = new Player();

        player1.setfName("Lionel");
        player1.setlName("Messi");
        player1.setPhoto("https://res.cloudinary.com/jabiremeka/image/upload/v1628872014/https_3A_2F_2Fspecials-images.forbesimg.com_2Fimageserve_2F5ec595d45f39760007b05c07_2F0x0_jsz4ag.jpg");
        player1.setAge(18);
        player1.setTeam(team1);

        player2.setfName("Christino");
        player2.setlName("Ronaldo");
        player2.setPhoto("https://res.cloudinary.com/jabiremeka/image/upload/v1628867596/gettyimages-971463110_booxsx.jpg");
        player2.setAge(20);
        player2.setTeam(team1);

        player3.setfName("Tom");
        player3.setlName("Brady");
        player3.setPhoto("https://res.cloudinary.com/jabiremeka/image/upload/v1628872035/07sb-oldbrady-mediumSquareAt3X_eeegu0.jpg");
        player3.setAge(17);
        player3.setTeam(team2);

        player4.setfName("Odell");
        player4.setlName("Beckham");
        player4.setPhoto("https://res.cloudinary.com/jabiremeka/image/upload/v1628874192/2090_eke89x.jpg");
        player4.setAge(16);
        player4.setTeam(team2);

        player5.setfName("Lebron");
        player5.setlName("James");
        player5.setPhoto("https://res.cloudinary.com/jabiremeka/image/upload/v1628868021/504c63a03d8a751a5cbeda0bc064306bb4-lebron-james.rsquare.w700_eihwpb.jpg");
        player5.setAge(18);
        player5.setTeam(team3);

        player6.setfName("Giannis");
        player6.setlName("Antetokounmpo");
        player6.setPhoto("https://res.cloudinary.com/jabiremeka/image/upload/v1628868043/ob8r0cv__400x400_mkwudk.jpg");
        player6.setAge(15);
        player6.setTeam(team3);

        Set<Player> players1 = new HashSet<>();
        Set<Player> players2 = new HashSet<>();
        Set<Player> players3 = new HashSet<>();

        players1.add(player1);
        players1.add(player2);
        players2.add(player3);
        players2.add(player4);
        players3.add(player5);
        players3.add(player6);

        team1.setPlayers(players1);
        team2.setPlayers(players2);
        team3.setPlayers(players3);

        teamRepo.save(team1);
        teamRepo.save(team2);
        teamRepo.save(team3);
    }

}
