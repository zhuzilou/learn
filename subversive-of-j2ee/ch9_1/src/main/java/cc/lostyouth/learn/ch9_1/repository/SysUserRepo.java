package cc.lostyouth.learn.ch9_1.repository;

import cc.lostyouth.learn.ch9_1.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by endless on 12/19/2017.
 */
public interface SysUserRepo extends JpaRepository<SysUser, Long> {
    SysUser findByUsername(String username);
}
