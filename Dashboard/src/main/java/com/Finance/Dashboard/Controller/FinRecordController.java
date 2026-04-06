package com.Finance.Dashboard.Controller;

import com.Finance.Dashboard.Model.FinRecords;
import com.Finance.Dashboard.Service.FinRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/records")
public class FinRecordController {

    @Autowired
    public FinRecordService finRecordService;

    @GetMapping
    ResponseEntity<List<FinRecords>> getAllRecords(){
        List<FinRecords> allRecord = finRecordService.getAllRecords();
        return new ResponseEntity<>(allRecord,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<FinRecords> getRecords(@PathVariable Long id){
   FinRecords records =  finRecordService.getRecords(id);

   if(records==null){
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
        return new ResponseEntity<>(records,HttpStatus.OK);
    }


    @PostMapping
    ResponseEntity<FinRecords> createRecords(@Valid @RequestBody FinRecords finRecords){
        FinRecords create = finRecordService.createRecords(finRecords);

        return new ResponseEntity<>(create,HttpStatus.CREATED);
    }

    @PatchMapping("/records/update/{id}")
    ResponseEntity<FinRecords> updateRecords(@PathVariable Long id, @Valid @RequestBody FinRecords finRecords){

      FinRecords updated =  finRecordService.UpdateRecords(id,finRecords);

      if (updated == null){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }
}
