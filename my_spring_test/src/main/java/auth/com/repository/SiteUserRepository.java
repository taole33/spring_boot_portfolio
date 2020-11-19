package auth.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import auth.com.model.SiteUser;

public interface SiteUserRepository extends JpaRepository<SiteUser,Long> {
	SiteUser findByUsername(String username);
	boolean existsByUsername(String username);	
}