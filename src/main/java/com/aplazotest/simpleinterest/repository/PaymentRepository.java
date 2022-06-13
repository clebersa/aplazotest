package com.aplazotest.simpleinterest.repository;

import com.aplazotest.simpleinterest.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository for Payment objects.
 *
 * @author cleber
 */
@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
