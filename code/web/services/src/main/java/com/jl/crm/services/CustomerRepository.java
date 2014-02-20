package com.jl.crm.services;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Repository for dealing with {@link Customer customer } records.
 *
 * @author Josh Long
 */
@RepositoryRestResource
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	Page<Customer> findByUserId(@Param("userId") Long userId, Pageable pageable);

	List<Customer> findByUserId(@Param("userId") Long userId);

	@Query ("select c from Customer c where  c.user.id = :userId and (LOWER(concat(c.firstName, c.lastName)) LIKE :q   )")
	List<Customer> search(@Param("userId") Long userId,  @Param("q") String query);

}
