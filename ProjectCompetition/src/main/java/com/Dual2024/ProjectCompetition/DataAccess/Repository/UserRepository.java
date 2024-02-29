package com.Dual2024.ProjectCompetition.DataAccess.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByNick(String nick);

	User findByEmail(String email);

	List<User> findByState(UserState state);

	List<User> findByNickOrEmail(String nick, String email);

}
