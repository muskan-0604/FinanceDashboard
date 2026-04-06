package com.Finance.Dashboard.Service;

import com.Finance.Dashboard.Exception.AccessDeniedException;
import com.Finance.Dashboard.Exception.ResourceNotFoundException;
import com.Finance.Dashboard.Model.Enum.RecordType;
import com.Finance.Dashboard.Model.Enum.Role;
import com.Finance.Dashboard.Model.FinRecords;
import com.Finance.Dashboard.Model.User;
import com.Finance.Dashboard.Repository.FinRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FinRecordService {

    @Autowired
    public FinRecordsRepository finRecordsRepository;


    //Getting all records
    public List<FinRecords> getAllRecords(){
        return finRecordsRepository.findAll();
    }


    //Getting records by id
    public FinRecords getRecords(Long id) {
        return finRecordsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
    }


    //Delete Records
    public void deleteRecord(Long id) {
        FinRecords record = finRecordsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));

        if (record.getUser().getRole() != Role.ADMIN) {
            throw new AccessDeniedException("Access Denied");
        }

        finRecordsRepository.deleteById(id);
    }

    //Create Records

    public FinRecords createRecords(FinRecords finRecords) {
      User user =    finRecords.getUser();
      Role role =  user.getRole();

        if(role != Role.ADMIN ){
            return null;
        }
        else {
            return finRecordsRepository.save(finRecords);
        }
    }

    //Update Records
    public FinRecords UpdateRecords(Long id,FinRecords finRecords) {
     Optional <FinRecords> optional = finRecordsRepository.findById(id);

        if (optional.isEmpty()) {
            throw new  ResourceNotFoundException("Record not found with id: " + id);
        }

        FinRecords data = optional.get();
        User user = data.getUser();

        if(user.getRole() != Role.ADMIN){
            throw new AccessDeniedException("You don't have permission to access this");
        }

            if(finRecords.getAmount()!=null){
                data.setAmount(finRecords.getAmount());
            }
            if(finRecords.getType()!=null){
                data.setType(finRecords.getType());
            }
            if(finRecords.getCategory()!=null){
                data.setCategory(finRecords.getCategory());
            }
            if(finRecords.getDate()!=null){
                data.setDate(finRecords.getDate());
            }
            if(finRecords.getDescription()!=null){
                data.setDescription(finRecords.getDescription());
            }
            if(finRecords.getUser()!=null){
                data.setUser(finRecords.getUser());
            }

return finRecordsRepository.save(data);
}


    public List<FinRecords> getRecordsByType (RecordType type){
   return   finRecordsRepository.findByType(type);
    }
    public List<FinRecords> getRecordsByDate (LocalDate start , LocalDate end){
        return   finRecordsRepository.findByDateBetween(start,end);
    }
    public List<FinRecords> getRecordsByCategory (String category){
        return   finRecordsRepository.findByCategory(category);
    }
}