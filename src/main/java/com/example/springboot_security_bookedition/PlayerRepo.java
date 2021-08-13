package com.example.springboot_security_bookedition;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepo extends CrudRepository<Player, Long> {
}
