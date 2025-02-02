package com.web.backend.application.service.impl.objective;

import com.web.backend.application.dto.objective.ObjectiveRequest;
import com.web.backend.application.dto.objective.ObjectiveResponse;
import com.web.backend.application.exception.customer.CustomerNotFoundException;
import com.web.backend.application.exception.objective.ObjectiveNotFoundException;
import com.web.backend.application.exception.objective.ObjectiveStatusNotFoundException;
import com.web.backend.application.service.interfaces.objective.ObjectiveService;
import com.web.backend.domain.model.customer.Customer;
import com.web.backend.domain.model.objective.Objective;
import com.web.backend.domain.model.objective.ObjectiveStatus;
import com.web.backend.domain.repository.customer.RCustomer;
import com.web.backend.domain.repository.objective.ObjectiveRepository;
import com.web.backend.domain.repository.objective.ObjectiveStatusRepository;
import com.web.backend.infrastructure.api.utils.objective.ObjectiveMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectiveServiceImpl implements ObjectiveService {
    private final ObjectiveRepository objectiveRepository;
    private final ObjectiveStatusRepository objectiveStatusRepository;
    private final RCustomer customerRepository;
    private final ObjectiveMapper objectiveMapper;

    @Override
    public ObjectiveResponse createObjective(ObjectiveRequest objectiveRequest) {
        Objective objective = objectiveMapper.toObjective(objectiveRequest);
        ObjectiveStatus status = objectiveStatusRepository.findById(objectiveRequest.objectiveStatusId())
                        .orElseThrow(() -> new ObjectiveStatusNotFoundException("Objective status not found with id: " + objectiveRequest.objectiveStatusId()));
        objective.setObjectiveStatus(status);

        Customer customer = customerRepository.findById(objectiveRequest.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + objectiveRequest.customerId()));
        objective.setCustomer(customer);

        objective.setProgress((int)((objective.getCurrentAmount() / objective.getTargetAmount()) * 100));

        objectiveRepository.save(objective);
        return objectiveMapper.toObjectiveResponse(objective);
    }

    @Override
    public ObjectiveResponse getObjectiveById(Long id) {
        Objective objective = objectiveRepository.findById(id)
                .orElseThrow(() -> new ObjectiveNotFoundException("Objective not found with id: " + id));
        return objectiveMapper.toObjectiveResponse(objective);
    }

    @Override
    public List<ObjectiveResponse> getObjectivesByDeleted(boolean deleted) {
        List<Objective> objectives = objectiveRepository.findAllByIsDeleted(deleted);
        return objectives.stream().map(objectiveMapper::toObjectiveResponse).toList();
    }

    @Override
    public ObjectiveResponse updateObjective(Long id, ObjectiveRequest objectiveRequest) {
        Objective existingObjective = objectiveRepository.findById(id)
                .orElseThrow(() -> new ObjectiveNotFoundException("Objective not found with id: " + id));

        objectiveMapper.updateObjectiveFromRequest(objectiveRequest, existingObjective);

        if(objectiveRequest.customerId()!= null) {
            Customer customer = customerRepository.findById(objectiveRequest.customerId())
                   .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + objectiveRequest.customerId()));
            existingObjective.setCustomer(customer);
        }

        if(objectiveRequest.objectiveStatusId() != null) {
            ObjectiveStatus objectiveStatus = objectiveStatusRepository.findById(objectiveRequest.objectiveStatusId())
                    .orElseThrow(() -> new ObjectiveStatusNotFoundException("Objective status not found with id: " + id));
            existingObjective.setObjectiveStatus(objectiveStatus);
        }

        objectiveRepository.save(existingObjective);
        return objectiveMapper.toObjectiveResponse(existingObjective);
    }

    @Override
    public void deleteObjective(Long id) {
        if(!objectiveRepository.existsById(id))
                throw new ObjectiveNotFoundException("Objective not found with id: " + id);
        objectiveRepository.deleteById(id);
    }

    public List<Objective> getAllCustomerObj(Long customerId){
        return objectiveRepository.findByCustomerId(customerId);
    }

    public Page<Objective> getAllCustomerObjLimit(Long customerId, Pageable pageable){
        return objectiveRepository.findByCustomerId(customerId,pageable);
    }
}
