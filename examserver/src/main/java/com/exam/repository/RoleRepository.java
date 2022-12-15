package com.exam.repository;

//step 4 b)

import com.exam.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long>
{
}
