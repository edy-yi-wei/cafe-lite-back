package com.besoft.cafelite.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.besoft.cafelite.model.Adjustment;
import com.besoft.cafelite.model.CashierSession;
import com.besoft.cafelite.model.Invoice;
import com.besoft.cafelite.model.Purchasing;

@Repository
public interface ReportRepository extends JpaRepository<Invoice, Long>{
	
	@Query("SELECT i FROM Invoice i WHERE i.invoiceDate>= :startDate AND i.invoiceDate<= :endDate")
	List<Invoice> findInvoiceByPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query("SELECT c FROM CashierSession c WHERE c.loginTime >= :startDate AND c.loginTime <= :endDate")
	List<CashierSession> findCashierSessionByPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query("SELECT i FROM Purchasing i WHERE i.purchasingDate>= :startDate AND i.purchasingDate<= :endDate ORDER BY i.purchasingDate ASC")
	Page<Purchasing> findPurchasingByPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable page);
	
	@Query("SELECT i FROM Adjustment i WHERE i.adjustmentDate>= :startDate AND i.adjustmentDate<= :endDate ORDER BY i.adjustmentDate ASC")
	Page<Adjustment> findAdjustmentByPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable page);
}
