package com.aplazotest.simpleinterest.repository;

import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cleber
 */
@Repository
public interface SimpleInterestRequestRepository extends CrudRepository<SimpleInterestRequest, Long> {
}
