package com.enviodecorreo.email.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.enviodecorreo.email.Entity.Email;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {
}
