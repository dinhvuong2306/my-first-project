/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.repositories;

import com.mycompany.jv30_project_final.entities.AccountEntity;
import java.io.Serializable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends
        CrudRepository<AccountEntity, Integer> {

    AccountEntity findByEmailLikeAndPasswordLike(
            String email, String password);

    AccountEntity findByEmailLike(String email);
    
    @Query("Select acc From AccountEntity acc "
            + "Join fetch acc.accountRoles "
            + "Where acc.email Like ?1 and "
            + "acc.password Like ?2")
    AccountEntity findAccountByEmailAndPassword(
            String email, String password);
}
