package com.Finance.Dashboard.Repository;

import com.Finance.Dashboard.Model.Enum.Role;
import com.Finance.Dashboard.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>
{
public User findByEmail(String email);
public List<User> findByRole(Role role);
}
