package org.walletuser.walletuser.Repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.walletuser.walletuser.Model.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByRole(String role);

    @Query("SELECT u FROM User u WHERE u.role = :role AND u.createdAt BETWEEN :startDate AND :endDate")
    List<User> findUsersByRoleAndCreatedAtBetween(
            @Param("role") String role,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
