package com.cst438.package_booking.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchDetailsRepository extends JpaRepository<SearchDetails, Long>{
	public SearchDetails findBySearchId(int searchId);
}
