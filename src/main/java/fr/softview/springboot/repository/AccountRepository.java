package fr.softview.springboot.repository;

import fr.softview.springboot.model.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by sambakamara on 19/11/2017.
 */

public interface AccountRepository extends CrudRepository<AccountEntity, String> {

    AccountEntity findByNumber(String number);
}
