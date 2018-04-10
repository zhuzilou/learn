package cc.lostyouth.learn.ch9_1.security;

import cc.lostyouth.learn.ch9_1.domain.SysUser;
import cc.lostyouth.learn.ch9_1.repository.SysUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by endless on 12/19/2017.
 */
public class CustomUserService implements UserDetailsService {
    @Autowired
    SysUserRepo sysUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserRepo.findByUsername(username);
        if (null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return sysUser;
    }
}
