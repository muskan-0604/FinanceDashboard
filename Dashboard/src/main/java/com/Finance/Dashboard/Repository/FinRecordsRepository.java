package com.Finance.Dashboard.Repository;

import com.Finance.Dashboard.Model.Enum.RecordType;
import com.Finance.Dashboard.Model.FinRecords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface FinRecordsRepository extends JpaRepository<FinRecords,Long>
{

    public List<FinRecords> findByType (RecordType type);

    public List<FinRecords> findByCategory(String category);

    public List<FinRecords> findByDateBetween(LocalDate start , LocalDate end);
}
