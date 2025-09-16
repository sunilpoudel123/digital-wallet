package org.walletuser.walletuser.Service;

import org.springframework.http.ResponseEntity;
import org.walletuser.walletuser.Model.User;

public interface UserService {

    public ResponseEntity updateUser(User user) ;
    //public ResponseEntity updateAdmin(User user) ;
}
