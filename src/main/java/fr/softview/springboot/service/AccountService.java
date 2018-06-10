package fr.softview.springboot.service;

import fr.softview.springboot.model.business.Operation;
import fr.softview.springboot.model.dto.OperationsDto;

/**
 * Created by sambakamara on 19/11/2017.
 */

public interface AccountService {

    public Operation deposit(String accountNumber, int amount);
    public OperationsDto history(String accountNumber);
}
