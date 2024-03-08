package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.Utils.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByNick(String nick);

    User findByEmail(String email);

    List<User> findByState(UserState state);

    List<User> findByNickOrEmail(String nick, String email);

}
