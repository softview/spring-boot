package fr.softview.springboot.repository;

import fr.softview.springboot.model.entity.OperationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by sambakamara on 19/11/2017.
 */

public interface OperationRepository  extends CrudRepository<OperationEntity, Integer> {

    List<OperationEntity> findByAccount(String accountNumber);
}
