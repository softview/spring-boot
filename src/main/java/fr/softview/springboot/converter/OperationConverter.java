package fr.softview.springboot.converter;

import fr.softview.springboot.model.business.Operation;
import fr.softview.springboot.model.dto.OperationsDto;
import fr.softview.springboot.model.entity.OperationEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by sambakamara on 19/11/2017.
 */

@Component
public class OperationConverter<A,B>  implements Function <OperationEntity,Operation>{

    @Override
    public Operation apply(OperationEntity operationEntity) {

        Operation operation = new Operation();
        operation.setId(operationEntity.getId());
        operation.setAccountNumber(operationEntity.getAccount());
        operation.setBalance(operationEntity.getBalance());
        operation.setType(operationEntity.getType());
        operation.setDate(operationEntity.getDate());
        operation.setAmount(operationEntity.getAmount());

        return operation;
    }

    public Operation convert(OperationEntity operationEntity){
        return  apply(operationEntity);
    }

    public OperationsDto convertList(List<OperationEntity> operationEntities){
        List<Operation> operations = operationEntities.stream().map(this::apply).collect(Collectors.toList());

        OperationsDto operationsDto = new OperationsDto();
        operationsDto.setOperations(operations);

        return operationsDto;
    }

}
