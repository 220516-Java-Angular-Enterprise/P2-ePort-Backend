package com.revature.ePort.scp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SCPRepository extends CrudRepository<SCP, String> {
}
